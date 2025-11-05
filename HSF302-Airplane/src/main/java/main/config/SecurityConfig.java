package main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Sử dụng NoOpPasswordEncoder để chấp nhận plain text password
        // LƯU Ý: Không khuyến khích cho môi trường production
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Tạm thời disable CSRF để test dễ hơn
            .authorizeHttpRequests(authz -> authz
                // Public pages
                .requestMatchers("/", "/search", "/flight/**", "/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()
                
                // Admin pages - only ADMIN role
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
                // Manager pages - only MANAGER role
                .requestMatchers("/manager/**").hasRole("MANAGER")
                
                // User pages - only USER role
                .requestMatchers("/user/**").hasRole("USER")
                
                // Booking pages - only USER role
                .requestMatchers("/booking/**").hasRole("USER")
                
                // All other requests need authentication
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler((request, response, authentication) -> {
                    // Redirect based on role
                    String redirectUrl = "/";
                    System.out.println("✅ Login successful for: " + authentication.getName());
                    System.out.println("✅ Authorities: " + authentication.getAuthorities());
                    
                    if (authentication.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                        redirectUrl = "/admin";
                        System.out.println("✅ Redirecting to: " + redirectUrl);
                    } else if (authentication.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_MANAGER"))) {
                        redirectUrl = "/manager";
                        System.out.println("✅ Redirecting to: " + redirectUrl);
                    } else if (authentication.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                        redirectUrl = "/user";
                        System.out.println("✅ Redirecting to: " + redirectUrl);
                    }
                    response.sendRedirect(redirectUrl);
                })
                .failureHandler((request, response, exception) -> {
                    System.out.println("❌ Login failed: " + exception.getMessage());
                    response.sendRedirect("/login?error=true");
                })
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/access-denied")
            );

        return http.build();
    }
}

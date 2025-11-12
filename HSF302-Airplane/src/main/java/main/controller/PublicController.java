package main.controller;

import main.enumerators.RoleEnum;
import main.pojo.Airport;
import main.pojo.Flight;
import main.pojo.Role;
import main.pojo.User;
import main.repository.RoleRepository;
import main.service.category.AirportService;
import main.service.produce.FlightService;
import main.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class PublicController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String home(Model model, Authentication authentication) {
        List<Airport> airports = airportService.getAllAirports();
        model.addAttribute("airports", airports);
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
        }
        return "public/home";
    }

    @GetMapping("/search")
    public String searchFlights(
            @RequestParam(required = false) String departure,
            @RequestParam(required = false) String arrival,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model,
            Authentication authentication) {
        
        List<Airport> airports = airportService.getAllAirports();
        model.addAttribute("airports", airports);
        
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
        }
        
        List<Flight> flights;
        // Nếu có tham số tìm kiếm, lọc theo điều kiện
        if (departure != null && arrival != null && date != null) {
            flights = flightService.searchFlights(departure, arrival, date);
            model.addAttribute("searchPerformed", true);
        } else {
            // Hiển thị tất cả chuyến bay nếu không có bộ lọc
            flights = flightService.getAllFlights();
        }
        
        model.addAttribute("flights", flights);
        
        return "public/search";
    }

    @GetMapping("/flight/{id}")
    public String flightDetail(@PathVariable Integer id, Model model, Authentication authentication) {
        Flight flight = flightService.getFlightById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        model.addAttribute("flight", flight);
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
        }
        return "public/flight-detail";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            RedirectAttributes redirectAttributes) {
        
        try {
            // Kiểm tra mật khẩu khớp
            if (!password.equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("error", "Mật khẩu xác nhận không khớp!");
                return "redirect:/register";
            }
            
            // Kiểm tra email đã tồn tại
            if (userService.existsByEmail(email)) {
                redirectAttributes.addFlashAttribute("error", "Email đã được sử dụng!");
                return "redirect:/register";
            }
            
            // Lấy role USER (mặc định cho người đăng ký)
            Role userRole = roleRepository.findByRoleName(RoleEnum.USER)
                    .orElseThrow(() -> new RuntimeException("Role USER không tồn tại trong hệ thống"));
            
            // Tạo user mới
            User newUser = new User();
            newUser.setFullName(fullName);
            newUser.setEmail(email);
            newUser.setPhone(phone);
            newUser.setPassword(password); // Lưu plain text như trong UserServiceImpl
            newUser.setRole(userRole);
            
            // Lưu user
            userService.createUser(newUser);
            
            redirectAttributes.addFlashAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "redirect:/login";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }
}

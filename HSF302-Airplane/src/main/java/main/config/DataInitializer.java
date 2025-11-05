package main.config;

import main.enumerators.FlightStatus;
import main.enumerators.RoleEnum;
import main.pojo.*;
import main.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Initialize Roles
        initializeRoles();
        
        // Initialize Users
        initializeUsers();
        
        // Initialize Airports
        initializeAirports();
        
        // Initialize Flights
        initializeFlights();
        
        // Initialize Seats
        initializeSeats();
    }

    private void initializeRoles() {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(RoleEnum.USER));
            roleRepository.save(new Role(RoleEnum.MANAGER));
            roleRepository.save(new Role(RoleEnum.ADMIN));
            System.out.println("✅ Roles initialized");
        }
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findByRoleName(RoleEnum.ADMIN).orElseThrow();
            Role managerRole = roleRepository.findByRoleName(RoleEnum.MANAGER).orElseThrow();
            Role userRole = roleRepository.findByRoleName(RoleEnum.USER).orElseThrow();

            // Create Admin
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setPassword("admin");
            admin.setFullName("Admin Nguyen");
            admin.setPhone("0901111111");
            admin.setRole(adminRole);
            userRepository.save(admin);

            // Create Manager 1
            User manager1 = new User();
            manager1.setEmail("manager1@gmail.com");
            manager1.setPassword("manager1");
            manager1.setFullName("Manager Tran");
            manager1.setPhone("0902222221");
            manager1.setRole(managerRole);
            userRepository.save(manager1);

            // Create Manager 2
            User manager2 = new User();
            manager2.setEmail("manager2@gmail.com");
            manager2.setPassword("manager2");
            manager2.setFullName("Manager Le");
            manager2.setPhone("0902222222");
            manager2.setRole(managerRole);
            userRepository.save(manager2);

            // Create User 1
            User user1 = new User();
            user1.setEmail("user1@gmail.com");
            user1.setPassword("user1");
            user1.setFullName("User Pham");
            user1.setPhone("0903333331");
            user1.setRole(userRole);
            userRepository.save(user1);

            // Create User 2
            User user2 = new User();
            user2.setEmail("user2@gmail.com");
            user2.setPassword("user2");
            user2.setFullName("User Hoang");
            user2.setPhone("0903333332");
            user2.setRole(userRole);
            userRepository.save(user2);

            // Create User 3
            User user3 = new User();
            user3.setEmail("user3@gmail.com");
            user3.setPassword("user3");
            user3.setFullName("User Vo");
            user3.setPhone("0903333333");
            user3.setRole(userRole);
            userRepository.save(user3);

            System.out.println("✅ Users initialized");
            System.out.println("📧 Admin: admin@gmail.com / admin");
            System.out.println("📧 Manager: manager1@gmail.com / manager1, manager2@gmail.com / manager2");
            System.out.println("📧 User: user1@gmail.com / user1, user2@gmail.com / user2, user3@gmail.com / user3");
        }
    }

    private void initializeAirports() {
        if (airportRepository.count() == 0) {
            airportRepository.save(new Airport("HAN", "Sân bay Quốc tế Nội Bài", "Hà Nội", "Việt Nam"));
            airportRepository.save(new Airport("SGN", "Sân bay Quốc tế Tân Sơn Nhất", "TP. Hồ Chí Minh", "Việt Nam"));
            airportRepository.save(new Airport("DAD", "Sân bay Quốc tế Đà Nẵng", "Đà Nẵng", "Việt Nam"));
            airportRepository.save(new Airport("CXR", "Sân bay Quốc tế Cam Ranh", "Nha Trang", "Việt Nam"));
            airportRepository.save(new Airport("PQC", "Sân bay Quốc tế Phú Quốc", "Phú Quốc", "Việt Nam"));
            System.out.println("✅ Airports initialized: 5 airports");
        }
    }

    private void initializeFlights() {
        if (flightRepository.count() == 0) {
            LocalDateTime baseDate = LocalDateTime.now().plusDays(1);

            // Chuyến bay HAN - SGN
            flightRepository.save(new Flight("HAN", "SGN", 
                baseDate.withHour(6).withMinute(0), 
                baseDate.withHour(8).withMinute(15), 
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("HAN", "SGN", 
                baseDate.withHour(14).withMinute(0), 
                baseDate.withHour(16).withMinute(15), 
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));

            // Chuyến bay SGN - HAN
            flightRepository.save(new Flight("SGN", "HAN", 
                baseDate.withHour(7).withMinute(0), 
                baseDate.withHour(9).withMinute(15), 
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("SGN", "HAN", 
                baseDate.withHour(20).withMinute(0), 
                baseDate.withHour(22).withMinute(15), 
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));

            // Chuyến bay HAN - DAD
            flightRepository.save(new Flight("HAN", "DAD", 
                baseDate.plusDays(1).withHour(9).withMinute(0), 
                baseDate.plusDays(1).withHour(10).withMinute(30), 
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));

            // Chuyến bay DAD - HAN
            flightRepository.save(new Flight("DAD", "HAN", 
                baseDate.plusDays(1).withHour(19).withMinute(0), 
                baseDate.plusDays(1).withHour(20).withMinute(30), 
                new BigDecimal("1700000"), FlightStatus.SCHEDULED));

            // Chuyến bay SGN - DAD
            flightRepository.save(new Flight("SGN", "DAD", 
                baseDate.plusDays(2).withHour(8).withMinute(0), 
                baseDate.plusDays(2).withHour(9).withMinute(30), 
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            // Chuyến bay DAD - SGN
            flightRepository.save(new Flight("DAD", "SGN", 
                baseDate.plusDays(2).withHour(15).withMinute(0), 
                baseDate.plusDays(2).withHour(16).withMinute(30), 
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));

            // Chuyến bay SGN - PQC
            flightRepository.save(new Flight("SGN", "PQC", 
                baseDate.plusDays(3).withHour(7).withMinute(30), 
                baseDate.plusDays(3).withHour(8).withMinute(30), 
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));
            
            // Chuyến bay PQC - SGN
            flightRepository.save(new Flight("PQC", "SGN", 
                baseDate.plusDays(3).withHour(17).withMinute(30), 
                baseDate.plusDays(3).withHour(18).withMinute(30), 
                new BigDecimal("1700000"), FlightStatus.SCHEDULED));

            // Chuyến bay HAN - CXR
            flightRepository.save(new Flight("HAN", "CXR", 
                baseDate.plusDays(4).withHour(10).withMinute(0), 
                baseDate.plusDays(4).withHour(12).withMinute(0), 
                new BigDecimal("2200000"), FlightStatus.SCHEDULED));

            // Chuyến bay CXR - HAN
            flightRepository.save(new Flight("CXR", "HAN", 
                baseDate.plusDays(4).withHour(18).withMinute(0), 
                baseDate.plusDays(4).withHour(20).withMinute(0), 
                new BigDecimal("2200000"), FlightStatus.SCHEDULED));

            System.out.println("✅ Flights initialized: 12 flights");
        }
    }

    private void initializeSeats() {
        if (seatRepository.count() == 0) {
            // Ghế hạng Business - Hàng 1
            seatRepository.save(new Seat("1A"));
            seatRepository.save(new Seat("1B"));
            seatRepository.save(new Seat("1C"));
            seatRepository.save(new Seat("1D"));
            
            // Ghế hạng Business - Hàng 2
            seatRepository.save(new Seat("2A"));
            seatRepository.save(new Seat("2B"));
            seatRepository.save(new Seat("2C"));
            seatRepository.save(new Seat("2D"));

            // Ghế hạng Business - Hàng 3
            seatRepository.save(new Seat("3A"));
            seatRepository.save(new Seat("3B"));
            seatRepository.save(new Seat("3C"));
            seatRepository.save(new Seat("3D"));

            // Ghế hạng Economy - Hàng 10
            seatRepository.save(new Seat("10A"));
            seatRepository.save(new Seat("10B"));
            seatRepository.save(new Seat("10C"));
            seatRepository.save(new Seat("10D"));
            seatRepository.save(new Seat("10E"));
            seatRepository.save(new Seat("10F"));

            // Ghế hạng Economy - Hàng 11
            seatRepository.save(new Seat("11A"));
            seatRepository.save(new Seat("11B"));
            seatRepository.save(new Seat("11C"));
            seatRepository.save(new Seat("11D"));
            seatRepository.save(new Seat("11E"));
            seatRepository.save(new Seat("11F"));

            // Ghế hạng Economy - Hàng 12
            seatRepository.save(new Seat("12A"));
            seatRepository.save(new Seat("12B"));
            seatRepository.save(new Seat("12C"));
            seatRepository.save(new Seat("12D"));
            seatRepository.save(new Seat("12E"));
            seatRepository.save(new Seat("12F"));

            // Ghế hạng Economy - Hàng 13
            seatRepository.save(new Seat("13A"));
            seatRepository.save(new Seat("13B"));
            seatRepository.save(new Seat("13C"));
            seatRepository.save(new Seat("13D"));
            seatRepository.save(new Seat("13E"));
            seatRepository.save(new Seat("13F"));

            // Ghế hạng Economy - Hàng 14
            seatRepository.save(new Seat("14A"));
            seatRepository.save(new Seat("14B"));
            seatRepository.save(new Seat("14C"));
            seatRepository.save(new Seat("14D"));
            seatRepository.save(new Seat("14E"));
            seatRepository.save(new Seat("14F"));

            // Ghế hạng Economy - Hàng 15
            seatRepository.save(new Seat("15A"));
            seatRepository.save(new Seat("15B"));
            seatRepository.save(new Seat("15C"));
            seatRepository.save(new Seat("15D"));
            seatRepository.save(new Seat("15E"));
            seatRepository.save(new Seat("15F"));

            System.out.println("✅ Seats initialized: 48 seats (12 Business + 36 Economy)");
        }
    }
}

package main.config;

import main.enumerators.FlightStatus;
import main.enumerators.RoleEnum;
import main.enumerators.SeatStatus;
import main.enumerators.TicketClass;
import main.pojo.*;
import main.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    private FlightSeatRepository flightSeatRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private int flightNumberCounter = 100;

    private String generateFlightNumber() {
        return "VN" + (flightNumberCounter++);
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize Roles
        initializeRoles();
        
        // Initialize Users
        initializeUsers();
        
        // Initialize Airports
        initializeAirports();
        
        // Initialize Seats
        initializeSeats();
        
        // Initialize Flights
        initializeFlights();
        
        // Initialize Flight Seats
        initializeFlightSeats();
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
            airportRepository.save(new Airport("HAN", "Sân bay Quốc tế Nội Bài", "Hà Nội (HAN)", "Việt Nam"));
            airportRepository.save(new Airport("SGN", "Sân bay Quốc tế Tân Sơn Nhất", "TP Hồ Chí Minh (SGN)", "Việt Nam"));
            airportRepository.save(new Airport("DAD", "Sân bay Quốc tế Đà Nẵng", "Đà Nẵng (DAD)", "Việt Nam"));
            airportRepository.save(new Airport("CXR", "Sân bay Quốc tế Cam Ranh", "Nha Trang (CXR)", "Việt Nam"));
            airportRepository.save(new Airport("PQC", "Sân bay Quốc tế Phú Quốc", "Phú Quốc (PQC)", "Việt Nam"));
            System.out.println("✅ Airports initialized: 5 airports");
        }
    }

    private void initializeFlights() {
        if (flightRepository.count() == 0) {
            // Chuyến bay từ ngày 10/11 đến 30/11/2025
            
            // ==================== 2025-11-10 ====================
            flightRepository.save(new Flight("VN100", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 10, 6, 0),
                LocalDateTime.of(2025, 11, 10, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN101", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 10, 14, 0),
                LocalDateTime.of(2025, 11, 10, 16, 15),
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN102", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 10, 7, 0),
                LocalDateTime.of(2025, 11, 10, 9, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN103", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 10, 16, 30),
                LocalDateTime.of(2025, 11, 10, 18, 45),
                new BigDecimal("2650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN104", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 10, 9, 0),
                LocalDateTime.of(2025, 11, 10, 10, 30),
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN105", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 10, 12, 30),
                LocalDateTime.of(2025, 11, 10, 14, 0),
                new BigDecimal("1750000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN106", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 10, 8, 0),
                LocalDateTime.of(2025, 11, 10, 9, 30),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN107", "DAD", "SGN",
                LocalDateTime.of(2025, 11, 10, 15, 0),
                LocalDateTime.of(2025, 11, 10, 16, 30),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-11 ====================
            flightRepository.save(new Flight("VN108", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 11, 6, 0),
                LocalDateTime.of(2025, 11, 11, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN109", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 11, 9, 30),
                LocalDateTime.of(2025, 11, 11, 11, 45),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN110", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 11, 18, 30),
                LocalDateTime.of(2025, 11, 11, 20, 45),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN111", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 11, 12, 0),
                LocalDateTime.of(2025, 11, 11, 14, 15),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN112", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 11, 20, 0),
                LocalDateTime.of(2025, 11, 11, 22, 15),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN113", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 11, 6, 30),
                LocalDateTime.of(2025, 11, 11, 8, 0),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN114", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 11, 15, 30),
                LocalDateTime.of(2025, 11, 11, 17, 0),
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN115", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 11, 11, 0),
                LocalDateTime.of(2025, 11, 11, 12, 30),
                new BigDecimal("1550000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN116", "DAD", "SGN",
                LocalDateTime.of(2025, 11, 11, 19, 30),
                LocalDateTime.of(2025, 11, 11, 21, 0),
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN117", "SGN", "PQC",
                LocalDateTime.of(2025, 11, 11, 7, 30),
                LocalDateTime.of(2025, 11, 11, 8, 30),
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-12 ====================
            flightRepository.save(new Flight("VN118", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 12, 6, 0),
                LocalDateTime.of(2025, 11, 12, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN119", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 12, 14, 0),
                LocalDateTime.of(2025, 11, 12, 16, 15),
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN120", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 12, 7, 0),
                LocalDateTime.of(2025, 11, 12, 9, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN121", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 12, 16, 30),
                LocalDateTime.of(2025, 11, 12, 18, 45),
                new BigDecimal("2650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN122", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 12, 13, 0),
                LocalDateTime.of(2025, 11, 12, 14, 30),
                new BigDecimal("1850000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN123", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 12, 19, 0),
                LocalDateTime.of(2025, 11, 12, 20, 30),
                new BigDecimal("1700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN124", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 12, 16, 0),
                LocalDateTime.of(2025, 11, 12, 17, 30),
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-13 ====================
            flightRepository.save(new Flight("VN125", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 13, 6, 0),
                LocalDateTime.of(2025, 11, 13, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN126", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 13, 9, 30),
                LocalDateTime.of(2025, 11, 13, 11, 45),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN127", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 13, 18, 30),
                LocalDateTime.of(2025, 11, 13, 20, 45),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN128", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 13, 7, 0),
                LocalDateTime.of(2025, 11, 13, 9, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN129", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 13, 12, 0),
                LocalDateTime.of(2025, 11, 13, 14, 15),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN130", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 13, 9, 0),
                LocalDateTime.of(2025, 11, 13, 10, 30),
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN131", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 13, 12, 30),
                LocalDateTime.of(2025, 11, 13, 14, 0),
                new BigDecimal("1750000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN132", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 13, 6, 0),
                LocalDateTime.of(2025, 11, 13, 7, 30),
                new BigDecimal("1400000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN133", "DAD", "SGN",
                LocalDateTime.of(2025, 11, 13, 9, 0),
                LocalDateTime.of(2025, 11, 13, 10, 30),
                new BigDecimal("1450000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-14 ====================
            flightRepository.save(new Flight("VN134", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 14, 6, 0),
                LocalDateTime.of(2025, 11, 14, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN135", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 14, 14, 0),
                LocalDateTime.of(2025, 11, 14, 16, 15),
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN136", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 14, 16, 30),
                LocalDateTime.of(2025, 11, 14, 18, 45),
                new BigDecimal("2650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN137", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 14, 20, 0),
                LocalDateTime.of(2025, 11, 14, 22, 15),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN138", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 14, 17, 0),
                LocalDateTime.of(2025, 11, 14, 18, 30),
                new BigDecimal("1750000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN139", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 14, 8, 30),
                LocalDateTime.of(2025, 11, 14, 10, 0),
                new BigDecimal("1650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN140", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 14, 8, 0),
                LocalDateTime.of(2025, 11, 14, 9, 30),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN141", "DAD", "SGN",
                LocalDateTime.of(2025, 11, 14, 16, 30),
                LocalDateTime.of(2025, 11, 14, 18, 0),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-15 ====================
            flightRepository.save(new Flight("VN142", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 15, 6, 0),
                LocalDateTime.of(2025, 11, 15, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN143", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 15, 14, 0),
                LocalDateTime.of(2025, 11, 15, 16, 15),
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN144", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 15, 7, 0),
                LocalDateTime.of(2025, 11, 15, 9, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN145", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 15, 16, 30),
                LocalDateTime.of(2025, 11, 15, 18, 45),
                new BigDecimal("2650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN146", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 15, 9, 0),
                LocalDateTime.of(2025, 11, 15, 10, 30),
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN147", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 15, 12, 30),
                LocalDateTime.of(2025, 11, 15, 14, 0),
                new BigDecimal("1750000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN148", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 15, 8, 0),
                LocalDateTime.of(2025, 11, 15, 9, 30),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN149", "DAD", "SGN",
                LocalDateTime.of(2025, 11, 15, 15, 0),
                LocalDateTime.of(2025, 11, 15, 16, 30),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-16 ====================
            flightRepository.save(new Flight("VN150", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 16, 6, 0),
                LocalDateTime.of(2025, 11, 16, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN151", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 16, 9, 30),
                LocalDateTime.of(2025, 11, 16, 11, 45),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN152", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 16, 18, 30),
                LocalDateTime.of(2025, 11, 16, 20, 45),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN153", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 16, 12, 0),
                LocalDateTime.of(2025, 11, 16, 14, 15),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN154", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 16, 20, 0),
                LocalDateTime.of(2025, 11, 16, 22, 15),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN155", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 16, 6, 30),
                LocalDateTime.of(2025, 11, 16, 8, 0),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN156", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 16, 15, 30),
                LocalDateTime.of(2025, 11, 16, 17, 0),
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN157", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 16, 11, 0),
                LocalDateTime.of(2025, 11, 16, 12, 30),
                new BigDecimal("1550000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN158", "DAD", "SGN",
                LocalDateTime.of(2025, 11, 16, 19, 30),
                LocalDateTime.of(2025, 11, 16, 21, 0),
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN159", "SGN", "PQC",
                LocalDateTime.of(2025, 11, 16, 7, 30),
                LocalDateTime.of(2025, 11, 16, 8, 30),
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-17 ====================
            flightRepository.save(new Flight("VN160", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 17, 6, 0),
                LocalDateTime.of(2025, 11, 17, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN161", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 17, 14, 0),
                LocalDateTime.of(2025, 11, 17, 16, 15),
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN162", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 17, 7, 0),
                LocalDateTime.of(2025, 11, 17, 9, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN163", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 17, 16, 30),
                LocalDateTime.of(2025, 11, 17, 18, 45),
                new BigDecimal("2650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN164", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 17, 13, 0),
                LocalDateTime.of(2025, 11, 17, 14, 30),
                new BigDecimal("1850000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN165", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 17, 19, 0),
                LocalDateTime.of(2025, 11, 17, 20, 30),
                new BigDecimal("1700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN166", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 17, 16, 0),
                LocalDateTime.of(2025, 11, 17, 17, 30),
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-18 ====================
            flightRepository.save(new Flight("VN167", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 18, 6, 0),
                LocalDateTime.of(2025, 11, 18, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN168", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 18, 9, 30),
                LocalDateTime.of(2025, 11, 18, 11, 45),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN169", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 18, 18, 30),
                LocalDateTime.of(2025, 11, 18, 20, 45),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN170", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 18, 7, 0),
                LocalDateTime.of(2025, 11, 18, 9, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN171", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 18, 12, 0),
                LocalDateTime.of(2025, 11, 18, 14, 15),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN172", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 18, 9, 0),
                LocalDateTime.of(2025, 11, 18, 10, 30),
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN173", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 18, 12, 30),
                LocalDateTime.of(2025, 11, 18, 14, 0),
                new BigDecimal("1750000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN174", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 18, 6, 0),
                LocalDateTime.of(2025, 11, 18, 7, 30),
                new BigDecimal("1400000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN175", "DAD", "SGN",
                LocalDateTime.of(2025, 11, 18, 9, 0),
                LocalDateTime.of(2025, 11, 18, 10, 30),
                new BigDecimal("1450000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-19 ====================
            flightRepository.save(new Flight("VN176", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 19, 6, 0),
                LocalDateTime.of(2025, 11, 19, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN177", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 19, 14, 0),
                LocalDateTime.of(2025, 11, 19, 16, 15),
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN178", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 19, 16, 30),
                LocalDateTime.of(2025, 11, 19, 18, 45),
                new BigDecimal("2650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN179", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 19, 20, 0),
                LocalDateTime.of(2025, 11, 19, 22, 15),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN180", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 19, 17, 0),
                LocalDateTime.of(2025, 11, 19, 18, 30),
                new BigDecimal("1750000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN181", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 19, 8, 30),
                LocalDateTime.of(2025, 11, 19, 10, 0),
                new BigDecimal("1650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN182", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 19, 8, 0),
                LocalDateTime.of(2025, 11, 19, 9, 30),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN183", "DAD", "SGN",
                LocalDateTime.of(2025, 11, 19, 16, 30),
                LocalDateTime.of(2025, 11, 19, 18, 0),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-20 ====================
            flightRepository.save(new Flight("VN184", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 20, 6, 0),
                LocalDateTime.of(2025, 11, 20, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN185", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 20, 9, 30),
                LocalDateTime.of(2025, 11, 20, 11, 45),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN186", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 20, 18, 30),
                LocalDateTime.of(2025, 11, 20, 20, 45),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN187", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 20, 7, 0),
                LocalDateTime.of(2025, 11, 20, 9, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN188", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 20, 12, 0),
                LocalDateTime.of(2025, 11, 20, 14, 15),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN189", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 20, 6, 30),
                LocalDateTime.of(2025, 11, 20, 8, 0),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN190", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 20, 13, 0),
                LocalDateTime.of(2025, 11, 20, 14, 30),
                new BigDecimal("1850000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN191", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 20, 15, 30),
                LocalDateTime.of(2025, 11, 20, 17, 0),
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN192", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 20, 11, 0),
                LocalDateTime.of(2025, 11, 20, 12, 30),
                new BigDecimal("1550000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN193", "PQC", "SGN",
                LocalDateTime.of(2025, 11, 20, 15, 0),
                LocalDateTime.of(2025, 11, 20, 16, 0),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-21 ====================
            flightRepository.save(new Flight("VN194", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 21, 6, 0),
                LocalDateTime.of(2025, 11, 21, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN195", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 21, 14, 0),
                LocalDateTime.of(2025, 11, 21, 16, 15),
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN196", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 21, 16, 30),
                LocalDateTime.of(2025, 11, 21, 18, 45),
                new BigDecimal("2650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN197", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 21, 20, 0),
                LocalDateTime.of(2025, 11, 21, 22, 15),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN198", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 21, 9, 0),
                LocalDateTime.of(2025, 11, 21, 10, 30),
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN199", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 21, 19, 0),
                LocalDateTime.of(2025, 11, 21, 20, 30),
                new BigDecimal("1700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN200", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 21, 16, 0),
                LocalDateTime.of(2025, 11, 21, 17, 30),
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-22 ====================
            flightRepository.save(new Flight("VN201", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 22, 6, 0),
                LocalDateTime.of(2025, 11, 22, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN202", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 22, 9, 30),
                LocalDateTime.of(2025, 11, 22, 11, 45),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN203", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 22, 18, 30),
                LocalDateTime.of(2025, 11, 22, 20, 45),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN204", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 22, 7, 0),
                LocalDateTime.of(2025, 11, 22, 9, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN205", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 22, 12, 0),
                LocalDateTime.of(2025, 11, 22, 14, 15),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN206", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 22, 17, 0),
                LocalDateTime.of(2025, 11, 22, 18, 30),
                new BigDecimal("1750000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN207", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 22, 12, 30),
                LocalDateTime.of(2025, 11, 22, 14, 0),
                new BigDecimal("1750000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN208", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 22, 8, 0),
                LocalDateTime.of(2025, 11, 22, 9, 30),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN209", "DAD", "SGN",
                LocalDateTime.of(2025, 11, 22, 12, 0),
                LocalDateTime.of(2025, 11, 22, 13, 30),
                new BigDecimal("1550000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-23 ====================
            flightRepository.save(new Flight("VN210", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 23, 6, 0),
                LocalDateTime.of(2025, 11, 23, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN211", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 23, 14, 0),
                LocalDateTime.of(2025, 11, 23, 16, 15),
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN212", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 23, 7, 0),
                LocalDateTime.of(2025, 11, 23, 9, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN213", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 23, 16, 30),
                LocalDateTime.of(2025, 11, 23, 18, 45),
                new BigDecimal("2650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN214", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 23, 13, 0),
                LocalDateTime.of(2025, 11, 23, 14, 30),
                new BigDecimal("1850000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN215", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 23, 8, 30),
                LocalDateTime.of(2025, 11, 23, 10, 0),
                new BigDecimal("1650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN216", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 23, 6, 0),
                LocalDateTime.of(2025, 11, 23, 7, 30),
                new BigDecimal("1400000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN217", "DAD", "SGN",
                LocalDateTime.of(2025, 11, 23, 21, 0),
                LocalDateTime.of(2025, 11, 23, 22, 30),
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-24 ====================
            flightRepository.save(new Flight("VN218", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 24, 6, 0),
                LocalDateTime.of(2025, 11, 24, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN219", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 24, 9, 30),
                LocalDateTime.of(2025, 11, 24, 11, 45),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN220", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 24, 18, 30),
                LocalDateTime.of(2025, 11, 24, 20, 45),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN221", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 24, 12, 0),
                LocalDateTime.of(2025, 11, 24, 14, 15),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN222", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 24, 20, 0),
                LocalDateTime.of(2025, 11, 24, 22, 15),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN223", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 24, 6, 30),
                LocalDateTime.of(2025, 11, 24, 8, 0),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN224", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 24, 15, 30),
                LocalDateTime.of(2025, 11, 24, 17, 0),
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN225", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 24, 11, 0),
                LocalDateTime.of(2025, 11, 24, 12, 30),
                new BigDecimal("1550000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN226", "DAD", "SGN",
                LocalDateTime.of(2025, 11, 24, 19, 30),
                LocalDateTime.of(2025, 11, 24, 21, 0),
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN227", "HAN", "CXR",
                LocalDateTime.of(2025, 11, 24, 10, 0),
                LocalDateTime.of(2025, 11, 24, 12, 0),
                new BigDecimal("2200000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-25 ====================
            flightRepository.save(new Flight("VN228", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 25, 6, 0),
                LocalDateTime.of(2025, 11, 25, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN229", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 25, 14, 0),
                LocalDateTime.of(2025, 11, 25, 16, 15),
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN230", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 25, 7, 0),
                LocalDateTime.of(2025, 11, 25, 9, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN231", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 25, 16, 30),
                LocalDateTime.of(2025, 11, 25, 18, 45),
                new BigDecimal("2650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN232", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 25, 9, 0),
                LocalDateTime.of(2025, 11, 25, 10, 30),
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN233", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 25, 19, 0),
                LocalDateTime.of(2025, 11, 25, 20, 30),
                new BigDecimal("1700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN234", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 25, 16, 0),
                LocalDateTime.of(2025, 11, 25, 17, 30),
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-26 ====================
            flightRepository.save(new Flight("VN235", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 26, 6, 0),
                LocalDateTime.of(2025, 11, 26, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN236", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 26, 9, 30),
                LocalDateTime.of(2025, 11, 26, 11, 45),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN237", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 26, 18, 30),
                LocalDateTime.of(2025, 11, 26, 20, 45),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN238", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 26, 7, 0),
                LocalDateTime.of(2025, 11, 26, 9, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN239", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 26, 12, 0),
                LocalDateTime.of(2025, 11, 26, 14, 15),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN240", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 26, 13, 0),
                LocalDateTime.of(2025, 11, 26, 14, 30),
                new BigDecimal("1850000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN241", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 26, 12, 30),
                LocalDateTime.of(2025, 11, 26, 14, 0),
                new BigDecimal("1750000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN242", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 26, 8, 0),
                LocalDateTime.of(2025, 11, 26, 9, 30),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN243", "DAD", "SGN",
                LocalDateTime.of(2025, 11, 26, 15, 0),
                LocalDateTime.of(2025, 11, 26, 16, 30),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-27 ====================
            flightRepository.save(new Flight("VN244", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 27, 6, 0),
                LocalDateTime.of(2025, 11, 27, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN245", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 27, 14, 0),
                LocalDateTime.of(2025, 11, 27, 16, 15),
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN246", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 27, 16, 30),
                LocalDateTime.of(2025, 11, 27, 18, 45),
                new BigDecimal("2650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN247", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 27, 20, 0),
                LocalDateTime.of(2025, 11, 27, 22, 15),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN248", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 27, 17, 0),
                LocalDateTime.of(2025, 11, 27, 18, 30),
                new BigDecimal("1750000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN249", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 27, 8, 30),
                LocalDateTime.of(2025, 11, 27, 10, 0),
                new BigDecimal("1650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN250", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 27, 6, 0),
                LocalDateTime.of(2025, 11, 27, 7, 30),
                new BigDecimal("1400000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN251", "DAD", "SGN",
                LocalDateTime.of(2025, 11, 27, 9, 0),
                LocalDateTime.of(2025, 11, 27, 10, 30),
                new BigDecimal("1450000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-28 ====================
            flightRepository.save(new Flight("VN252", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 28, 6, 0),
                LocalDateTime.of(2025, 11, 28, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN253", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 28, 9, 30),
                LocalDateTime.of(2025, 11, 28, 11, 45),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN254", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 28, 18, 30),
                LocalDateTime.of(2025, 11, 28, 20, 45),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN255", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 28, 7, 0),
                LocalDateTime.of(2025, 11, 28, 9, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN256", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 28, 12, 0),
                LocalDateTime.of(2025, 11, 28, 14, 15),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN257", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 28, 6, 30),
                LocalDateTime.of(2025, 11, 28, 8, 0),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN258", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 28, 15, 30),
                LocalDateTime.of(2025, 11, 28, 17, 0),
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN259", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 28, 11, 0),
                LocalDateTime.of(2025, 11, 28, 12, 30),
                new BigDecimal("1550000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN260", "DAD", "SGN",
                LocalDateTime.of(2025, 11, 28, 16, 30),
                LocalDateTime.of(2025, 11, 28, 18, 0),
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN261", "SGN", "CXR",
                LocalDateTime.of(2025, 11, 28, 10, 0),
                LocalDateTime.of(2025, 11, 28, 11, 0),
                new BigDecimal("1400000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-29 ====================
            flightRepository.save(new Flight("VN262", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 29, 6, 0),
                LocalDateTime.of(2025, 11, 29, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN263", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 29, 14, 0),
                LocalDateTime.of(2025, 11, 29, 16, 15),
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN264", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 29, 7, 0),
                LocalDateTime.of(2025, 11, 29, 9, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN265", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 29, 16, 30),
                LocalDateTime.of(2025, 11, 29, 18, 45),
                new BigDecimal("2650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN266", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 29, 9, 0),
                LocalDateTime.of(2025, 11, 29, 10, 30),
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN267", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 29, 19, 0),
                LocalDateTime.of(2025, 11, 29, 20, 30),
                new BigDecimal("1700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN268", "SGN", "DAD",
                LocalDateTime.of(2025, 11, 29, 16, 0),
                LocalDateTime.of(2025, 11, 29, 17, 30),
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));

            // ==================== 2025-11-30 ====================
            flightRepository.save(new Flight("VN269", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 30, 6, 0),
                LocalDateTime.of(2025, 11, 30, 8, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN270", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 30, 9, 30),
                LocalDateTime.of(2025, 11, 30, 11, 45),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN271", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 30, 14, 0),
                LocalDateTime.of(2025, 11, 30, 16, 15),
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN272", "SGN", "HAN",
                LocalDateTime.of(2025, 11, 30, 18, 30),
                LocalDateTime.of(2025, 11, 30, 20, 45),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN273", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 30, 7, 0),
                LocalDateTime.of(2025, 11, 30, 9, 15),
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN274", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 30, 12, 0),
                LocalDateTime.of(2025, 11, 30, 14, 15),
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN275", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 30, 16, 30),
                LocalDateTime.of(2025, 11, 30, 18, 45),
                new BigDecimal("2650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN276", "HAN", "SGN",
                LocalDateTime.of(2025, 11, 30, 20, 0),
                LocalDateTime.of(2025, 11, 30, 22, 15),
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN277", "HAN", "DAD",
                LocalDateTime.of(2025, 11, 30, 13, 0),
                LocalDateTime.of(2025, 11, 30, 14, 30),
                new BigDecimal("1850000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("VN278", "DAD", "HAN",
                LocalDateTime.of(2025, 11, 30, 12, 30),
                LocalDateTime.of(2025, 11, 30, 14, 0),
                new BigDecimal("1750000"), FlightStatus.SCHEDULED));

            long totalFlights = flightRepository.count();
            System.out.println("✅ Flights initialized: " + totalFlights + " flights (10/11 - 30/11/2025)");
        }
    }

    private void initializeSeats() {
        if (seatRepository.count() == 0) {
            // Ghế hạng Business - 3 hàng đầu tiên (Hàng 1-3) - 12 ghế
            // Hàng 1
            seatRepository.save(new Seat("1A"));
            seatRepository.save(new Seat("1B"));
            seatRepository.save(new Seat("1C"));
            seatRepository.save(new Seat("1D"));
            
            // Hàng 2
            seatRepository.save(new Seat("2A"));
            seatRepository.save(new Seat("2B"));
            seatRepository.save(new Seat("2C"));
            seatRepository.save(new Seat("2D"));

            // Hàng 3
            seatRepository.save(new Seat("3A"));
            seatRepository.save(new Seat("3B"));
            seatRepository.save(new Seat("3C"));
            seatRepository.save(new Seat("3D"));

            // Ghế hạng Premium Economy - 5 hàng tiếp theo (Hàng 4-8) - 30 ghế
            // Hàng 4
            seatRepository.save(new Seat("4A"));
            seatRepository.save(new Seat("4B"));
            seatRepository.save(new Seat("4C"));
            seatRepository.save(new Seat("4D"));
            seatRepository.save(new Seat("4E"));
            seatRepository.save(new Seat("4F"));

            // Hàng 5
            seatRepository.save(new Seat("5A"));
            seatRepository.save(new Seat("5B"));
            seatRepository.save(new Seat("5C"));
            seatRepository.save(new Seat("5D"));
            seatRepository.save(new Seat("5E"));
            seatRepository.save(new Seat("5F"));

            // Hàng 6
            seatRepository.save(new Seat("6A"));
            seatRepository.save(new Seat("6B"));
            seatRepository.save(new Seat("6C"));
            seatRepository.save(new Seat("6D"));
            seatRepository.save(new Seat("6E"));
            seatRepository.save(new Seat("6F"));

            // Hàng 7
            seatRepository.save(new Seat("7A"));
            seatRepository.save(new Seat("7B"));
            seatRepository.save(new Seat("7C"));
            seatRepository.save(new Seat("7D"));
            seatRepository.save(new Seat("7E"));
            seatRepository.save(new Seat("7F"));

            // Hàng 8
            seatRepository.save(new Seat("8A"));
            seatRepository.save(new Seat("8B"));
            seatRepository.save(new Seat("8C"));
            seatRepository.save(new Seat("8D"));
            seatRepository.save(new Seat("8E"));
            seatRepository.save(new Seat("8F"));

            // Ghế hạng Economy - Còn lại (Hàng 9-15) - 42 ghế
            // Hàng 9
            seatRepository.save(new Seat("9A"));
            seatRepository.save(new Seat("9B"));
            seatRepository.save(new Seat("9C"));
            seatRepository.save(new Seat("9D"));
            seatRepository.save(new Seat("9E"));
            seatRepository.save(new Seat("9F"));

            // Hàng 10
            seatRepository.save(new Seat("10A"));
            seatRepository.save(new Seat("10B"));
            seatRepository.save(new Seat("10C"));
            seatRepository.save(new Seat("10D"));
            seatRepository.save(new Seat("10E"));
            seatRepository.save(new Seat("10F"));

            // Hàng 11
            seatRepository.save(new Seat("11A"));
            seatRepository.save(new Seat("11B"));
            seatRepository.save(new Seat("11C"));
            seatRepository.save(new Seat("11D"));
            seatRepository.save(new Seat("11E"));
            seatRepository.save(new Seat("11F"));

            // Hàng 12
            seatRepository.save(new Seat("12A"));
            seatRepository.save(new Seat("12B"));
            seatRepository.save(new Seat("12C"));
            seatRepository.save(new Seat("12D"));
            seatRepository.save(new Seat("12E"));
            seatRepository.save(new Seat("12F"));

            // Hàng 13
            seatRepository.save(new Seat("13A"));
            seatRepository.save(new Seat("13B"));
            seatRepository.save(new Seat("13C"));
            seatRepository.save(new Seat("13D"));
            seatRepository.save(new Seat("13E"));
            seatRepository.save(new Seat("13F"));

            // Hàng 14
            seatRepository.save(new Seat("14A"));
            seatRepository.save(new Seat("14B"));
            seatRepository.save(new Seat("14C"));
            seatRepository.save(new Seat("14D"));
            seatRepository.save(new Seat("14E"));
            seatRepository.save(new Seat("14F"));

            // Hàng 15
            seatRepository.save(new Seat("15A"));
            seatRepository.save(new Seat("15B"));
            seatRepository.save(new Seat("15C"));
            seatRepository.save(new Seat("15D"));
            seatRepository.save(new Seat("15E"));
            seatRepository.save(new Seat("15F"));

            System.out.println("✅ Seats initialized: 84 seats (12 Business + 30 Premium Economy + 42 Economy)");
            System.out.println("   - Hàng 1-3: Thương gia (Business Class) - 12 ghế");
            System.out.println("   - Hàng 4-8: Phổ thông Đặc biệt (Premium Economy) - 30 ghế");
            System.out.println("   - Hàng 9-15: Phổ thông (Economy) - 42 ghế");
        }
    }
    
    private void initializeFlightSeats() {
        if (flightSeatRepository.count() == 0) {
            List<Flight> flights = flightRepository.findAll();
            List<Seat> seats = seatRepository.findAll();
            
            int flightCount = 0;
            int totalFlightSeats = 0;
            
            for (Flight flight : flights) {
                flightCount++;
                BigDecimal basePrice = flight.getPrice();
                
                // Một số chuyến bay có nhiều hạng vé (đánh dấu bằng dấu sao)
                // Các chuyến bay dài (HAN-SGN, HAN-PQC, HAN-CXR) sẽ có nhiều hạng vé
                boolean hasMultipleClasses = false;
                
                // Chuyến bay có giá >= 2.5 triệu sẽ có nhiều hạng vé
                if (basePrice.compareTo(new BigDecimal("2500000")) >= 0) {
                    hasMultipleClasses = true;
                }
                
                flight.setHasMultipleClasses(hasMultipleClasses);
                flightRepository.save(flight);
                
                // Tạo ghế cho chuyến bay này
                if (hasMultipleClasses) {
                    // Chuyến bay có nhiều hạng vé: Business, Premium Economy, Economy
                    
                    // Business Class: 12 ghế đầu tiên (hàng 1-3) - giá cao nhất
                    for (int i = 0; i < 12; i++) {
                        Seat seat = seats.get(i);
                        BigDecimal businessPrice = basePrice.multiply(new BigDecimal(TicketClass.BUSINESS.getPriceMultiplier()));
                        FlightSeat flightSeat = new FlightSeat(
                            flight, seat, TicketClass.BUSINESS, seat.getSeatNumber(), 
                            SeatStatus.AVAILABLE, businessPrice
                        );
                        flightSeatRepository.save(flightSeat);
                        totalFlightSeats++;
                    }
                    
                    // Premium Economy: 30 ghế tiếp theo (hàng 4-8) - giá trung bình
                    for (int i = 12; i < 42; i++) {
                        Seat seat = seats.get(i);
                        BigDecimal premiumPrice = basePrice.multiply(new BigDecimal(TicketClass.PREMIUM_ECONOMY.getPriceMultiplier()));
                        FlightSeat flightSeat = new FlightSeat(
                            flight, seat, TicketClass.PREMIUM_ECONOMY, seat.getSeatNumber(), 
                            SeatStatus.AVAILABLE, premiumPrice
                        );
                        flightSeatRepository.save(flightSeat);
                        totalFlightSeats++;
                    }
                    
                    // Economy: 42 ghế còn lại (hàng 9-15) - giá cơ bản
                    for (int i = 42; i < 84; i++) {
                        Seat seat = seats.get(i);
                        FlightSeat flightSeat = new FlightSeat(
                            flight, seat, TicketClass.ECONOMY, seat.getSeatNumber(), 
                            SeatStatus.AVAILABLE, basePrice
                        );
                        flightSeatRepository.save(flightSeat);
                        totalFlightSeats++;
                    }
                } else {
                    // Chuyến bay chỉ có Economy
                    for (Seat seat : seats) {
                        FlightSeat flightSeat = new FlightSeat(
                            flight, seat, TicketClass.ECONOMY, seat.getSeatNumber(), 
                            SeatStatus.AVAILABLE, basePrice
                        );
                        flightSeatRepository.save(flightSeat);
                        totalFlightSeats++;
                    }
                }
            }
            
            System.out.println("✅ Flight Seats initialized: " + totalFlightSeats + " seats for " + flightCount + " flights");
            System.out.println("⭐ Flights with multiple ticket classes are marked with a star (*)");
            System.out.println("💼 Business Class (Thương gia) - Most expensive");
            System.out.println("✈️  Premium Economy (Phổ thông Đặc biệt) - Medium price");
            System.out.println("🪑 Economy (Phổ thông) - Basic price");
        }
    }
}

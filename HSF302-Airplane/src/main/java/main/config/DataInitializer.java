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
            LocalDateTime baseDate = LocalDateTime.now().plusDays(1);

            // ========== HAN - SGN (Hà Nội - TP. Hồ Chí Minh) ==========
            // Ngày 1
            flightRepository.save(new Flight("HAN", "SGN", 
                baseDate.withHour(6).withMinute(0), 
                baseDate.withHour(8).withMinute(15), 
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("HAN", "SGN", 
                baseDate.withHour(9).withMinute(30), 
                baseDate.withHour(11).withMinute(45), 
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("HAN", "SGN", 
                baseDate.withHour(14).withMinute(0), 
                baseDate.withHour(16).withMinute(15), 
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("HAN", "SGN", 
                baseDate.withHour(18).withMinute(30), 
                baseDate.withHour(20).withMinute(45), 
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));

            // ========== SGN - HAN (TP. Hồ Chí Minh - Hà Nội) ==========
            flightRepository.save(new Flight("SGN", "HAN", 
                baseDate.withHour(7).withMinute(0), 
                baseDate.withHour(9).withMinute(15), 
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("SGN", "HAN", 
                baseDate.withHour(12).withMinute(0), 
                baseDate.withHour(14).withMinute(15), 
                new BigDecimal("2700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("SGN", "HAN", 
                baseDate.withHour(16).withMinute(30), 
                baseDate.withHour(18).withMinute(45), 
                new BigDecimal("2650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("SGN", "HAN", 
                baseDate.withHour(20).withMinute(0), 
                baseDate.withHour(22).withMinute(15), 
                new BigDecimal("2600000"), FlightStatus.SCHEDULED));

            // ========== HAN - DAD (Hà Nội - Đà Nẵng) ==========
            flightRepository.save(new Flight("HAN", "DAD", 
                baseDate.withHour(6).withMinute(30), 
                baseDate.withHour(8).withMinute(0), 
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("HAN", "DAD", 
                baseDate.withHour(9).withMinute(0), 
                baseDate.withHour(10).withMinute(30), 
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("HAN", "DAD", 
                baseDate.withHour(13).withMinute(0), 
                baseDate.withHour(14).withMinute(30), 
                new BigDecimal("1850000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("HAN", "DAD", 
                baseDate.withHour(17).withMinute(0), 
                baseDate.withHour(18).withMinute(30), 
                new BigDecimal("1750000"), FlightStatus.SCHEDULED));

            // ========== DAD - HAN (Đà Nẵng - Hà Nội) ==========
            flightRepository.save(new Flight("DAD", "HAN", 
                baseDate.withHour(8).withMinute(30), 
                baseDate.withHour(10).withMinute(0), 
                new BigDecimal("1650000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("DAD", "HAN", 
                baseDate.withHour(12).withMinute(30), 
                baseDate.withHour(14).withMinute(0), 
                new BigDecimal("1750000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("DAD", "HAN", 
                baseDate.withHour(15).withMinute(30), 
                baseDate.withHour(17).withMinute(0), 
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("DAD", "HAN", 
                baseDate.withHour(19).withMinute(0), 
                baseDate.withHour(20).withMinute(30), 
                new BigDecimal("1700000"), FlightStatus.SCHEDULED));

            // ========== SGN - DAD (TP. Hồ Chí Minh - Đà Nẵng) ==========
            flightRepository.save(new Flight("SGN", "DAD", 
                baseDate.withHour(6).withMinute(0), 
                baseDate.withHour(7).withMinute(30), 
                new BigDecimal("1400000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("SGN", "DAD", 
                baseDate.withHour(8).withMinute(0), 
                baseDate.withHour(9).withMinute(30), 
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("SGN", "DAD", 
                baseDate.withHour(11).withMinute(0), 
                baseDate.withHour(12).withMinute(30), 
                new BigDecimal("1550000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("SGN", "DAD", 
                baseDate.withHour(16).withMinute(0), 
                baseDate.withHour(17).withMinute(30), 
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));
            
            // ========== DAD - SGN (Đà Nẵng - TP. Hồ Chí Minh) ==========
            flightRepository.save(new Flight("DAD", "SGN", 
                baseDate.withHour(7).withMinute(30), 
                baseDate.withHour(9).withMinute(0), 
                new BigDecimal("1450000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("DAD", "SGN", 
                baseDate.withHour(10).withMinute(30), 
                baseDate.withHour(12).withMinute(0), 
                new BigDecimal("1550000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("DAD", "SGN", 
                baseDate.withHour(15).withMinute(0), 
                baseDate.withHour(16).withMinute(30), 
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("DAD", "SGN", 
                baseDate.withHour(19).withMinute(30), 
                baseDate.withHour(21).withMinute(0), 
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));

            // ========== SGN - PQC (TP. Hồ Chí Minh - Phú Quốc) ==========
            flightRepository.save(new Flight("SGN", "PQC", 
                baseDate.withHour(6).withMinute(0), 
                baseDate.withHour(7).withMinute(0), 
                new BigDecimal("1300000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("SGN", "PQC", 
                baseDate.withHour(7).withMinute(30), 
                baseDate.withHour(8).withMinute(30), 
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("SGN", "PQC", 
                baseDate.withHour(10).withMinute(0), 
                baseDate.withHour(11).withMinute(0), 
                new BigDecimal("1450000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("SGN", "PQC", 
                baseDate.withHour(14).withMinute(30), 
                baseDate.withHour(15).withMinute(30), 
                new BigDecimal("1550000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("SGN", "PQC", 
                baseDate.withHour(19).withMinute(0), 
                baseDate.withHour(20).withMinute(0), 
                new BigDecimal("1400000"), FlightStatus.SCHEDULED));
            
            // ========== PQC - SGN (Phú Quốc - TP. Hồ Chí Minh) ==========
            flightRepository.save(new Flight("PQC", "SGN", 
                baseDate.withHour(7).withMinute(30), 
                baseDate.withHour(8).withMinute(30), 
                new BigDecimal("1350000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("PQC", "SGN", 
                baseDate.withHour(11).withMinute(30), 
                baseDate.withHour(12).withMinute(30), 
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("PQC", "SGN", 
                baseDate.withHour(15).withMinute(0), 
                baseDate.withHour(16).withMinute(0), 
                new BigDecimal("1600000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("PQC", "SGN", 
                baseDate.withHour(17).withMinute(30), 
                baseDate.withHour(18).withMinute(30), 
                new BigDecimal("1700000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("PQC", "SGN", 
                baseDate.withHour(20).withMinute(30), 
                baseDate.withHour(21).withMinute(30), 
                new BigDecimal("1450000"), FlightStatus.SCHEDULED));

            // ========== HAN - CXR (Hà Nội - Nha Trang) ==========
            flightRepository.save(new Flight("HAN", "CXR", 
                baseDate.withHour(6).withMinute(30), 
                baseDate.withHour(8).withMinute(30), 
                new BigDecimal("2000000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("HAN", "CXR", 
                baseDate.withHour(10).withMinute(0), 
                baseDate.withHour(12).withMinute(0), 
                new BigDecimal("2200000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("HAN", "CXR", 
                baseDate.withHour(14).withMinute(30), 
                baseDate.withHour(16).withMinute(30), 
                new BigDecimal("2300000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("HAN", "CXR", 
                baseDate.withHour(18).withMinute(0), 
                baseDate.withHour(20).withMinute(0), 
                new BigDecimal("2100000"), FlightStatus.SCHEDULED));

            // ========== CXR - HAN (Nha Trang - Hà Nội) ==========
            flightRepository.save(new Flight("CXR", "HAN", 
                baseDate.withHour(8).withMinute(30), 
                baseDate.withHour(10).withMinute(30), 
                new BigDecimal("2050000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("CXR", "HAN", 
                baseDate.withHour(12).withMinute(30), 
                baseDate.withHour(14).withMinute(30), 
                new BigDecimal("2250000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("CXR", "HAN", 
                baseDate.withHour(16).withMinute(0), 
                baseDate.withHour(18).withMinute(0), 
                new BigDecimal("2300000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("CXR", "HAN", 
                baseDate.withHour(18).withMinute(0), 
                baseDate.withHour(20).withMinute(0), 
                new BigDecimal("2200000"), FlightStatus.SCHEDULED));

            // ========== SGN - CXR (TP. Hồ Chí Minh - Nha Trang) ==========
            flightRepository.save(new Flight("SGN", "CXR", 
                baseDate.withHour(6).withMinute(0), 
                baseDate.withHour(7).withMinute(0), 
                new BigDecimal("1200000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("SGN", "CXR", 
                baseDate.withHour(10).withMinute(0), 
                baseDate.withHour(11).withMinute(0), 
                new BigDecimal("1400000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("SGN", "CXR", 
                baseDate.withHour(14).withMinute(0), 
                baseDate.withHour(15).withMinute(0), 
                new BigDecimal("1350000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("SGN", "CXR", 
                baseDate.withHour(18).withMinute(30), 
                baseDate.withHour(19).withMinute(30), 
                new BigDecimal("1300000"), FlightStatus.SCHEDULED));

            // ========== CXR - SGN (Nha Trang - TP. Hồ Chí Minh) ==========
            flightRepository.save(new Flight("CXR", "SGN", 
                baseDate.withHour(7).withMinute(30), 
                baseDate.withHour(8).withMinute(30), 
                new BigDecimal("1250000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("CXR", "SGN", 
                baseDate.withHour(11).withMinute(30), 
                baseDate.withHour(12).withMinute(30), 
                new BigDecimal("1400000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("CXR", "SGN", 
                baseDate.withHour(15).withMinute(30), 
                baseDate.withHour(16).withMinute(30), 
                new BigDecimal("1380000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("CXR", "SGN", 
                baseDate.withHour(20).withMinute(0), 
                baseDate.withHour(21).withMinute(0), 
                new BigDecimal("1300000"), FlightStatus.SCHEDULED));

            // ========== HAN - PQC (Hà Nội - Phú Quốc) ==========
            flightRepository.save(new Flight("HAN", "PQC", 
                baseDate.withHour(7).withMinute(0), 
                baseDate.withHour(9).withMinute(30), 
                new BigDecimal("2800000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("HAN", "PQC", 
                baseDate.withHour(13).withMinute(0), 
                baseDate.withHour(15).withMinute(30), 
                new BigDecimal("3000000"), FlightStatus.SCHEDULED));

            // ========== PQC - HAN (Phú Quốc - Hà Nội) ==========
            flightRepository.save(new Flight("PQC", "HAN", 
                baseDate.withHour(10).withMinute(0), 
                baseDate.withHour(12).withMinute(30), 
                new BigDecimal("2850000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("PQC", "HAN", 
                baseDate.withHour(16).withMinute(0), 
                baseDate.withHour(18).withMinute(30), 
                new BigDecimal("3000000"), FlightStatus.SCHEDULED));

            // ========== DAD - CXR (Đà Nẵng - Nha Trang) ==========
            flightRepository.save(new Flight("DAD", "CXR", 
                baseDate.withHour(8).withMinute(0), 
                baseDate.withHour(9).withMinute(0), 
                new BigDecimal("1100000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("DAD", "CXR", 
                baseDate.withHour(15).withMinute(0), 
                baseDate.withHour(16).withMinute(0), 
                new BigDecimal("1200000"), FlightStatus.SCHEDULED));

            // ========== CXR - DAD (Nha Trang - Đà Nẵng) ==========
            flightRepository.save(new Flight("CXR", "DAD", 
                baseDate.withHour(10).withMinute(0), 
                baseDate.withHour(11).withMinute(0), 
                new BigDecimal("1100000"), FlightStatus.SCHEDULED));
            
            flightRepository.save(new Flight("CXR", "DAD", 
                baseDate.withHour(17).withMinute(0), 
                baseDate.withHour(18).withMinute(0), 
                new BigDecimal("1200000"), FlightStatus.SCHEDULED));

            System.out.println("✅ Flights initialized: 60 flights covering all major routes");
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

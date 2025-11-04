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
            admin.setEmail("admin@vietnamairlines.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFullName("Quản trị viên");
            admin.setPhone("0901234567");
            admin.setRole(adminRole);
            userRepository.save(admin);

            // Create Manager
            User manager = new User();
            manager.setEmail("manager@vietnamairlines.com");
            manager.setPassword(passwordEncoder.encode("manager123"));
            manager.setFullName("Nguyễn Văn Manager");
            manager.setPhone("0902345678");
            manager.setRole(managerRole);
            userRepository.save(manager);

            // Create User
            User user = new User();
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setFullName("Trần Thị User");
            user.setPhone("0903456789");
            user.setRole(userRole);
            userRepository.save(user);

            System.out.println("✅ Users initialized");
            System.out.println("📧 Admin: admin@vietnamairlines.com / admin123");
            System.out.println("📧 Manager: manager@vietnamairlines.com / manager123");
            System.out.println("📧 User: user@example.com / user123");
        }
    }

    private void initializeAirports() {
        if (airportRepository.count() == 0) {
            airportRepository.save(new Airport("HAN", "Sân bay Quốc tế Nội Bài", "Hà Nội", "Việt Nam"));
            airportRepository.save(new Airport("SGN", "Sân bay Quốc tế Tân Sơn Nhất", "TP. Hồ Chí Minh", "Việt Nam"));
            airportRepository.save(new Airport("DAD", "Sân bay Quốc tế Đà Nẵng", "Đà Nẵng", "Việt Nam"));
            airportRepository.save(new Airport("CXR", "Sân bay Quốc tế Cam Ranh", "Nha Trang", "Việt Nam"));
            airportRepository.save(new Airport("HPH", "Sân bay Cát Bi", "Hải Phòng", "Việt Nam"));
            airportRepository.save(new Airport("PQC", "Sân bay Quốc tế Phú Quốc", "Phú Quốc", "Việt Nam"));
            airportRepository.save(new Airport("VCA", "Sân bay Cần Thơ", "Cần Thơ", "Việt Nam"));
            airportRepository.save(new Airport("VII", "Sân bay Vinh", "Vinh", "Việt Nam"));
            airportRepository.save(new Airport("HUI", "Sân bay Phú Bài", "Huế", "Việt Nam"));
            airportRepository.save(new Airport("BMV", "Sân bay Buôn Ma Thuột", "Buôn Ma Thuột", "Việt Nam"));
            System.out.println("✅ Airports initialized");
        }
    }

    private void initializeFlights() {
        if (flightRepository.count() == 0) {
            LocalDateTime baseDate = LocalDateTime.now().plusDays(5);

            flightRepository.save(new Flight("HAN", "SGN", 
                baseDate.withHour(8).withMinute(0), 
                baseDate.withHour(10).withMinute(15), 
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));

            flightRepository.save(new Flight("SGN", "HAN", 
                baseDate.withHour(14).withMinute(0), 
                baseDate.withHour(16).withMinute(15), 
                new BigDecimal("2500000"), FlightStatus.SCHEDULED));

            flightRepository.save(new Flight("HAN", "DAD", 
                baseDate.plusDays(1).withHour(9).withMinute(0), 
                baseDate.plusDays(1).withHour(10).withMinute(30), 
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));

            flightRepository.save(new Flight("DAD", "HAN", 
                baseDate.plusDays(1).withHour(15).withMinute(0), 
                baseDate.plusDays(1).withHour(16).withMinute(30), 
                new BigDecimal("1800000"), FlightStatus.SCHEDULED));

            flightRepository.save(new Flight("SGN", "PQC", 
                baseDate.plusDays(2).withHour(7).withMinute(30), 
                baseDate.plusDays(2).withHour(8).withMinute(30), 
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));

            flightRepository.save(new Flight("PQC", "SGN", 
                baseDate.plusDays(2).withHour(16).withMinute(0), 
                baseDate.plusDays(2).withHour(17).withMinute(0), 
                new BigDecimal("1500000"), FlightStatus.SCHEDULED));

            flightRepository.save(new Flight("HAN", "CXR", 
                baseDate.plusDays(3).withHour(10).withMinute(0), 
                baseDate.plusDays(3).withHour(12).withMinute(0), 
                new BigDecimal("2200000"), FlightStatus.SCHEDULED));

            flightRepository.save(new Flight("CXR", "HAN", 
                baseDate.plusDays(3).withHour(18).withMinute(0), 
                baseDate.plusDays(3).withHour(20).withMinute(0), 
                new BigDecimal("2200000"), FlightStatus.SCHEDULED));

            flightRepository.save(new Flight("SGN", "VCA", 
                baseDate.plusDays(4).withHour(11).withMinute(0), 
                baseDate.plusDays(4).withHour(11).withMinute(50), 
                new BigDecimal("1200000"), FlightStatus.SCHEDULED));

            flightRepository.save(new Flight("HAN", "HPH", 
                baseDate.plusDays(5).withHour(6).withMinute(0), 
                baseDate.plusDays(5).withHour(6).withMinute(45), 
                new BigDecimal("800000"), FlightStatus.SCHEDULED));

            System.out.println("✅ Flights initialized");
        }
    }

    private void initializeSeats() {
        if (seatRepository.count() == 0) {
            // Business Class seats (rows 1-5)
            String[] businessRows = {"1", "2", "3", "4", "5"};
            String[] businessCols = {"A", "B", "C", "D"};
            
            for (String row : businessRows) {
                for (String col : businessCols) {
                    seatRepository.save(new Seat(row + col));
                }
            }

            // Economy Class seats (rows 10-25)
            String[] economyRows = {"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"};
            String[] economyCols = {"A", "B", "C", "D", "E", "F"};
            
            for (String row : economyRows) {
                for (String col : economyCols) {
                    seatRepository.save(new Seat(row + col));
                }
            }

            System.out.println("✅ Seats initialized");
        }
    }
}

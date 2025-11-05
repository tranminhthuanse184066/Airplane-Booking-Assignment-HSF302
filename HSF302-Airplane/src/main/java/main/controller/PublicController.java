package main.controller;

import main.pojo.Airport;
import main.pojo.Flight;
import main.service.category.AirportService;
import main.service.produce.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class PublicController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportService airportService;

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

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }
}

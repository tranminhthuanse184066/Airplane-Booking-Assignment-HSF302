package main.controller;

import main.pojo.Airport;
import main.pojo.Flight;
import main.service.category.AirportService;
import main.service.produce.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public String home(Model model) {
        List<Airport> airports = airportService.getAllAirports();
        model.addAttribute("airports", airports);
        return "public/home";
    }

    @GetMapping("/search")
    public String searchFlights(
            @RequestParam(required = false) String departure,
            @RequestParam(required = false) String arrival,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {
        
        List<Airport> airports = airportService.getAllAirports();
        model.addAttribute("airports", airports);
        
        if (departure != null && arrival != null && date != null) {
            List<Flight> flights = flightService.searchFlights(departure, arrival, date);
            model.addAttribute("flights", flights);
            model.addAttribute("searchPerformed", true);
        }
        
        return "public/search";
    }

    @GetMapping("/flight/{id}")
    public String flightDetail(@PathVariable Integer id, Model model) {
        Flight flight = flightService.getFlightById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        model.addAttribute("flight", flight);
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

package main.controller;

import main.enumerators.FlightStatus;
import main.pojo.Airport;
import main.pojo.Flight;
import main.service.category.AirportService;
import main.service.produce.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportService airportService;

    @GetMapping
    public String managerDashboard(Model model) {
        List<Flight> flights = flightService.getAllFlights();
        model.addAttribute("flights", flights);
        return "manager/dashboard";
    }

    @GetMapping("/flights")
    public String listFlights(Model model) {
        List<Flight> flights = flightService.getAllFlights();
        model.addAttribute("flights", flights);
        return "manager/flights";
    }

    @GetMapping("/flights/create")
    public String createFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        model.addAttribute("airports", airportService.getAllAirports());
        model.addAttribute("statuses", FlightStatus.values());
        return "manager/flight-form";
    }

    @PostMapping("/flights/create")
    public String createFlight(@ModelAttribute Flight flight) {
        flightService.createFlight(flight);
        return "redirect:/manager/flights";
    }

    @GetMapping("/flights/edit/{id}")
    public String editFlightForm(@PathVariable Integer id, Model model) {
        Flight flight = flightService.getFlightById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        model.addAttribute("flight", flight);
        model.addAttribute("airports", airportService.getAllAirports());
        model.addAttribute("statuses", FlightStatus.values());
        return "manager/flight-form";
    }

    @PostMapping("/flights/edit/{id}")
    public String editFlight(@PathVariable Integer id, @ModelAttribute Flight flight) {
        flightService.updateFlight(id, flight);
        return "redirect:/manager/flights";
    }

    @GetMapping("/flights/delete/{id}")
    public String deleteFlight(@PathVariable Integer id) {
        flightService.deleteFlight(id);
        return "redirect:/manager/flights";
    }

    @GetMapping("/airports")
    public String listAirports(Model model) {
        List<Airport> airports = airportService.getAllAirports();
        model.addAttribute("airports", airports);
        return "manager/airports";
    }

    @GetMapping("/airports/create")
    public String createAirportForm(Model model) {
        model.addAttribute("airport", new Airport());
        return "manager/airport-form";
    }

    @PostMapping("/airports/create")
    public String createAirport(@ModelAttribute Airport airport) {
        airportService.createAirport(airport);
        return "redirect:/manager/airports";
    }

    @GetMapping("/airports/edit/{id}")
    public String editAirportForm(@PathVariable Integer id, Model model) {
        Airport airport = airportService.getAirportById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));
        model.addAttribute("airport", airport);
        return "manager/airport-form";
    }

    @PostMapping("/airports/edit/{id}")
    public String editAirport(@PathVariable Integer id, @ModelAttribute Airport airport) {
        airportService.updateAirport(id, airport);
        return "redirect:/manager/airports";
    }

    @GetMapping("/airports/delete/{id}")
    public String deleteAirport(@PathVariable Integer id) {
        airportService.deleteAirport(id);
        return "redirect:/manager/airports";
    }
}

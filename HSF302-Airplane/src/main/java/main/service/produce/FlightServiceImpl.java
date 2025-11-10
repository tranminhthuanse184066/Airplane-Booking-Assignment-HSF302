package main.service.produce;

import main.enumerators.FlightStatus;
import main.pojo.Flight;
import main.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private main.repository.TicketRepository ticketRepository;

    @Override
    @Transactional
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    @Transactional
    public Flight updateFlight(Integer flightId, Flight flight) {
        Flight existingFlight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        
        existingFlight.setDepartureAirport(flight.getDepartureAirport());
        existingFlight.setArrivalAirport(flight.getArrivalAirport());
        existingFlight.setDepartureTime(flight.getDepartureTime());
        existingFlight.setArrivalTime(flight.getArrivalTime());
        existingFlight.setPrice(flight.getPrice());
        existingFlight.setStatus(flight.getStatus());
        
        return flightRepository.save(existingFlight);
    }

    @Override
    @Transactional
    public void deleteFlight(Integer flightId) {
        flightRepository.deleteById(flightId);
    }

    @Override
    public Optional<Flight> getFlightById(Integer flightId) {
        return flightRepository.findById(flightId);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> searchFlights(String departure, String arrival, LocalDate date) {
        return flightRepository.searchFlights(departure, arrival, date);
    }

    @Override
    public List<Flight> getFlightsByStatus(FlightStatus status) {
        return flightRepository.findByStatus(status);
    }

    @Override
    public int getBookingCountByFlightId(Integer flightId) {
        return ticketRepository.countByFlightFlightId(flightId);
    }
}

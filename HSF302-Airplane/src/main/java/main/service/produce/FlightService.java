package main.service.produce;

import main.enumerators.FlightStatus;
import main.pojo.Flight;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightService {
    Flight createFlight(Flight flight);
    Flight updateFlight(Integer flightId, Flight flight);
    void deleteFlight(Integer flightId);
    Optional<Flight> getFlightById(Integer flightId);
    List<Flight> getAllFlights();
    List<Flight> searchFlights(String departure, String arrival, LocalDate date);
    List<Flight> getFlightsByStatus(FlightStatus status);
}

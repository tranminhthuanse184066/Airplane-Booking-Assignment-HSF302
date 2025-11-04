package main.service.category;

import main.pojo.Airport;
import java.util.List;
import java.util.Optional;

public interface AirportService {
    Airport createAirport(Airport airport);
    Airport updateAirport(Integer airportId, Airport airport);
    void deleteAirport(Integer airportId);
    Optional<Airport> getAirportById(Integer airportId);
    Optional<Airport> getAirportByCode(String code);
    List<Airport> getAllAirports();
    List<Airport> getAirportsByCity(String city);
}

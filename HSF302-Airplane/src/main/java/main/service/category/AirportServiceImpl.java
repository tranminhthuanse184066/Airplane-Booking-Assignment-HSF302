package main.service.category;

import main.pojo.Airport;
import main.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    @Transactional
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    @Transactional
    public Airport updateAirport(Integer airportId, Airport airport) {
        Airport existingAirport = airportRepository.findById(airportId)
                .orElseThrow(() -> new RuntimeException("Airport not found"));
        
        existingAirport.setCode(airport.getCode());
        existingAirport.setName(airport.getName());
        existingAirport.setCity(airport.getCity());
        existingAirport.setCountry(airport.getCountry());
        
        return airportRepository.save(existingAirport);
    }

    @Override
    @Transactional
    public void deleteAirport(Integer airportId) {
        airportRepository.deleteById(airportId);
    }

    @Override
    public Optional<Airport> getAirportById(Integer airportId) {
        return airportRepository.findById(airportId);
    }

    @Override
    public Optional<Airport> getAirportByCode(String code) {
        return airportRepository.findByCode(code);
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public List<Airport> getAirportsByCity(String city) {
        return airportRepository.findByCity(city);
    }
}

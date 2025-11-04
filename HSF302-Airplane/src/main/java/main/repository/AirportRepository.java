package main.repository;

import main.pojo.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
    Optional<Airport> findByCode(String code);
    List<Airport> findByCity(String city);
    List<Airport> findByCountry(String country);
}

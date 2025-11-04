package main.repository;

import main.enumerators.FlightStatus;
import main.pojo.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByStatus(FlightStatus status);
    
    @Query("SELECT f FROM Flight f WHERE f.departureAirport = :departure " +
           "AND f.arrivalAirport = :arrival AND f.departureDate = :date")
    List<Flight> searchFlights(@Param("departure") String departure, 
                               @Param("arrival") String arrival, 
                               @Param("date") LocalDate date);
    
    List<Flight> findByDepartureAirportAndArrivalAirport(String departureAirport, String arrivalAirport);
    
    List<Flight> findByDepartureDate(LocalDate departureDate);
}

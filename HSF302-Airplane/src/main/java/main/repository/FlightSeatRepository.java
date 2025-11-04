package main.repository;

import main.enumerators.SeatStatus;
import main.pojo.FlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightSeatRepository extends JpaRepository<FlightSeat, Long> {
    List<FlightSeat> findByFlightFlightId(Integer flightId);
    List<FlightSeat> findByFlightFlightIdAndStatus(Integer flightId, SeatStatus status);
    List<FlightSeat> findByTicketTicketId(Integer ticketId);
}

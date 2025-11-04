package main.repository;

import main.enumerators.TicketStatus;
import main.pojo.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByOrderOrderId(Integer orderId);
    List<Ticket> findByStatus(TicketStatus status);
}

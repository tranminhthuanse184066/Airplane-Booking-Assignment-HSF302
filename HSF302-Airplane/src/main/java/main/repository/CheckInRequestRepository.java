package main.repository;

import main.enumerators.CheckInStatus;
import main.pojo.CheckInRequest;
import main.pojo.Order;
import main.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckInRequestRepository extends JpaRepository<CheckInRequest, Integer> {
    @Query("SELECT DISTINCT c FROM CheckInRequest c " +
           "LEFT JOIN FETCH c.order o " +
           "LEFT JOIN FETCH c.user u " +
           "LEFT JOIN FETCH o.tickets t " +
           "LEFT JOIN FETCH t.flight f " +
           "WHERE c.status = :status")
    List<CheckInRequest> findByStatus(@Param("status") CheckInStatus status);
    
    List<CheckInRequest> findByUserUserId(Integer userId);
    List<CheckInRequest> findByOrderOrderId(Integer orderId);
    Optional<CheckInRequest> findByOrderAndStatus(Order order, CheckInStatus status);
}

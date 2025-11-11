package main.repository;

import main.enumerators.CheckInStatus;
import main.pojo.CheckInRequest;
import main.pojo.Order;
import main.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckInRequestRepository extends JpaRepository<CheckInRequest, Integer> {
    List<CheckInRequest> findByStatus(CheckInStatus status);
    List<CheckInRequest> findByUserUserId(Integer userId);
    List<CheckInRequest> findByOrderOrderId(Integer orderId);
    Optional<CheckInRequest> findByOrderAndStatus(Order order, CheckInStatus status);
}

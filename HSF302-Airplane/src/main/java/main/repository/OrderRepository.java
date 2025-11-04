package main.repository;

import main.enumerators.OrderStatus;
import main.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserUserId(Integer userId);
    List<Order> findByStatus(OrderStatus status);
}

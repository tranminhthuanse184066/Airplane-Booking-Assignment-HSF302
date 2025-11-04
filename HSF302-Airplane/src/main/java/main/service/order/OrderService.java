package main.service.order;

import main.enumerators.OrderStatus;
import main.pojo.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);
    Order updateOrder(Integer orderId, Order order);
    void deleteOrder(Integer orderId);
    Optional<Order> getOrderById(Integer orderId);
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(Integer userId);
    List<Order> getOrdersByStatus(OrderStatus status);
}

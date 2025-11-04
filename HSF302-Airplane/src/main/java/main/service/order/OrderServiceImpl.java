package main.service.order;

import main.enumerators.OrderStatus;
import main.pojo.Order;
import main.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateOrder(Integer orderId, Order order) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        existingOrder.setFullName(order.getFullName());
        existingOrder.setEmail(order.getEmail());
        existingOrder.setPhone(order.getPhone());
        existingOrder.setTotalPrice(order.getTotalPrice());
        existingOrder.setStatus(order.getStatus());
        
        return orderRepository.save(existingOrder);
    }

    @Override
    @Transactional
    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Optional<Order> getOrderById(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUserId(Integer userId) {
        return orderRepository.findByUserUserId(userId);
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }
}

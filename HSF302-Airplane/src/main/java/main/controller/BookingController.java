package main.controller;

import main.enumerators.OrderStatus;
import main.enumerators.TicketStatus;
import main.pojo.*;
import main.service.order.OrderService;
import main.service.produce.FlightService;
import main.service.user.UserService;
import main.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping("/create")
    public String createBooking(
            @RequestParam Integer flightId,
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String seatClass,
            Authentication authentication,
            Model model) {
        
        // Get current user
        String userEmail = authentication.getName();
        User user = userService.getUserByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Get flight
        Flight flight = flightService.getFlightById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        
        // Calculate price based on seat class
        BigDecimal ticketPrice = flight.getPrice();
        if ("Business".equals(seatClass)) {
            // Business class is 2x the base price
            ticketPrice = ticketPrice.multiply(new BigDecimal("2"));
        }
        
        // Create order
        Order order = new Order();
        order.setUser(user);
        order.setFullName(fullName);
        order.setEmail(email);
        order.setPhone(phone);
        order.setTotalPrice(ticketPrice);
        order.setStatus(OrderStatus.PENDING);
        
        Order savedOrder = orderService.createOrder(order);
        
        // Create ticket
        Ticket ticket = new Ticket();
        ticket.setSeatClass(seatClass);
        ticket.setStatus(TicketStatus.BOOKED);
        ticket.setPrice(ticketPrice);
        ticket.setOrder(savedOrder);
        ticket.setFlight(flight);

        Ticket savedTicket = ticketRepository.save(ticket);
        
        // Add to model for confirmation page
        model.addAttribute("order", savedOrder);
        model.addAttribute("flight", flight);
        model.addAttribute("ticket", savedTicket);
        
        return "user/booking-confirmation";
    }

    @PostMapping("/payment")
    public String processPayment(
            @RequestParam Integer orderId,
            Authentication authentication,
            Model model) {
        
        // Get current user
        String userEmail = authentication.getName();
        User user = userService.getUserByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Get order
        Order order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        // Verify the order belongs to the user
        if (!order.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized access");
        }
        
        // Update order status to PAID
        order.setStatus(OrderStatus.PAID);
        orderService.updateOrder(orderId, order);
        
        // Update all tickets in this order to BOOKED status
        java.util.List<Ticket> tickets = ticketRepository.findByOrderOrderId(orderId);
        for (Ticket ticket : tickets) {
            ticket.setStatus(TicketStatus.BOOKED);
            ticketRepository.save(ticket);
        }
        
        // Redirect to order detail page to show ticket
        return "redirect:/user/orders/" + orderId + "?paymentSuccess=true";
    }
}

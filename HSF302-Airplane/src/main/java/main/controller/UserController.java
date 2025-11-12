package main.controller;

import main.pojo.Order;
import main.pojo.User;
import main.service.order.OrderService;
import main.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String userDashboard(Authentication authentication, Model model,
                                @RequestParam(required = false) String paymentSuccess) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        List<Order> orders = orderService.getOrdersByUserId(user.getUserId());
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        
        if ("true".equals(paymentSuccess)) {
            model.addAttribute("successMessage", "Thanh toán thành công!");
        }
        
        return "user/dashboard";
    }

    @GetMapping("/orders")
    public String listOrders(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        List<Order> orders = orderService.getOrdersByUserId(user.getUserId());
        model.addAttribute("orders", orders);
        return "user/orders";
    }

    @Autowired
    private main.repository.TicketRepository ticketRepository;

    @GetMapping("/orders/{id}")
    public String orderDetail(@PathVariable Integer id, Authentication authentication, Model model,
                               @RequestParam(required = false) String paymentSuccess) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Order order = orderService.getOrderById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        if (!order.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized access");
        }
        
        java.util.List<main.pojo.Ticket> tickets = ticketRepository.findByOrderOrderId(order.getOrderId());

        java.util.List<main.pojo.CheckInRequest> checkInRequests = checkInRequestRepository.findByOrderOrderId(order.getOrderId());
        if (!checkInRequests.isEmpty()) {
            model.addAttribute("checkInRequest", checkInRequests.get(checkInRequests.size() - 1));
        }

        if ("true".equals(paymentSuccess)) {
            model.addAttribute("successMessage", "Thanh toán thành công! Vé máy bay của bạn đã sẵn sàng.");
        }

        model.addAttribute("order", order);
        model.addAttribute("tickets", tickets);
        return "user/order-detail";
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(Authentication authentication, @ModelAttribute User updatedUser) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        userService.updateUser(user.getUserId(), updatedUser);
        return "redirect:/user/profile";
    }

    @GetMapping("/check-in")
    public String checkIn(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Order> orders = orderService.getOrdersByUserId(user.getUserId())
                .stream()
                .filter(order -> order.getStatus() == main.enumerators.OrderStatus.CONFIRMED)
                .collect(java.util.stream.Collectors.toList());

        model.addAttribute("orders", orders);
        return "user/check-in";
    }

    @Autowired
    private main.repository.CheckInRequestRepository checkInRequestRepository;

    @Autowired
    private main.repository.OrderRepository orderRepository;

    @PostMapping("/check-in/request")
    @ResponseBody
    public java.util.Map<String, Object> submitCheckInRequest(@RequestParam Integer orderId, Authentication authentication) {
        java.util.Map<String, Object> response = new java.util.HashMap<>();
        
        try {
            String email = authentication.getName();
            User user = userService.getUserByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            
            Order order = orderService.getOrderById(orderId)
                    .orElseThrow(() -> new RuntimeException("Order not found"));
            
            if (!order.getUser().getUserId().equals(user.getUserId())) {
                response.put("success", false);
                response.put("message", "Bạn không có quyền thực hiện thao tác này");
                return response;
            }
            
            if (order.getStatus() != main.enumerators.OrderStatus.PAID) {
                response.put("success", false);
                response.put("message", "Đơn hàng phải được thanh toán trước khi check-in");
                return response;
            }
            
            java.util.Optional<main.pojo.CheckInRequest> existingRequest = 
                checkInRequestRepository.findByOrderAndStatus(order, main.enumerators.CheckInStatus.PENDING);
            
            if (existingRequest.isPresent()) {
                response.put("success", false);
                response.put("message", "Đã có yêu cầu check-in đang chờ xử lý");
                return response;
            }
            
            main.pojo.CheckInRequest checkInRequest = new main.pojo.CheckInRequest();
            checkInRequest.setOrder(order);
            checkInRequest.setUser(user);
            checkInRequest.setStatus(main.enumerators.CheckInStatus.PENDING);
            checkInRequestRepository.save(checkInRequest);
            
            order.setStatus(main.enumerators.OrderStatus.CHECK_IN_PENDING);
            orderRepository.save(order);
            
            response.put("success", true);
            response.put("message", "Yêu cầu check-in đã được gửi thành công!");
            return response;
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Có lỗi xảy ra: " + e.getMessage());
            return response;
        }
    }
}

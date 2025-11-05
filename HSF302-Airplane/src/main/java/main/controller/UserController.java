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

    @GetMapping("/orders/{id}")
    public String orderDetail(@PathVariable Integer id, Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Order order = orderService.getOrderById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        // Verify the order belongs to the user
        if (!order.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized access");
        }
        
        model.addAttribute("order", order);
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
}

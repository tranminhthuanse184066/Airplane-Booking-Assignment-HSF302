package main.controller;

import main.enumerators.RoleEnum;
import main.pojo.Role;
import main.pojo.User;
import main.repository.RoleRepository;
import main.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String adminDashboard(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/dashboard";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/users/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/user-form";
    }

    @PostMapping("/users/create")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/user-form";
    }

    @PostMapping("/users/edit/{id}")
    public String editUser(@PathVariable Integer id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/managers")
    public String listManagers(Model model) {
        Role managerRole = roleRepository.findByRoleName(RoleEnum.MANAGER)
                .orElseThrow(() -> new RuntimeException("Manager role not found"));
        List<User> managers = userService.getUsersByRole(managerRole.getRoleId());
        model.addAttribute("managers", managers);
        return "admin/managers";
    }
}

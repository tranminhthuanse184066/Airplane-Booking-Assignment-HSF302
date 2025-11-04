package main.service.user;

import main.pojo.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    User updateUser(Integer userId, User user);
    void deleteUser(Integer userId);
    Optional<User> getUserById(Integer userId);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    List<User> getUsersByRole(Integer roleId);
    boolean existsByEmail(String email);
}

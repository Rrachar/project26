package atu.ie.oopproject26.service;

import atu.ie.oopproject26.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();
    private long nextId = 1;

    public User createUser(User user) {
        user.setId(Long.valueOf(nextId++));
        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return users;
    }

    // Find user by email (for account + reset)
    public User findByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    // Generate reset token
    public String generateResetToken(String email) {
        User user = findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        String token = "RESET-" + System.currentTimeMillis();
        user.setResetToken(token);

        return token;
    }

    // Reset password
    public void resetPassword(String token, String newPassword) {
        User user = users.stream()
                .filter(u -> token.equals(u.getResetToken()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        user.setPassword(newPassword);
        user.setResetToken(null); // invalidate token
    }
}
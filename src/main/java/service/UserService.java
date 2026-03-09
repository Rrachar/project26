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
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return users;
    }
}


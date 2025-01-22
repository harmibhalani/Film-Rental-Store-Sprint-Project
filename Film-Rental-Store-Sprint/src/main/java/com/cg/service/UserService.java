package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.cg.model.User;
import com.cg.repositories.UserRepository;
 
import java.util.Optional;
 
@Service
public class UserService {
 
    @Autowired
    private UserRepository userRepository;
    public User registerUser(User user) {
        return userRepository.save(user);
    }
    public Optional<User> validateUser(String username, String password) {
        return userRepository.findByUsername(username)
            .filter(user -> user.getPassword().equals(password));
    }
}
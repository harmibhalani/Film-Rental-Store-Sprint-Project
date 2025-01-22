package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.model.User;
import com.cg.service.UserService;

 
@RestController
@RequestMapping("/api/users")
public class UserController {
 
    @Autowired
    private UserService userService;
 
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
 
    @GetMapping("/validate/{username}/{password}")
    public User validateUser(@PathVariable String username, @PathVariable String password) {
        return userService.validateUser(username, password).orElse(null);
    }
}

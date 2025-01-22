package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cg.model.User;
import com.cg.service.UserService;

import jakarta.servlet.http.HttpSession;
 
@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/login?success";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed");
            return "signup";
        }
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, 
                       @RequestParam String password, 
                       HttpSession session,
                       Model model) {
        return userService.validateUser(username, password)
            .map(user -> {
                session.setAttribute("user", user);
                return "redirect:/homePage/dashboard?success"; // Redirect to home, which shows menu.html
            })
            .orElseGet(() -> {
                model.addAttribute("error", "Invalid username or password");
                return "login";
            });
    }
  
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return "redirect:/index"; // Redirect to the login page
    }
}

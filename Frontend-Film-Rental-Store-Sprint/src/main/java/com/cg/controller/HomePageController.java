package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomePageController {

//    @Autowired
//    private HomePageService homePageService;

	 @GetMapping("/dashboard")
	    public String dashboard(HttpSession session) {
	        if (session.getAttribute("user") == null) {
	            return "redirect:/login"; // Redirect to login if not logged in
	        }
	        return "dashboard"; // Return dashboard if logged in
	    }
	
	 @GetMapping("/index")
	    public String index() {
	        return "index"; 
	    }
    @GetMapping("/home")
    public String home() {
        return "home"; 
    }
    
    @GetMapping("/homePage/dashboard")
    public String dashboard() {
        return "dashboard"; 
    }
    
   

    
    @GetMapping("/homePage")
    public String homePage() {
        return "homePage"; 
    }
    
//    @GetMapping("/storeManagement")
//    public String storeManagement() {
//        return "storeManagement"; 
//    }
    
    @GetMapping("/shop")
    public String shop() {
        return "shop"; 
    }
    
    @GetMapping("/filmManagement")
    public String filmManagement() {
        return "filmManagement"; 
    }
    
    @GetMapping("/actorManagement")
    public String actorManagement() {
        return "actorManagement"; 
    }
    
    @GetMapping("/staffManagement")
    public String staffManagement() {
        return "staffManagement"; 
    }
    
    @GetMapping("/storeManagement")
    public String storeManagement() {
        return "storeManagement"; 
    }
  
    @GetMapping("/customerManagement")
    public String customerManagement() {
        return "customerManagement"; 
    }
    
    
    @GetMapping("/rentalManagement")
    public String rentalManagement() {
        return "rentalManagement"; 
    }
    
    @GetMapping("/inventoryManagement")
    public String inventoryManagement() {
        return "inventoryManagement"; 
    }
    
    @GetMapping("/paymentManagement")
    public String paymentManagement() {
        return "paymentManagement"; 
    }
    
    
//    @GetMapping("/logout")
//    public String logout() {
//        return "index"; 
//    }
    
}

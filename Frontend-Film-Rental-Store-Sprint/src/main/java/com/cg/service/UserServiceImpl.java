package com.cg.service;

import java.util.Optional;

import com.cg.model.User;
 
public class UserServiceImpl {

 
	public interface UserService {
	    User registerUser(User user);
	    Optional<User> validateUser(String username, String password);
	}
 
 
}

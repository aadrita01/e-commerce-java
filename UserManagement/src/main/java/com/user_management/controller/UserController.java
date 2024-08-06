package com.user_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user_management.constant.constant;
import com.user_management.model.JwtRequest;
import com.user_management.model.JwtResponse;
import com.user_management.model.User;
import com.user_management.service.JwtService;
import com.user_management.service.UserService;

@RestController
@CrossOrigin(constant.CLIENT)
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private JwtService jwtService;
	
	
	@PostMapping({constant.REGISTER})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

	@GetMapping({constant.ADMIN})
    @PreAuthorize("hasRole('admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({constant.USER})
    @PreAuthorize("hasRole('user')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
    
    
}

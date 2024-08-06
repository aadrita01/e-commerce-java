package com.user_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user_management.constant.constant;
import com.user_management.model.JwtRequest;
import com.user_management.model.JwtResponse;
import com.user_management.service.JwtService;

@RestController
@CrossOrigin(constant.CLIENT)
public class JwtController {
	
	@Autowired
    private JwtService jwtService;
	
	@PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }

}

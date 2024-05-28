package com.skon.eshoppe.userservice.controller;

import com.skon.eshoppe.userservice.entity.User;
import com.skon.eshoppe.userservice.model.LoginCredentials;
import com.skon.eshoppe.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);

        userService.registerUser(user);

        return new ResponseEntity<>("User registered successfully" , HttpStatus.CREATED);
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<String> addAdmin(@RequestBody User user){
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);

        userService.addAdmin(user);

        return new ResponseEntity<>("Admin added User successfully" , HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginCredentials credentials){
        UsernamePasswordAuthenticationToken authCredentials = new UsernamePasswordAuthenticationToken(
                credentials.getUserName(), credentials.getPassword());

        authenticationManager.authenticate(authCredentials);

        return new ResponseEntity<>("Login Successful" , HttpStatus.OK);
    }

}


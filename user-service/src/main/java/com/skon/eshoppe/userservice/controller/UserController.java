package com.skon.eshoppe.userservice.controller;

import com.skon.eshoppe.userservice.entity.User;
import com.skon.eshoppe.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("admin/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){

        return userService.getAllUsers();
    }

    @GetMapping("{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId){

        return userService.findByUserId(userId);
    }

    @PutMapping("{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user){
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);

        return userService.updateUser(userId, user);
    }

    @DeleteMapping("admin/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){

        return userService.deleteUser(userId);
    }

    @PostMapping("admin/addUser/{roleName}")
    public ResponseEntity<String> addUserForAdmin(@RequestBody User user, @PathVariable String roleName){
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);

        userService.addAUserFordmin(user, roleName);

        return new ResponseEntity<>("Admin added User successfully" , HttpStatus.CREATED);
    }
}

package com.skon.eshoppe.userservice.service;

import com.skon.eshoppe.userservice.entity.Role;
import com.skon.eshoppe.userservice.entity.User;
import com.skon.eshoppe.userservice.repository.RoleRepository;
import com.skon.eshoppe.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public User registerUser(User user) {
        Role role = roleRepository.findByRoleName("user");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);

        return user;
    }

    public User addAdmin(User user) {
        Role role = roleRepository.findByRoleName("admin");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);

        return user;
    }

    public User addAUserFordmin(User user, String roleName) {
        Role role = roleRepository.findByRoleName(roleName);

        Set<Role> roles = new HashSet<>();
        if(role == null){
            Role newRole = roleRepository.save(new Role(roleName));
            roles.add(newRole);
        }

        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);

        return user;
    }

    public ResponseEntity<User> findByUserId(Long userId) {

        if(userRepository.existsById(userId)){
            return new ResponseEntity<>(userRepository.findByUserId(userId), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<User> updateUser(Long userId, User user) {

        User userFromDB =userRepository.findByUserId(userId);

        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setEmail(user.getEmail());
        userFromDB.setMobileNumber(user.getMobileNumber());
        userRepository.save(userFromDB);

        return new ResponseEntity<>(userFromDB, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUser(Long userId) {

        User userFromDB =userRepository.findByUserId(userId);
        userRepository.delete(userFromDB);

        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    public User getUserByUsername(String userName) {

        User user = userRepository.getUserByUsername(userName);

        return user;
    }
}

package com.skon.eshoppe.userservice.service;

import com.skon.eshoppe.userservice.config.UserDetailsConfig;
import com.skon.eshoppe.userservice.entity.User;
import com.skon.eshoppe.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.getUserByUsername(userName);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return new UserDetailsConfig(user);
    }
}

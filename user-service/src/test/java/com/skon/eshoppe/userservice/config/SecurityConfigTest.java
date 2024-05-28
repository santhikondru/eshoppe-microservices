package com.skon.eshoppe.userservice.config;


import com.skon.eshoppe.userservice.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SecurityConfigTest {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DaoAuthenticationProvider authenticationProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    void userDetailsServiceBeanIsLoaded() {
        assertThat(userDetailsService).isNotNull();
        assertThat(userDetailsService).isInstanceOf(UserDetailsServiceImpl.class);
    }

    @Test
    void passwordEncoderBeanIsLoaded() {
        assertThat(passwordEncoder).isNotNull();
        assertThat(passwordEncoder).isInstanceOf(BCryptPasswordEncoder.class);
    }

    @Test
    void securityFilterChainBeanIsLoaded() throws Exception {
        assertThat(securityConfig.securityFilterChain(null)).isNotNull();
    }

}

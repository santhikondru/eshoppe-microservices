package com.skon.eshoppe.userservice.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCredentials {

    @Email
    @Column(unique = true, nullable = false)
    private String userName;

    private String password;
}

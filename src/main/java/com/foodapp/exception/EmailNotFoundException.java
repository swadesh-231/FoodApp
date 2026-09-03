package com.foodapp.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmailNotFoundException extends UsernameNotFoundException {
    public EmailNotFoundException(String email) {
        super(String.format("User not found with email: %s", email));
    }
}

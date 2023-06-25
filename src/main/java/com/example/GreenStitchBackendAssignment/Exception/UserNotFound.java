package com.example.GreenStitchBackendAssignment.Exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFound extends UsernameNotFoundException {
    public UserNotFound(String message) {
        super(message);
    }
}

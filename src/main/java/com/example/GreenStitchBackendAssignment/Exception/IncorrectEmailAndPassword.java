package com.example.GreenStitchBackendAssignment.Exception;

import org.springframework.security.authentication.BadCredentialsException;

public class IncorrectEmailAndPassword extends BadCredentialsException {
    public IncorrectEmailAndPassword(String message) {
        super(message);
    }
}

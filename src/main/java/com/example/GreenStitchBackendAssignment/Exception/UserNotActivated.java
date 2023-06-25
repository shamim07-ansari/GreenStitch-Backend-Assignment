package com.example.GreenStitchBackendAssignment.Exception;

import org.springframework.security.authentication.DisabledException;

public class UserNotActivated extends DisabledException {
    public UserNotActivated(String message) {
        super(message);
    }
}

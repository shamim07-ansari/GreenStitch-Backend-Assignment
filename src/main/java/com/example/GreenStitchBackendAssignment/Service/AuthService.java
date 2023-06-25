package com.example.GreenStitchBackendAssignment.Service;

import com.example.GreenStitchBackendAssignment.Dto.SignupDto;
import com.example.GreenStitchBackendAssignment.Dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupDto signupDto);
}

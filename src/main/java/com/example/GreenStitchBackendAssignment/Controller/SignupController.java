package com.example.GreenStitchBackendAssignment.Controller;

import com.example.GreenStitchBackendAssignment.Dto.SignupDto;
import com.example.GreenStitchBackendAssignment.Dto.UserDto;
import com.example.GreenStitchBackendAssignment.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {
    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signupUser(@RequestBody SignupDto signupDto) {
        UserDto createdUser = authService.createUser(signupDto);
        if(createdUser == null) {
            return new ResponseEntity("User not created !!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("User created", HttpStatus.CREATED);
    }
}

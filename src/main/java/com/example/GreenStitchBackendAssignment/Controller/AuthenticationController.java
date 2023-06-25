package com.example.GreenStitchBackendAssignment.Controller;

import com.example.GreenStitchBackendAssignment.Dto.AuthenticationDto;
import com.example.GreenStitchBackendAssignment.Dto.AuthenticationResponse;
import com.example.GreenStitchBackendAssignment.Exception.IncorrectEmailAndPassword;
import com.example.GreenStitchBackendAssignment.Exception.UserNotActivated;
import com.example.GreenStitchBackendAssignment.Exception.UserNotFound;
import com.example.GreenStitchBackendAssignment.Service.JWT.UserDetailsServiceImpl;
import com.example.GreenStitchBackendAssignment.Util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationDto authenticationDto, HttpServletResponse response) throws IncorrectEmailAndPassword, UserNotActivated, UserNotFound, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDto.getEmail(), authenticationDto.getPassword()));
        }
        catch (IncorrectEmailAndPassword e) {
            throw new IncorrectEmailAndPassword("Incorrect email and password !!");
        }
        catch (UserNotActivated e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return null;
        }

        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authenticationDto.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new AuthenticationResponse(jwt);
    }
}

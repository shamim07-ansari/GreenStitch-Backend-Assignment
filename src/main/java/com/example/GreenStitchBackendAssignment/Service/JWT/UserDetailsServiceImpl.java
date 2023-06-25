package com.example.GreenStitchBackendAssignment.Service.JWT;

import com.example.GreenStitchBackendAssignment.Entity.User;
import com.example.GreenStitchBackendAssignment.Exception.UserNotFound;
import com.example.GreenStitchBackendAssignment.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFound {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UserNotFound("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}

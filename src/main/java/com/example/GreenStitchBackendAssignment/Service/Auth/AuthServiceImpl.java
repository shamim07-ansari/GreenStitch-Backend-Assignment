package com.example.GreenStitchBackendAssignment.Service.Auth;

import com.example.GreenStitchBackendAssignment.Dto.SignupDto;
import com.example.GreenStitchBackendAssignment.Dto.UserDto;
import com.example.GreenStitchBackendAssignment.Entity.User;
import com.example.GreenStitchBackendAssignment.Repository.UserRepository;
import com.example.GreenStitchBackendAssignment.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDto createUser(SignupDto signupDto) {

        // save the user in database
        User user = new User();
        user.setName(signupDto.getName());
        user.setEmail(signupDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDto.getPassword()));
        User savedUser = userRepository.save(user);

        // convert savedUser to userDto
        UserDto userDto = new UserDto();
        userDto.setName(savedUser.getName());
        userDto.setEmail(savedUser.getEmail());
        return userDto;
    }
}

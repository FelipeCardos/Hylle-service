package com.hylle.service;

import com.hylle.dto.UserResponseDTO;
import com.hylle.model.User;
import com.hylle.repository.UserRepository;
import com.hylle.utils.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OAuthService {
    private final UserRepository userRepository;
    private JwtUtil jwtUtil;

    public OAuthService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.jwtUtil = new JwtUtil();

    }

    public UserResponseDTO signUserIn(UserDetails userDetails){
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());

        return buildUserResponseDTO(user.get());
    }

    private UserResponseDTO buildUserResponseDTO(User user){
        String token = jwtUtil.generateToken(user.getUsername());
        return UserResponseDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .username(user.getUsername())
                .token(token)
                .build();
    }
}

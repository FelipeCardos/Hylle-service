package com.hylle.service.oauth;

import com.hylle.dto.user.UserResponseDTO;
import com.hylle.model.user.User;
import com.hylle.repository.user.UserRepository;
import com.hylle.utils.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OAuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public OAuthService(UserRepository userRepository, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;

    }

    public UserResponseDTO signUserIn(UserDetails userDetails){
        User user = userRepository.findByUsername(userDetails.getUsername());

        return buildUserResponseDTO(user);
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

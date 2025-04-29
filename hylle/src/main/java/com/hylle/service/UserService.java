package com.hylle.service;

import com.hylle.dto.UserDTO;
import com.hylle.dto.UserResponseDTO;
import com.hylle.exception.UserAlreadyExistsException;
import com.hylle.model.User;
import com.hylle.repository.UserRepository;
import com.hylle.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtUtil = new JwtUtil();
    }

    public UserResponseDTO createUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.email()).isPresent()) {
            throw new UserAlreadyExistsException("Email already registered.");
        }
        if (userRepository.findByUsername(userDTO.username()).isPresent()) {
            throw new UserAlreadyExistsException("Username already registered.");
        }

        User user = new User();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        user.setUsername(userDTO.username());

        return buildUserResponseDTO(userRepository.save(user));
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

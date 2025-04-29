package com.hylle.controller;

import com.hylle.dto.UserDTO;
import com.hylle.dto.UserResponseDTO;
import com.hylle.model.User;
import com.hylle.service.UserService;
import com.hylle.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserResponseDTO user = userService.createUser(userDTO);

        return ResponseEntity.ok(user);
    }
}
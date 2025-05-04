package com.hylle.resource;

import com.hylle.dto.UserDTO;
import com.hylle.dto.UserResponseDTO;
import com.hylle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserResource {
    private final UserService userService;

    @Autowired


    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserResponseDTO user = userService.createUser(userDTO);

        return ResponseEntity.ok(user);
    }
}
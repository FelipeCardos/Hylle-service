package com.hylle.resource.user;

import com.hylle.dto.user.UserDTO;
import com.hylle.dto.user.UserResponseDTO;
import com.hylle.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Tag(name = "User")
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
package com.hylle.resource.user;

import com.hylle.dto.shelf.ShelfResponseDTO;
import com.hylle.dto.user.UserDTO;
import com.hylle.dto.user.UserResponseDTO;
import com.hylle.service.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.annotations.WhereJoinTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "User")
public class UserResource {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserResponseDTO user = userService.createUser(userDTO);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/shelves")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<ShelfResponseDTO>> getUsersShelves(Authentication authentication){
        String username = authentication.getName();
        List<ShelfResponseDTO> shelfResponseDTO = userService.getShelves(username);

        return ResponseEntity.ok(shelfResponseDTO);
    }
}
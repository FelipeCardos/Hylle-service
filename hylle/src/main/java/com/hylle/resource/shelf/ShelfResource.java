package com.hylle.resource.shelf;

import com.hylle.dto.shelf.ShelfDTO;
import com.hylle.dto.shelf.ShelfResponseDTO;
import com.hylle.service.shelf.ShelfService;
import com.hylle.utils.JwtUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@Tag(name = "Shelf")
@RestController
@RequestMapping("/shelf")
public class ShelfResource {
    private final ShelfService shelfService;
    private final JwtUtil jwtUtil;

    public ShelfResource(ShelfService shelfService, JwtUtil jwtUtil){
        this.shelfService = shelfService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping()
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ShelfResponseDTO> createShelf(@RequestBody ShelfDTO shelfDTO,
                                                        Authentication authentication) {
        String username = authentication.getName();
        ShelfResponseDTO shelfResponseDTO = shelfService.createShelf(shelfDTO, username);
        return ResponseEntity.ok(shelfResponseDTO);
    }
}

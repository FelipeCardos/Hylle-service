package com.hylle.resource.oauth;

import com.hylle.dto.oauth.LoginRequestDTO;
import com.hylle.dto.user.UserResponseDTO;
import com.hylle.service.oauth.OAuthService;
import com.hylle.utils.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Tag(name = "OAuth")
@RestController
@RequestMapping("/auth")
public class OAuthResource {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final OAuthService oAuthService;

    public OAuthResource(JwtUtil jwtUtil, UserDetailsService userDetailsService, OAuthService oAuthService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.oAuthService = oAuthService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.getUsername());
        UserResponseDTO userResponseDTO = oAuthService.signUserIn(userDetails);
        return ResponseEntity.ok(userResponseDTO);
    }
}

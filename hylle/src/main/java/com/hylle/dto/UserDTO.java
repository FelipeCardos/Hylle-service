package com.hylle.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank(message = "Name is mandatory") String name,
        @Email(message = "E-mail inválido") @NotBlank(message = "O e-mail é obrigatório") String email,
        @NotBlank(message = "Password is mandatory") String password,
        @NotBlank(message = "Username is mandatory") String username
) {}

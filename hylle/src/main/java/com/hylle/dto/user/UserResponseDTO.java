package com.hylle.dto.user;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponseDTO {
    String name;
    String email;
    String username;
    String token;
}

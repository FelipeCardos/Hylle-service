package com.hylle.service.user;

import com.hylle.dto.shelf.ShelfResponseDTO;
import com.hylle.dto.user.UserDTO;
import com.hylle.dto.user.UserResponseDTO;
import com.hylle.exception.UserAlreadyExistsException;
import com.hylle.model.shelf.Shelf;
import com.hylle.model.user.User;
import com.hylle.repository.shelf.ShelfRepository;
import com.hylle.repository.user.UserRepository;
import com.hylle.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ShelfRepository shelfRepository;

    public UserResponseDTO createUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.email()) != null) {
            throw new UserAlreadyExistsException("Email already registered.");
        }
        if (userRepository.findByUsername(userDTO.username()) != null) {
            throw new UserAlreadyExistsException("Username already registered.");
        }

        User user = new User();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        user.setUsername(userDTO.username());

        return buildUserResponseDTO(userRepository.save(user));
    }

    public List<ShelfResponseDTO> getShelves(String username){
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }

        List<Shelf> userShelves = shelfRepository.findAllByUserId(user.getId());
        return userShelves.stream()
                .map(this::toShelfResponse)
                .toList();
    }

    private ShelfResponseDTO toShelfResponse(Shelf shelf){
        long bookQuantity = shelfRepository.countBooksByShelfId(shelf.getId());

        return ShelfResponseDTO.builder()
                .id(shelf.getId())
                .name(shelf.getName())
                .bookQuantity(bookQuantity)
                .build();
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

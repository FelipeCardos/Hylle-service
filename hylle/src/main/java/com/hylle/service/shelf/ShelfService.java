package com.hylle.service.shelf;

import com.hylle.dto.shelf.ShelfDTO;
import com.hylle.dto.shelf.ShelfResponseDTO;
import com.hylle.exception.ShelfAlreadyExistsException;
import com.hylle.model.shelf.Shelf;
import com.hylle.model.user.User;
import com.hylle.repository.shelf.ShelfRepository;
import com.hylle.repository.shelfBook.ShelfBookRepository;
import com.hylle.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelfService {
    private final ShelfRepository shelfRepository;
    private final UserRepository userRepository;
    private final ShelfBookRepository shelfBookRepository;

    public ShelfService(ShelfRepository shelfRepository, UserRepository userRepository, ShelfBookRepository shelfBookRepository){
        this.shelfRepository = shelfRepository;
        this.userRepository = userRepository;
        this.shelfBookRepository = shelfBookRepository;
    }

    public ShelfResponseDTO createShelf(ShelfDTO shelfDTO, String username){
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        List<String> userShelvesNames = getAllUserShelvesNames(user);
        if(userShelvesNames.contains(shelfDTO.getName())){
            throw new ShelfAlreadyExistsException("Shelf already exists for this user");
        }

        Shelf shelfToSave = buildShelf(shelfDTO, user);

        return buildShelfResponse(shelfRepository.save(shelfToSave));
    }

    private List<String> getAllUserShelvesNames(User user){
        List<Shelf> userShelves = shelfRepository.findAllByUserId(user.getId());
        return userShelves.stream()
                .map(Shelf::getName)
                .toList();
    }

    private Shelf buildShelf(ShelfDTO shelfDTO, User user){
      Shelf userShelf = new Shelf();
      userShelf.setUser(user);
      userShelf.setName(shelfDTO.getName());

      return userShelf;
    }

    private ShelfResponseDTO buildShelfResponse(Shelf shelf){
       long bookQuantity = shelfRepository.countBooksByShelfId(shelf.getId());

       return ShelfResponseDTO.builder()
               .id(shelf.getId())
               .name(shelf.getName())
               .bookQuantity(bookQuantity)
               .build();
    }
}

package com.hylle.model.shelf;

import com.hylle.model.book.Book;
import com.hylle.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "shelves")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "shelf_books",
            joinColumns = @JoinColumn(name = "shelf_id"),
            inverseJoinColumns = @JoinColumn(name = "book_isbn", referencedColumnName = "isbn")
    )
    private List<Book> books;
}

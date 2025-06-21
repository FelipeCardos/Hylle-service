package com.hylle.model.book;

import com.hylle.model.shelf.Shelf;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @Column(length = 13)
    private String isbn;

    private String title;

    private String author;

    private String edition;

    @ManyToMany(mappedBy = "books")
    private List<Shelf> shelves;
}
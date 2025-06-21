package com.hylle.model.shelfBook;

import com.hylle.model.book.Book;
import com.hylle.model.shelf.Shelf;
import jakarta.persistence.*;

@Entity
@IdClass(ShelfBookId.class)
@Table(name = "shelf_books")
public class ShelfBook {

    @Id
    @ManyToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}

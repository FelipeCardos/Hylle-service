package com.hylle.repository.shelfBook;

import com.hylle.model.shelfBook.ShelfBook;
import com.hylle.model.shelfBook.ShelfBookId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfBookRepository extends JpaRepository<ShelfBook, ShelfBookId> {

    @Query("SELECT COUNT(sb) FROM ShelfBook sb WHERE sb.shelf.id = :shelfId")
    long countBooksByShelfId(@Param("shelfId") Long shelfId);
}

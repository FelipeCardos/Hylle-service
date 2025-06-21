package com.hylle.repository.shelf;

import com.hylle.model.shelf.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf,Long> {

    List<Shelf> findAllByUserId(Long userId);
    @Query("SELECT COUNT(sb) FROM ShelfBook sb WHERE sb.shelf.id = :shelfId")
    long countBooksByShelfId(@Param("shelfId") Long shelfId);

}

package com.hylle.resource.book;

import com.hylle.dto.book.GoogleBooksResponseDTO;
import com.hylle.service.book.GoogleBookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Book")
@RequestMapping("/book")
@RestController
public class BookResource {
    @Autowired
    private GoogleBookService googleBookService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<GoogleBooksResponseDTO> searchBook(@RequestParam String isbn) {
        GoogleBooksResponseDTO result = googleBookService.searchBooks(isbn);
        return ResponseEntity.ok(result);
    }
}

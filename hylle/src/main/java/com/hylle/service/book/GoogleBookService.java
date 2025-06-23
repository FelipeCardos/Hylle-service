package com.hylle.service.book;

import com.hylle.dto.book.GoogleBooksResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@Service
public class GoogleBookService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${google.api.key}")
    private String GOOGLE_API_KEY;

    private final String BASE_GOOGLE_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    public GoogleBooksResponseDTO searchBooks(String isbn) {
        String url = BASE_GOOGLE_URL + "isbn:" + isbn + "&key=" + GOOGLE_API_KEY;
        System.out.println("Requesting URL: " + url);

        ResponseEntity<GoogleBooksResponseDTO> response = restTemplate.getForEntity(url, GoogleBooksResponseDTO.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Erro ao consultar Google Books API");
        }
    }
}

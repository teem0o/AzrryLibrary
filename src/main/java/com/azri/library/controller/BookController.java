package com.azri.library.controller;

import com.azri.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<?> getAllBooks() {
        return null;
    }

    public ResponseEntity<?> getBookById(Long id) {
        return null;
    }

    public ResponseEntity<?> createBook() {
        return null;
    }

    public ResponseEntity<?> updateBook(Long id) {
        return null;
    }

    public ResponseEntity<?> deleteBook(Long id) {
        return null;
    }

}

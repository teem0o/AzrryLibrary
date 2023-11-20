package com.azri.library.service.impl;

import com.azri.library.repository.BookRepository;
import com.azri.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

//    @Autowired
//    public BookServiceImpl(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

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

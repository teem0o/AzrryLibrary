package com.azri.library.controller;

import com.azri.library.dto.BookRequest;
import com.azri.library.dto.BookResponse;
import com.azri.library.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAllBooks(@RequestParam(required = false) String title,
                                                          @RequestParam(required = false) String author,
                                                          @RequestParam(required = false) String isbn,
                                                          Pageable pageable) {
        return ResponseEntity.ok(service.getAllBooks(title, author, isbn, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(service.createBook(bookRequest));
    }

    @PutMapping
    public ResponseEntity<BookResponse> updateBook(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(service.updateBook(bookRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/borrow/{bookId}")
    public ResponseEntity<BookResponse> borrowBook(@NonNull HttpServletRequest request, @PathVariable Long bookId) {
        return ResponseEntity.ok(service.borrowBook(request, bookId));
    }

    @PatchMapping("/return/{bookId}")
    public ResponseEntity<BookResponse> returnBook(@NonNull HttpServletRequest request, @PathVariable Long bookId) {
        return ResponseEntity.ok(service.returnBook(request, bookId));
    }

}

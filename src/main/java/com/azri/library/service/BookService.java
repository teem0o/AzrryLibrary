package com.azri.library.service;

import com.azri.library.dto.BookRequest;
import com.azri.library.dto.BookResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<BookResponse> getAllBooks(String title, String author, String isbn, Pageable pageable);
    public BookResponse getBookById(Long id);
    BookResponse createBook(BookRequest bookRequest);
    BookResponse updateBook(BookRequest bookRequest);
    void deleteBook(Long id);

    BookResponse borrowBook(HttpServletRequest request, Long bookId);
    BookResponse returnBook(HttpServletRequest request, Long id);
}

package com.azri.library.service.impl;

import com.azri.library.dto.BookRequest;
import com.azri.library.dto.BookResponse;
import com.azri.library.entity.Book;
import com.azri.library.entity.Status;
import com.azri.library.entity.User;
import com.azri.library.exception.BookActivityProcessingException;
import com.azri.library.exception.BookNotFoundException;
import com.azri.library.jms.BookActivityMessage;
import com.azri.library.jms.BookActivitySenderService;
import com.azri.library.repository.BookRepository;
import com.azri.library.security.JWTService;
import com.azri.library.service.BookService;
import com.azri.library.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private ModelMapper modelMapper;
    private UserService userService;
    private final JWTService jwtService;
    private BookActivitySenderService bookActivitySenderService;

    public Page<BookResponse> getAllBooks(String title, String author, String isbn, Pageable pageable) {
        Page<Book> books = bookRepository.findBooksByCustomFilter(
                title != null ? title.toLowerCase() : null,
                author != null ? author.toLowerCase() : null,
                isbn != null ? isbn.toLowerCase() : null, pageable);
        return books.map(book -> modelMapper.map(book, BookResponse.class));

    }

    public BookResponse getBookById(Long id) {
        return bookRepository.findById(id).map(book -> modelMapper.map(book, BookResponse.class))
                .orElseThrow(() -> new BookNotFoundException(id + ""));
    }

    public BookResponse createBook(BookRequest bookRequest) {
        var book = modelMapper.map(bookRequest, Book.class);
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookResponse.class);
    }

    public BookResponse updateBook(BookRequest bookRequest) {
        bookRepository.findById(bookRequest.getId())
                .orElseThrow(() -> new BookNotFoundException(bookRequest.getId() + ""));
        var book = modelMapper.map(bookRequest, Book.class);
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookResponse.class);
    }

    public void deleteBook(Long id) {
        bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id + ""));
        bookRepository.deleteById(id);
    }

    @Override
    public BookResponse borrowBook(HttpServletRequest request, Long bookId) {
        String username = getUserName(request);
        var user = modelMapper.map(userService.getUserByUsername(username), User.class);
        var book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId + ""));
        if (book.getStatus() == Status.BORROWED) {
            throw new BookNotFoundException(bookId + " It is already borrowed");
        }
        book.setStatus(Status.BORROWED);
        book.setUser(user);
        bookRepository.save(book);
        logSavedBook(book, user, Status.BORROWED);
        return modelMapper.map(book, BookResponse.class);
    }


    @Override
    public BookResponse returnBook(HttpServletRequest request, Long bookId) {
        String username = getUserName(request);
        var user = modelMapper.map(userService.getUserByUsername(username), User.class);
        var book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId + ""));
        if (book.getUser() == null || !book.getUser().getId().equals(user.getId())) {
            throw new BookNotFoundException(bookId + " It's not your book");
        }
        if (book.getStatus() == Status.AVAILABLE) {
            throw new BookNotFoundException(bookId + " It's already returned");
        }
        book.setStatus(Status.AVAILABLE);
        if (book.getUser().getId().equals(user.getId())) {
            book.setUser(null);
        }
        bookRepository.save(book);
        logSavedBook(book, user, Status.AVAILABLE);
        return modelMapper.map(book, BookResponse.class);
    }

    private void logSavedBook(Book book, User user, Status status) {
        var bookActivityMessage = new BookActivityMessage(book.getId(), user.getId(), status);
        var objectMapper = new ObjectMapper();
        String jsonMessage;
        try {
            jsonMessage = objectMapper.writeValueAsString(bookActivityMessage);
        } catch (JsonProcessingException e) {
            throw new BookActivityProcessingException("error at serializing " + e);
        }
        bookActivitySenderService.sendBookActivity(jsonMessage);
    }

    private String getUserName(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);
        return jwtService.extractUsername(jwt);
    }

}

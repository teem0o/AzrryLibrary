package com.azri.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }
    @GetMapping("/test2")
    public String test2() {
        return "test";
    }
}

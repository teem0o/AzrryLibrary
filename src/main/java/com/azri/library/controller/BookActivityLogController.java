package com.azri.library.controller;

import com.azri.library.entity.BookActivityLog;
import com.azri.library.service.BookActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book-activity-log")
@RequiredArgsConstructor
public class BookActivityLogController {
    private final BookActivityLogService service;
    @GetMapping
    public List<BookActivityLog> getAllBookActivityLog() {
        return service.getAllBookActivityLog();
    }

}

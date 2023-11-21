package com.azri.library.service.impl;

import com.azri.library.entity.BookActivityLog;
import com.azri.library.repository.BookActivityLogRepository;
import com.azri.library.service.BookActivityLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookActivityLogServiceImpl implements BookActivityLogService {
    private final BookActivityLogRepository bookActivityLogRepository;

    @Override
    public BookActivityLog saveBookActivity(BookActivityLog bookActivityMessage) {
        return bookActivityLogRepository.save(bookActivityMessage);
    }
}

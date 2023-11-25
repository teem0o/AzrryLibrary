package com.azri.library.service;

import com.azri.library.entity.BookActivityLog;

import java.util.List;

public interface BookActivityLogService {
    BookActivityLog saveBookActivity(BookActivityLog bookActivityMessage);
    List<BookActivityLog> getAllBookActivityLog();
}

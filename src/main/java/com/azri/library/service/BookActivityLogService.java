package com.azri.library.service;

import com.azri.library.entity.BookActivityLog;

public interface BookActivityLogService {
    BookActivityLog saveBookActivity(BookActivityLog bookActivityMessage);
}

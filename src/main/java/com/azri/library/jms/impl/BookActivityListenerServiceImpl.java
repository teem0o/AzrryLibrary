package com.azri.library.jms.impl;

import com.azri.library.entity.BookActivityLog;
import com.azri.library.jms.BookActivityMessage;
import com.azri.library.service.BookActivityLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class BookActivityListenerServiceImpl {

    @Autowired
    private BookActivityLogService bookActivityLogService;

    static final Logger log =
            LoggerFactory.getLogger(BookActivityListenerServiceImpl.class);


    @JmsListener(destination = "myqueue")
    public void receiveBookActivityMessage(String message) {
        if (message != null) {
            var objectMapper = new ObjectMapper();
            BookActivityMessage bookActivityMessage;
            var bookActivityLog = new BookActivityLog();
            try {
                bookActivityMessage = objectMapper.readValue(message, BookActivityMessage.class);
                bookActivityLog.setMessage("book is now on status: " +
                        bookActivityMessage.getStatus() + " thanks to user: "
                        + bookActivityMessage.getUserId() +
                        ". book id: " + bookActivityMessage.getBookId());
                bookActivityLog.setBookId(bookActivityMessage.getBookId());
                bookActivityLog.setUserId(bookActivityMessage.getUserId());
                bookActivityLog.setStatus(bookActivityMessage.getStatus());
                bookActivityLogService.saveBookActivity(bookActivityLog);
                log.info("received message='{}'", message);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

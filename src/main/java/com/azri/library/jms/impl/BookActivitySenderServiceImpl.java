package com.azri.library.jms.impl;

import com.azri.library.jms.BookActivitySenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookActivitySenderServiceImpl implements BookActivitySenderService {
    private final JmsTemplate jmsTemplate;
    @Value("${input.queue}")
    private String myqueue;


    public void sendBookActivity(String bookActivityMessage) {
        jmsTemplate.convertAndSend(myqueue, bookActivityMessage);
    }

}

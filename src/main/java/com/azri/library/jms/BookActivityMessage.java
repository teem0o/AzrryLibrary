package com.azri.library.jms;

import com.azri.library.entity.Status;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookActivityMessage {

    private Long bookId;
    private Long userId;
    private Status status;

    @JsonCreator
    public BookActivityMessage(
            @JsonProperty("bookId") Long bookId,
            @JsonProperty("userId") String userId,
            @JsonProperty("status") String status) {
        this.bookId = bookId;
        this.userId = Long.parseLong(userId);
        this.status = Status.valueOf(status);

    }
}

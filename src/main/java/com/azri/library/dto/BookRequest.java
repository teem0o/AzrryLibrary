package com.azri.library.dto;

import com.azri.library.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Status status;
}

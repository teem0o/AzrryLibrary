package com.azri.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_activity_log")
public class BookActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long bookId;
    private Long userId;
    private Status status;
    private String message;
    private LocalDateTime createdDate = LocalDateTime.now();
}

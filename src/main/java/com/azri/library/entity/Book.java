package com.azri.library.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
@SQLDelete(sql = "UPDATE book SET deleted = 'true' WHERE id = ?")
@Where(clause = "deleted='false'")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean deleted = false;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

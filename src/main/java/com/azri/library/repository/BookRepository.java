package com.azri.library.repository;

import com.azri.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b " +
            "WHERE (:title IS NULL OR lower( b.title) LIKE %:title%) " +
            "AND (:author IS NULL OR lower( b.author) LIKE %:author%) " +
            "AND (:isbn IS NULL OR lower( b.isbn) LIKE %:isbn%)")
    Page<Book> findBooksByCustomFilter(@Param("title") String title,
                                       @Param("author") String author,
                                       @Param("isbn") String isbn,
                                       Pageable pageable);
}

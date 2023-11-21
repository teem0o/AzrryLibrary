package com.azri.library.repository;

import com.azri.library.entity.BookActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookActivityLogRepository extends JpaRepository<BookActivityLog, Long> {
}

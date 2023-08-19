package com.argus.alishevspring.Library2Boot.repositories;


import com.argus.alishevspring.Library2Boot.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByOrderByAgeOfPublishment();


    List<Book> findByTitleIgnoreCaseStartsWith(String key);

    Page<Book> findByTitle(String title, Pageable pageable);
}

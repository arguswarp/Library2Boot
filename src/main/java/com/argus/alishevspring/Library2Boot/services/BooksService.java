package com.argus.alishevspring.Library2Boot.services;


import com.argus.alishevspring.Library2Boot.models.Book;
import com.argus.alishevspring.Library2Boot.models.Person;
import com.argus.alishevspring.Library2Boot.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> index() {
        return booksRepository.findAll();
    }

    public Page<Book> index(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }

    public List<Book> indexSorted() {
        return booksRepository.findByOrderByAgeOfPublishment();
    }

    public Page<Book> index(String title, Pageable pageable) {
        return booksRepository.findByTitle(title, pageable);
    }

    public List<Book> find(String title) {
        return booksRepository.findByTitleIgnoreCaseStartsWith(title);
    }

    public Book show(int bookId) {
        return booksRepository.findById(bookId).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int bookId, Book updatedBook) {
        Book bookToBeUpdated = booksRepository.findById(bookId).get();
        updatedBook.setBookId(bookId);
        updatedBook.setOwner(bookToBeUpdated.getOwner());
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int bookId) {
        booksRepository.deleteById(bookId);
    }

    public Person showPerson(int bookId) {
        return booksRepository.findById(bookId).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void assignPerson(int bookId, Person person) {
        booksRepository.findById(bookId).ifPresent(book -> {
            book.setOwner(person);
            book.setAssignedAt(new Date());
        });
    }

    @Transactional
    public void releasePerson(int bookId) {
        booksRepository.findById(bookId).ifPresent(book -> {
            book.setAssignedAt(null);
            book.setOwner(null);
        });
    }
}

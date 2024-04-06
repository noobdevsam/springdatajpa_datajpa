package com.example.springdatajpa_datajpa.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdatajpa_datajpa.model.Book;
import com.example.springdatajpa_datajpa.repos.BookRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookDaoImpl implements BookDao {

    private final BookRepo bookRepo;
    
    public BookDaoImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepo.findBookByTitle(title).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book getById(Long id) {
        return bookRepo.getReferenceById(id);
    }

    @Override
    public Book saveNewBook(Book book) {
        return bookRepo.save(book);
    }

    @Transactional
    @Override
    public Book updateBook(Book book) {
        var found = bookRepo.getReferenceById(book.getId());
        found.setIsbn(book.getIsbn());
        found.setTitle(book.getTitle());
        found.setPublisher(book.getPublisher());
        found.setAuthorId(book.getAuthorId());
        return bookRepo.save(found);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        Pageable pageable = PageRequest.ofSize(pageSize);

        if (offset > 0) {
            pageable = pageable.withPage(offset/pageSize);
        } else {
            pageable = pageable.withPage(0);
        }

        return this.findAllBooks(pageable);
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        return bookRepo.findAll(pageable).getContent();
    }

    @Override
    public List<Book> findAllBooksSortByTitle(Pageable pageable) {
        Page<Book> bookPage = bookRepo.findAll(pageable);
        return bookPage.getContent();
    }

}

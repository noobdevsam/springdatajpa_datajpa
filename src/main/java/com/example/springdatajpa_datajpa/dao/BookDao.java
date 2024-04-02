package com.example.springdatajpa_datajpa.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.springdatajpa_datajpa.model.Book;

public interface BookDao {
    List<Book> findAllBooks();
    List<Book> findAllBooks(int pageSize, int offset);
    List<Book> findAllBooks(Pageable pageable);
    List<Book> findAllBooksSortByTitle(Pageable pageable);
    Book getById(Long id);
    Book findBookByTitle(String title);
    Book saveNewBook(Book book);
    Book updateBook(Book book);
    void deleteBookById(Long id);
}

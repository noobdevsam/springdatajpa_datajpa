package com.example.springdatajpa_datajpa.dao;

import com.example.springdatajpa_datajpa.model.Book;

public interface BookDao {
    Book getById(Long id);
    Book findBookByTitle(String title);
    Book saveNewBook(Book book);
    Book updateBook(Book book);
    void deleteBookById(Long id);
}

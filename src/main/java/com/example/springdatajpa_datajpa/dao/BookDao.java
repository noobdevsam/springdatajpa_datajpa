package com.example.springdatajpa_datajpa.dao;

import java.util.List;
import com.example.springdatajpa_datajpa.model.Book;

public interface BookDao {
    List<Book> findAllBooks();
    Book getById(Long id);
    Book findBookByTitle(String title);
    Book saveNewBook(Book book);
    Book updateBook(Book book);
    void deleteBookById(Long id);
}

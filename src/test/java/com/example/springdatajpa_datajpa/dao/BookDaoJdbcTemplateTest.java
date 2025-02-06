package com.example.springdatajpa_datajpa.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.springdatajpa_datajpa.model.Book;

@DataJpaTest
@ComponentScan(basePackages = "com.example.springdatajpa_datajpa.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoJdbcTemplateTest {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    BookDao bookDao;

    @BeforeEach
    void setUp() {
        bookDao = new BookDaoJdbcTemplate(jdbcTemplate);
    }

    @Test
    void test_getbyid() {
        var book = bookDao.getById(3L);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    void findBookByTitle() {
        var book = bookDao.findBookByTitle("Clean Code");
        assertThat(book).isNotNull();
    }

    @Test
    void saveNewBook() {
        var book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(1L);

        var saved = bookDao.saveNewBook(book);
        assertThat(saved).isNotNull();
    }
    
    @Test
    void updateBook() {
        var book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(1L);
        var saved = bookDao.saveNewBook(book);

        saved.setTitle("New Book");
        bookDao.updateBook(saved);

        var fetched = bookDao.getById(saved.getId());
        assertThat(fetched.getTitle()).isEqualTo("New Book");
    }

    @Test
    void deleteBookById() {
        var book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        var saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());
        assertThrows(EmptyResultDataAccessException.class, () -> {
            bookDao.getById(saved.getId());
        });
    }

    @Test
    void test_find_all_books() {
        var books = bookDao.findAllBooks();
        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(4);
    }

    @Test
    void test_find_all_books_page_1() {
        var books = bookDao.findAllBooks(10, 0);
        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(5);
    }

    @Test
    void test_find_all_books_page_20() {
        var books = bookDao.findAllBooks(10,20);
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    void test_find_all_books_pageable_1() {
        var books = bookDao.findAllBooks(PageRequest.of(0, 10));
        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(5);
    }

    @Test
    void test_find_all_books_pageable_20() {
        var books = bookDao.findAllBooks(PageRequest.of(10,10));
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    void test_find_all_books_page_1_sort_by_title() {
        var books = bookDao.findAllBooksSortByTitle(PageRequest.of(0,10, Sort.by(Sort.Order.desc("title"))));
        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(5);
    }
    
}

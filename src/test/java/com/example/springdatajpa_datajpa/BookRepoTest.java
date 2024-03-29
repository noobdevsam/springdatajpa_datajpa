package com.example.springdatajpa_datajpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.springdatajpa_datajpa.repos.BookRepo;

@DataJpaTest
@ComponentScan(basePackages = "com.example.springdatajpa_datajpa")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepoTest {
    
    @Autowired
    BookRepo bookRepo;

    @Test
    void test_no_exception() {
        assertNull(bookRepo.getByTitle("foosdfsjfslskjfls"));
    }

    @Test
    void test_null_param() {
        assertNull(bookRepo.getByTitle(null));
    }

    @Test
    void test_empty_result_exception() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            var book = bookRepo.readByTitle("bar");
        });
    }

    @Test
    void test_jpa_with_stream() {
        var count = new AtomicInteger();
        bookRepo.findAllByTitleNotNull().forEach((book) -> {
            count.incrementAndGet();
        });
        assertThat(count.get()).isGreaterThan(4);
    }

    @Test
    void test_async_query() throws InterruptedException, ExecutionException {
        var futureBook = bookRepo.queryByTitle("Clean Code");
        var book = futureBook.get();
        assertNotNull(book);
    }

    @Test
    void test_query_annotation() {
        var book = bookRepo.findBookByTitleWithQuery("Clean Code");
        assertNotNull(book);
    }

    @Test
    void test_named_query() {
        var book = bookRepo.findBookByTitleWithNamedQuery("Clean Code");
        assertNotNull(book);
    }

    @Test
    void test_native_query() {
        var book = bookRepo.findBookByTitleWithNamedQuery("Clean Code");
        assertNotNull(book);
    }
}
 
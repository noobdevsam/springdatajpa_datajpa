package com.example.springdatajpa_datajpa;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        // this test will fail
    }
}
 
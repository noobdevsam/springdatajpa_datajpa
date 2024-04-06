package com.example.springdatajpa_datajpa.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ComponentScan(basePackages = "com.example.springdatajpa_datajpa.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoImplTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void testFindAllAuthorsByLastName() {
        var authors = authorDao.findAllAuthorsByLastName("Smith", PageRequest.of(0, 10));
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
    }

    @Test
    void testFindAllAuthorsByLastNameSortLastNameDesc() {
        var authors = authorDao.findAllAuthorsByLastName("Smith", PageRequest.of(0, 10, Sort.by(Sort.Order.desc("firstname"))));
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
        assertThat(authors.get(0).getFirstName()).isEqualTo("Yugal");
    }

    @Test
    void testFindAllAuthorsByLastNameSortLastNameAsc() {
        var authors = authorDao.findAllAuthorsByLastName("Smith", PageRequest.of(0, 10, Sort.by(Sort.Order.asc("firstname"))));
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
        assertThat(authors.get(0).getFirstName()).isEqualTo("Ahmed");
    }

    @Test
    void testFindAllAuthorsByLastNameAllRecords() {
        var authors = authorDao.findAllAuthorsByLastName("Smith", PageRequest.of(0, 100));
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(40);
    }
}

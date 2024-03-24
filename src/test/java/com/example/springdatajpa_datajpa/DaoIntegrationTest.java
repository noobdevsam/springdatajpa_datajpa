package com.example.springdatajpa_datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.example.springdatajpa_datajpa.dao.AuthorDao;
import com.example.springdatajpa_datajpa.model.Author;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ComponentScan(basePackages = "com.example.springdatajpa_datajpa")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void test_author_getbyid() {
        Author author = authorDao.getById(1L);
        assertThat(author).isNotNull();
    }
    
    // @Test
    // void test_author_findbyname() {
    //     Author author = authorDao.findAuthorByName("Craig", "Walls");
    //     assertThat(author).isNotNull();
    // }

    @Test
    void test_author_savenew() {
        Author author = new Author();
        author.setFirstName("Aa");
        author.setLastName("Bbb");
        Author saved = authorDao.saveNewAuthor(author);

        assertThat(saved).isNotNull();
    }

    @Test
    void test_author_update_existing() {
        Author author = new Author();
        author.setFirstName("Aa");
        author.setLastName("Bbb");
        Author saved = authorDao.saveNewAuthor(author);

        saved.setLastName("Rrrr");
        Author updated = authorDao.updateAuthor(saved);
        assertThat(updated.getLastName()).isEqualTo(saved.getLastName());
    }

    @Test
    void test_author_delete() {
        Author author = new Author();
        author.setFirstName("Aa");
        author.setLastName("Bbb");
        Author saved = authorDao.saveNewAuthor(author);

        authorDao.deleteAuthorById(saved.getId());

        Author deleted = authorDao.getById(saved.getId());
        assertThat(deleted).isNull();
    }
}

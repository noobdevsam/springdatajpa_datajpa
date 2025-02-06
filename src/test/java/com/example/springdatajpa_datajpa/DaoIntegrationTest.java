package com.example.springdatajpa_datajpa;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import com.example.springdatajpa_datajpa.dao.AuthorDao;
import com.example.springdatajpa_datajpa.dao.BookDao;
import com.example.springdatajpa_datajpa.model.Author;
import com.example.springdatajpa_datajpa.model.Book;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ComponentScan(basePackages = "com.example.springdatajpa_datajpa")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Autowired
    BookDao bookDao;

    @Test
    void test_author_getbyid() {
        Author author = authorDao.getById(1L);
        assertThat(author).isNotNull();
    }
    
    @Test
    void test_author_findbyname() {
        Author author = authorDao.findAuthorByName("Craig", "Walls");
        assertThat(author).isNotNull();
    }

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

        assertThrows(NoSuchElementException.class, () -> {
            authorDao.getById(saved.getId());
        });
    }

    @Test
    void test_book_getbyid() {
        var book = bookDao.getById(3L);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    void test_book_findbytitle() {
        var book = bookDao.findBookByTitle("Clean Code");
        assertThat(book).isNotNull();
    }

    @Test
    void test_book_savenew() {
        var book = new Book();
        book.setTitle("my book");
        book.setPublisher("self-2");
        book.setIsbn("4535353");
        book.setAuthorId(1L);

        var saved = bookDao.saveNewBook(book);
        assertThat(saved).isNotNull();
    }

    @Test
    void test_book_update_existing() {
        var book = new Book();
        book.setTitle("my book");
        book.setPublisher("self-2");
        book.setIsbn("4535353");
        book.setAuthorId(1L);

        var saved = bookDao.saveNewBook(book);
        
        saved.setIsbn("9898989");
        var updated = bookDao.updateBook(saved);
        assertThat(updated.getIsbn()).isEqualTo(saved.getIsbn());
    }

    @Test
    void test_book_delete() {
        var book = new Book();
        book.setTitle("my sfsfsfbook");
        book.setPublisher("seldsffsf-2");
        book.setIsbn("4535353");
        book.setAuthorId(1L);

        var saved = bookDao.saveNewBook(book);
        bookDao.deleteBookById(saved.getId());

        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            bookDao.getById(saved.getId());
        });
    }
}

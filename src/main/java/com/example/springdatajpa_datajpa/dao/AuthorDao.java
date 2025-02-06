package com.example.springdatajpa_datajpa.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.springdatajpa_datajpa.model.Author;

public interface AuthorDao {
    Author getById(Long id);
    Author findAuthorByName(String firstName, String lastName);
    List<Author> findAllAuthorsByLastName(String lastname, Pageable pageable);
    Author saveNewAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthorById(Long id);
}

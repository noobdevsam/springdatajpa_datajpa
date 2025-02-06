package com.example.springdatajpa_datajpa.repos;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdatajpa_datajpa.model.Author;

public interface AuthorRepo extends JpaRepository<Author, Long> {
    //jpa custom query methods
    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);

    Page<Author> findAuthorByLastName(String lastName, Pageable pageable);
}

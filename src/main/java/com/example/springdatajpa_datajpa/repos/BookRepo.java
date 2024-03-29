package com.example.springdatajpa_datajpa.repos;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import com.example.springdatajpa_datajpa.model.Book;

public interface BookRepo extends JpaRepository<Book, Long>{
    Optional<Book> findBookByTitle(String title);
    Book readByTitle(String title);

    @Nullable
    Book getByTitle(@Nullable String title);

    Stream<Book> findAllByTitleNotNull();
}

package com.example.springdatajpa_datajpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdatajpa_datajpa.model.Author;

public interface AuthorRepo extends JpaRepository<Author, Long> {
}

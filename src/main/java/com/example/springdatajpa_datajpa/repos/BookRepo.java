package com.example.springdatajpa_datajpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdatajpa_datajpa.model.Book;

public interface BookRepo extends JpaRepository<Book, Long>{
}

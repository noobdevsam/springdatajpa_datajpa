package com.example.springdatajpa_datajpa.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.springdatajpa_datajpa.model.Author;

public class AuthorDaoJdbcTemplate implements AuthorDao{

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getById(Long id) {
        return null;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<Author> findAllAuthorsByLastName(String lastname, Pageable pageable) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from author where last_name = ? ");

        if (pageable.getSort().getOrderFor("firstname") != null) {
            sb.append("order by first_name ").append(pageable.getSort().getOrderFor("firstname").getDirection().name());
        }

        sb.append(" limit ? offset ?");
        return jdbcTemplate.query(sb.toString(), getAuthorMapper(), lastname, pageable.getPageSize(), pageable.getOffset());
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {
        
    }

    private AuthorMapper getAuthorMapper() {
        return new AuthorMapper();
    }
    
}

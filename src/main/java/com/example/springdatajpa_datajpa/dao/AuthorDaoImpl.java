package com.example.springdatajpa_datajpa.dao;

import org.springframework.stereotype.Service;

import com.example.springdatajpa_datajpa.model.Author;
import com.example.springdatajpa_datajpa.repos.AuthorRepo;

@Service
public class AuthorDaoImpl implements AuthorDao{

    private final AuthorRepo authorRepo;

    public AuthorDaoImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @SuppressWarnings("null")
    @Override
    public Author getById(Long id) {
        return authorRepo.findById(id).orElseGet(null);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return authorRepo.findAuthorByFirstNameAndLastName(firstName, lastName);
    }

    @SuppressWarnings("null")
    @Override
    public Author saveNewAuthor(Author author) {
        return authorRepo.save(author);
    }

    @SuppressWarnings("null")
    @Override
    public Author updateAuthor(Author author) {
        Author update = authorRepo.findById(author.getId()).get();
        update.setFirstName(author.getFirstName());
        update.setLastName(author.getLastName());
        return authorRepo.save(update);
    }

    @SuppressWarnings("null")
    @Override
    public void deleteAuthorById(Long id) {
        authorRepo.deleteById(id);
    }
    
}

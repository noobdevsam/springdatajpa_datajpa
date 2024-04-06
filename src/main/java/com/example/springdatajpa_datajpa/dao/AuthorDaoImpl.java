package com.example.springdatajpa_datajpa.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.springdatajpa_datajpa.model.Author;
import com.example.springdatajpa_datajpa.repos.AuthorRepo;

@Service
public class AuthorDaoImpl implements AuthorDao{

    private final AuthorRepo authorRepo;

    public AuthorDaoImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author getById(Long id) {
        return authorRepo.findById(id).get();
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return authorRepo.findAuthorByFirstNameAndLastName(firstName, lastName).orElseGet(null);
    }

    @Override
    public List<Author> findAllAuthorsByLastName(String lastname, Pageable pageable) {
        return authorRepo.findAuthorByLastName(lastname, pageable).getContent();
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        Author update = authorRepo.findById(author.getId()).get();
        update.setFirstName(author.getFirstName());
        update.setLastName(author.getLastName());
        return authorRepo.save(update);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepo.deleteById(id);
    }
    
}

package com.example.springdatajpa_datajpa.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.springdatajpa_datajpa.model.Author;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class AuthorDaoHibernate implements AuthorDao{

    private final EntityManagerFactory emf;

    public AuthorDaoHibernate(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
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
        EntityManager em = getEntityManager();

        try {
            String hql = "SELECT a FROM Author a WHERE a.lastName = :lastName ";
            
            if (pageable.getSort().getOrderFor("firstname") != null) {
                hql = hql + "ORDER BY a.firstName " + pageable.getSort().getOrderFor("firstname").getDirection().name();
            }

            TypedQuery<Author> query = em.createQuery(hql, Author.class);
            query.setParameter("lastName", lastname);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());

            return query.getResultList();
        } finally {
            em.close();
        }
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
    
}

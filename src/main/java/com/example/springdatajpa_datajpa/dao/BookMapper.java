package com.example.springdatajpa_datajpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.example.springdatajpa_datajpa.model.Book;

public class BookMapper implements RowMapper<Book>{

    @SuppressWarnings("null")
    @Override
    @Nullable
    public Book mapRow(ResultSet arg0, int arg1) throws SQLException {
        var book = new Book();
        book.setId(arg0.getLong(1));
        book.setIsbn(arg0.getString(2));
        book.setPublisher(arg0.getString(3));
        book.setTitle(arg0.getString(4));
        book.setAuthorId(arg0.getLong(5));
        return book;
    }
    
}

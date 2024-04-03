package com.example.springdatajpa_datajpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import com.example.springdatajpa_datajpa.model.Author;

public class AuthorMapper implements RowMapper<Author>{

    @Override
    @Nullable
    public Author mapRow(ResultSet arg0, int arg1) throws SQLException {
        var author = new Author();
        author.setId(arg0.getLong("id"));
        author.setFirstName(arg0.getString("first_name"));
        author.setLastName(arg0.getString("last_name"));
        return author;
    }

}

package com.nf.empst.dao.impl;

import com.nf.empst.dao.BookDAO;
import com.nf.empst.entity.Book;

import java.util.List;

public class MySQLBookDAO implements BookDAO {
    @Override
    public List<Book> getByName(String name) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public void persist(Book book) {

    }

    @Override
    public void remove(String name) {

    }
}

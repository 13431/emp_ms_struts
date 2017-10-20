package com.nf.empst.dao;


import com.nf.empst.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getByName(String name);

    List<Book> getAll();

    void persist(Book book);

    void remove(String name);
}

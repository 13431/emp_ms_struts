package com.nf.empst.dao.impl;

import com.nf.empst.dao.BookDAO;
import com.nf.empst.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class MemoryBookDAO implements BookDAO {
    // 使用 ArrayList 模拟数据库
    private static List<Book> books = new ArrayList<>();

    @Override
    public List<Book> getByName(String name) {
        List<Book> newbooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getName().contains(name)) {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public void persist(Book book) {
        books.add(book);
    }

    @Override
    public void remove(String name) {
        List<Book> bookList = new ArrayList<>();
        for (Book book : books) {
            if (!book.getName().equalsIgnoreCase(name)) {
                bookList.add(book);
            }
        }
        books = bookList;
    }
}

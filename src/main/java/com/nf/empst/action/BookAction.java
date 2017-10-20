package com.nf.empst.action;


import com.nf.empst.dao.BookDAO;
import com.nf.empst.dao.impl.MemoryBookDAO;
import com.nf.empst.dao.impl.OracleBookDAO;
import com.nf.empst.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAction {

    private BookDAO bookDAO = new OracleBookDAO();

    // 初始化书籍信息
    public String bookinit() throws Exception {
        bookDAO.persist(new Book("java 编程思想", 222F));
        bookDAO.persist(new Book("JS Good Part", 22F));
        bookDAO.persist(new Book("Spring in Action", 33F));
        bookDAO.persist(new Book("南方it学院Oracle", 9293F));
        return "success";
    }

    // 读取书籍列表
    private List<Book> books = new ArrayList<>();

    public String booklist() throws Exception {
        books = bookDAO.getAll();
        return "success";
    }


    // 删除书籍
    private String bookName;

    public String bookdel() throws Exception {
        bookDAO.remove(bookName);
        return "success";
    }


    // 增加书籍
    private Book book;

    public String booksave() throws Exception {
        bookDAO.persist(book);
        return "success";
    }


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}

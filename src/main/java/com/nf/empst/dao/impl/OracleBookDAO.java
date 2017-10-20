package com.nf.empst.dao.impl;

import com.nf.empst.dao.BookDAO;
import com.nf.empst.entity.Book;
import com.nf.empst.entity.Employee;
import com.nf.empst.util.EMUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class OracleBookDAO extends BaseDAO<Book> implements BookDAO {

    @Override
    public List<Book> getByName(String name) {
        EntityManager entityManager = EMUtil.getEntityManager();

        String jpql = "from Book where name like :name";
        List<Book> books = entityManager.createQuery(jpql, Book.class)
                .setParameter("name", name.toUpperCase() + "%")
                .getResultList();

        entityManager.close();

        return books;
    }

    @Override
    public void remove(String name) {

    }
}

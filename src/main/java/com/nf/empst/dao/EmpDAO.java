package com.nf.empst.dao;


import com.nf.empst.entity.Employee;
import com.nf.empst.util.CommonUtil;
import com.nf.empst.util.EMUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import java.util.List;


public class EmpDAO extends BaseDAO<Employee> {

    public List<Employee> queryByEname(String ename) {
        EntityManager entityManager = EMUtil.getEntityManager();

        String jpql = "from Employee where name like :name";
        List<Employee> employees = entityManager.createQuery(jpql, Employee.class)
                .setParameter("name", ename.toUpperCase() + "%")
                .getResultList();

        entityManager.close();

        return employees;
    }

    public List<Employee> criteriaByConditions(String ename2, String lowsal, String hisal) {
        EntityManager entityManager = EMUtil.getEntityManager();
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Employee.class);

        List<Employee> employees;
        if (CommonUtil.notempty(ename2))
            criteria.add(Restrictions.like("name", ename2.toUpperCase() + "%"));

        if (CommonUtil.notempty(lowsal))
            criteria.add(Restrictions.ge("salary", Float.parseFloat(lowsal)));

        if (CommonUtil.notempty(hisal))
            criteria.add(Restrictions.le("salary", Float.parseFloat(hisal)));

        employees = criteria.addOrder(Order.desc("salary")).list();

        entityManager.close();
        return employees;
    }
}

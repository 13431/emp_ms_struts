package com.nf.empst.dao;

import com.nf.empst.entity.Employee;

import java.io.Serializable;
import java.util.List;

public interface EmpDAO {
    Employee getById(Serializable id);
    List<Employee> getAll();

    void persist(Employee employee);
    void merge(Employee employee);
    void remove(Serializable id);

    List<Employee> queryByEname(String name);
    List<Employee> criteriaByConditions(String name, String lowsal, String hisal);
}

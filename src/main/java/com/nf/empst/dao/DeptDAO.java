package com.nf.empst.dao;

import com.nf.empst.entity.Department;
import com.nf.empst.entity.Employee;

import java.io.Serializable;
import java.util.List;

public interface DeptDAO {
    Department getById(long id);
    List<Department> getAll();

    void persist(Department department);
    void merge(Department department);
    void remove(Serializable id);
}

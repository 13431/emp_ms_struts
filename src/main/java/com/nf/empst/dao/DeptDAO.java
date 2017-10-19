package com.nf.empst.dao;


import com.nf.empst.entity.Department;
import com.nf.empst.util.EMUtil;

import javax.persistence.EntityManager;

public class DeptDAO extends BaseDAO<Department> {
    public Department getById(long deptno) {
        EntityManager em = EMUtil.getEntityManager();
        Department department = em.find(Department.class, deptno);
        department.getEmployees().size();
        em.close();
        return department;
    }

}

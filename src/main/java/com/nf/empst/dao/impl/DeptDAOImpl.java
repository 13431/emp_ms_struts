package com.nf.empst.dao.impl;


import com.nf.empst.dao.DeptDAO;
import com.nf.empst.entity.Department;
import com.nf.empst.util.EMUtil;

import javax.persistence.EntityManager;

public class DeptDAOImpl extends BaseDAO<Department> implements DeptDAO {

    @Override
    public Department getById(long deptno) {
        EntityManager em = EMUtil.getEntityManager();
        Department department = em.find(Department.class, deptno);
        department.getEmployees().size();
        em.close();
        return department;
    }

}

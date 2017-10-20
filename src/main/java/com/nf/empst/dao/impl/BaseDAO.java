package com.nf.empst.dao.impl;


import com.nf.empst.util.EMUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class BaseDAO<T> {
    private Class<T> entityClazz = null;

    public BaseDAO() {
        // 得到 T 的类类型
        this.entityClazz = (Class<T>)
                ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    /**
     * 根据主键获取
     */
    public T getById(Serializable id) {
        EntityManager em = EMUtil.getEntityManager();

        T entity = em.find(entityClazz, id);
        em.close();
        return entity;
    }

    /**
     * 获取所有
     */
    public List<T> getAll() {
        EntityManager em = EMUtil.getEntityManager();
        List<T> resList = em.unwrap(Session.class).createCriteria(entityClazz).list();
        em.close();
        return resList;
    }

    /**
     * GetALl
     */
    public long countAll() {
        EntityManager em = EMUtil.getEntityManager();
        String jpql = "select count(*) from " + entityClazz.getSimpleName();
        return (long) em.createQuery(jpql).getSingleResult();
    }

    /**
     * 保存
     */
    public void persist(T entity) {
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * 更新
     */
    public void merge(T entity) {
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * 删除
     */
    public void remove(T entity) {
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * remove By Id
     */
    public void remove(Serializable id) {
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(entityClazz, id));
        em.getTransaction().commit();
        em.close();
    }
}

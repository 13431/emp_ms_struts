package com.nf.empst.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 获取 {@link EntityManager} 的辅助类
 */
public class EMUtil {
    private EntityManagerFactory entityManagerFactory;
    private static EMUtil instance;

    private EMUtil() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Development_Environment");
    }

    public static EMUtil getInstance() {
        if (instance == null) {
            instance = new EMUtil();
        }
        return instance;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return getInstance().entityManagerFactory;
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
}

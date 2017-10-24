package demo.mvc;

import com.nf.empst.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CCCService {

    public Employee getEmp(String name) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Development_Environment");
        EntityManager entityManager = factory.createEntityManager();
        Employee king = entityManager.createQuery("from Employee where name=:name", Employee.class)
                .setParameter("name", name).getSingleResult();

        entityManager.close();
        factory.close();
        return king;
    }
}

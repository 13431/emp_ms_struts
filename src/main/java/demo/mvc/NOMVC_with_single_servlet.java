package demo.mvc;


import com.nf.empst.dao.EmpDAO;
import com.nf.empst.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/aaa.html")
public class NOMVC_with_single_servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String name = req.getParameter("name");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Development_Environment");
        EntityManager entityManager = factory.createEntityManager();
        Employee king = entityManager.createQuery("from Employee where name=:name", Employee.class)
                .setParameter("name", name).getSingleResult();

        entityManager.close();
        factory.close();

        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");

        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>员工信息 from Servlet</h1>");
        writer.println("<div>" + king.getName() + "/" + king.getSalary() + "</div>");
        writer.println("</body>");
        writer.println("</html>");
        writer.flush();
        writer.close();
    }
}

package demo.mvc;

import com.nf.empst.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bbb.html")
public class MVC_Model_AND_Controller_Servlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String name = req.getParameter("name");

        Employee king = new CCCService().getEmp(name);

        req.setAttribute("king", king);
        if (king == null) {
            //
        } else if (king.getSalary() > 3000F) {
            req.getRequestDispatcher("/demo/MVC_view.jsp").forward(req, resp);
        } else {
            //
        }
    }
}
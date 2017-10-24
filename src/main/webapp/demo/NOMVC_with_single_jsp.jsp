<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="javax.persistence.Persistence" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="com.nf.empst.entity.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");

    String name = request.getParameter("name");

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Development_Environment");
    EntityManager entityManager = factory.createEntityManager();
    Employee king = entityManager.createQuery("from Employee where name=:name", Employee.class)
            .setParameter("name", name).getSingleResult();

    entityManager.close();
    factory.close();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>员工信息 from JSP</h1>

<div>
    <%= king.getName() %> | <%= king.getSalary() %>
</div>

</body>
</html>

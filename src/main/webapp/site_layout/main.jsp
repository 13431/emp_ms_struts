<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html>
<html>
<head>
    <style>

    </style>
    <title><decorator:title /></title>
    <decorator:head />
</head>

<body>
<%@ include file="header.jsp" %>

<div style="display: flex; flex-flow: row nowrap;">
    <aside>
        <%@include file="aside.jsp" %>
    </aside>
    <div>
        <decorator:body />
    </div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>
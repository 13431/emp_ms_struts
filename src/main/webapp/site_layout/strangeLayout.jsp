<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html>
<head>
    <title><decorator:title /></title>
    <style>
        html, body {
            margin: 0;
            padding: 0;
        }
    </style>
    <decorator:head />
</head>
<body>

<div style="border: 50px solid green; padding: 10px 30px; min-height: 100vh;">
    <decorator:body />
</div>

</body>
</html>

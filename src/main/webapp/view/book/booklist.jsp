<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>


<a href="/bookinit.php">初始化书籍</a>
<a href="/bookadd.php">新增书籍</a>

<ul>
    <s:iterator value="books" var="b" status="s">
        <li>
            <span>${s.index}:   ${b.name}    |    ${b.price}</span>
            <a href="/bookdel.php?bookName=${b.name}">删除书籍</a>
        </li>
    </s:iterator>
</ul>

</body>
</html>
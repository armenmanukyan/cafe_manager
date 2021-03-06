<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../../static/css/my.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <title>Manager</title>
</head>
<body>
<div class="container">
    <jsp:include page="managerChooser.jsp"/>
<br>
<br>
<br>
<h2>Waiters</h2>
<table class="table">
    <thead>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${waiters}" var="user">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td><a href="/manager/waiter/delete/${user.id}" type="button" class="btn btn-sm btn-danger">delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <br><br><br><br>

<h2>Tables</h2>

<table class="table">
    <thead>
    <tr>
        <th>Number Id</th>
        <th>Waiter Id</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${tables}" var="table">
        <tr>
            <td>${table.number}</td>
            <td>${table.getUserId()}</td>
            <td><a href="table/update/${table.id}" type="button" class="btn btn-sm btn-warning">Update table</a></td>
            </a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br><br>


</div>
</body>
</html>

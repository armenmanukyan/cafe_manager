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
    <title>Update Table</title>
</head>
<body>
<div class="container">
    <jsp:include page="managerChooser.jsp"/>
    <h2>Update Table</h2>
    <form:form action="${table.getId()}" method="post">
        <div class="form-group">
            <label for="user">Please select an user:</label>
            <select class="form-control" id="user" name="id">
                <c:forEach items="${waiters}" var="user">
                    <option name="id" value="${user.getId()}">${user.getFirstName()} ${user.getLastName()}</option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-sm btn-success">Update</button>
        </div>
    </form:form>
</div>
</body>
</html>


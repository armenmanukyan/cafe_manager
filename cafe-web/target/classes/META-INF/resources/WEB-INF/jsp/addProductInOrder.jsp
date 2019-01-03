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

    <title>Add Product </title>
</head>
<body>
<div class="container">
    <jsp:include page="managerChooser.jsp"/>
    <h2>Update Table</h2>
    <form:form action="${order.getId()}" method="post">
        <div class="form-group">
            <label for="product">Please select a product:</label>
            <select class="form-control" id="product" name="id">
                <c:forEach items="${products}" var="product">
                    <option name="id" value="${product.getId()}">${product.getName()}</option>
                </c:forEach>
            </select>
            <input type="number" name="amount" placeholder="amount" required/>
            <button type="submit" class="btn btn-sm btn-success">Add</button>
        </div>
    </form:form>
</div>
</body>
</html>

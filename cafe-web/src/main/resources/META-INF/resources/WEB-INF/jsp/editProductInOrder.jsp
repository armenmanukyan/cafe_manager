<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>Update product in order </title>
</head>
<body>
<div class="container">
    <h2>Update product in order</h2>
    <form:form action="${product.getId()}" method="post">
        <div class="form-group">
            <label for="product">Please select a status:</label>
            <select class="form-control" id="product" name="id">
                <c:forEach items="${statuses}" var="status">
                    <option name="status" value="${status.name()}">${status.name()}</option>
                </c:forEach>
            </select>
            <input type="number" name="amount" placeholder="amount"/>
            <c:param name="orderId" value="${product.getOrder().getId()}"></c:param>
            <button type="submit" class="btn btn-sm btn-success">Save</button>
        </div>
    </form:form>
</div>
</body>
</html>


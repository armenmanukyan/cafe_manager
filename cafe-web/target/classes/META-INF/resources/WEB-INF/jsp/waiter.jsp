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
    <title>Waiter</title>
</head>
<body>
<div class="container">
    <div class="row">
        <h1>Tables</h1>
    </div>
    <div class="col-sm-8">
        <div class="pull-right">
            <ul class="nav navbar-nav">
                <li><a href="/logout"><i class="fa fa-lock"></i> Logout</a></li>

            </ul>
        </div>
    </div>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>Table Id</th>
                <th>Add order</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="table" items="${tables}">
                <tr>
                    <td>${table.id}</td>
                    <td><a href="order/add/${table.id}">
                        <button type="submit" class="btn btn-sm btn-success"> Add order</button>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <jsp:include page="orders.jsp"/>
    <jsp:include page="products.jsp"/>

</body>
</html>

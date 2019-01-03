<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../static/css/my.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <style>
        .error{color:red}
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="managerChooser.jsp"/>
    <br><br><br><br>
    <h2>Create New Product</h2>
    <%--@elvariable id="cafeProduct" type="com.myCafe.core.dto.CafeProduct"--%>
    <form:form action="/product" method="post" modelAttribute="cafeProduct">

        <div class="form-group">
            Name: <form:input type="text" path="name" class="form-control" id="name"/>
            <form:errors path="name" cssClass="error"/>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">Add product</button>
        </div>
    </form:form>
</div>
</body>
</html>
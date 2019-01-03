<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>

<c:set var="orderStatuses" value='<%=request.getAttribute("orderStatuses")%>'/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../../static/css/my.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <title>Update Order Status</title>
</head>
<body>
<div class="container">
    <h2>Update Order</h2>
    <form:form action="${order.getId()}" method="post" modelAttribute="order">
        <div class="form-group">
            <spring-form:select path="status">
                <spring-form:option value="" label="Please select a status:" />
                <c:forEach items="${orderStatuses}" var="element">
                        <option value="${element.type}"  ${element.type== selectedDept ? 'selected="selected"' : ''}>${element.type}</option>
                </c:forEach>
            </spring-form:select>
            <button type="submit" class="btn btn-sm btn-success">Update</button>
        </div>
    </form:form>
</div>
</body>
</html>


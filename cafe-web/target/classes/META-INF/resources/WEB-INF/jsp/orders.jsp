<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>

    <link th:href="@{/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css}" rel="stylesheet"/>
    <link th:href="@{/css/style.css}" rel="stylesheet"/>
    <title>Orders</title>
</head>
<body>
<div class="container">
    <div class="row">
        <h1>Orders</h1>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th>Order Id</th>
                <th>Table id</th>
                <th>Status</th>
                <th>Product </th>
                <th> </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tableOrders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.table.id}</td>
                    <td>${order.status}</td>
                    <td><a href="/productInOrder/add/${order.id}" type="button" class="btn btn-sm btn-success">add product</a></td>
                    <td><a href="/order/edit/${order.id}" type="button" class="btn btn-sm btn-success">edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row">
        <div class="form-group col-md-1">
            <select class="form-control pagination" id="pageSizeSelect">
                <option th:each="pageSize : ${pageSizes}" th:text="${pageSize}" th:value="${pageSize}"
                        th:selected="${pageSize} == ${selectedPageSize}"></option>
            </select>
        </div>
        <div th:if="${Orders.totalPages != 1}" class="form-group col-md-11 pagination-centered">
            <ul class="pagination">
                <li th:class="${Orders.number == 0} ? disabled">
                    <a class="pageLink" th:href="@{/(pageSize=${selectedPageSize}, page=1)}">&laquo;</a>
                </li>
                <li th:class="${Orders.number == 0} ? disabled">
                    <a class="pageLink" th:href="@{/(pageSize=${selectedPageSize}, page=${Orders.number})}">&larr;</a>
                </li>
                <li th:class="${Orders.number == (page - 1)} ? 'active pointer-disabled'"
                    th:each="page : ${numbers.sequence(pager.startPage, pager.endPage)}">
                    <a class="pageLink" th:href="@{/(pageSize=${selectedPageSize}, page=${page})}"
                       th:text="${page}"></a>
                </li>
                <li th:class="${Orders.number + 1 == Orders.totalPages} ? disabled">
                    <a class="pageLink"
                       th:href="@{/(pageSize=${selectedPageSize}, page=${Orders.number + 2})}">&rarr;</a>
                </li>
                <li th:class="${Orders.number + 1 == Orders.totalPages} ? disabled">
                    <a class="pageLink"
                       th:href="@{/(pageSize=${selectedPageSize}, page=${Orders.totalPages})}">&raquo;</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<script th:src="@{/webjars/jquery/1.11.1/jquery.min.js}"></script>
<script th:src="@{/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js}"></script>
<script th:src="@{/js/app.js}"></script>
</body>
</html>
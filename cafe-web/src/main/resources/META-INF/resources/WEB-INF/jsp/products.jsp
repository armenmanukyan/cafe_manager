<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <h1>Products</h1>
    </div>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>Product Id</th>
                <th>Order Id</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="productInOrder" items="${productsInorder}">
                <tr>
                    <td>${productInOrder.product.id}</td>
                    <td>${productInOrder.order.getId()}</td>
                    <td>${productInOrder.amount}</td>
                    <td>${productInOrder.getStatus().name()}</td>
                    <td><a href="productInOrder/edit/${productInOrder.id}">
                        <button type="submit" class="btn btn-sm btn-warning"> Edit </button>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
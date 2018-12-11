<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<c:set var="userRoles" value='<%=request.getAttribute("userRoles")%>'/>
<c:set var="errorMessage" value='<%=request.getAttribute("message")%>'/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Cafe Registration Form</title>
</head>
<body>
<h1>Sign Up</h1>
<form action="/register" method="post" id="reg">
    <table style="with: 50%">
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstName"/></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="lastName"/></td>
        </tr>
        <tr>
            <td>UserName</td>
            <td><input type="text" name="userName" id="userName"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"/></td>
        </tr>

        <tr>
            <td>Role</td>
            <td><select name="role" id="role" required>
                <option value="" selected>Choose Role</option>
                <c:forEach items="${userRoles}" var="element">
                    <option value="${element.type}"  ${element.type == selectedDept ? 'selected="selected"' : ''}>${element.type}</option>
                </c:forEach>
            </select></td>
        </tr>
        <input type="submit" value="Submit"/>
    </table>
</form>
<script src="../static/js/jquery-2.1.4.js"></script>
<script src="../static/js/signUp-velocity.min.js"></script>
<script src="../static/js/signUp-main-min.js"></script> <!-- Resource jQuery -->
<script>
    jQuery(document).ready(function ($) {
        $('.close-carbon-adv').on('click', function (event) {
            event.preventDefault();
            $('#carbonads-container').hide();
        });
    });
</script>
<script>
    $(document).ready(function () {
        $("#userName").change(function () {
            //Remove any span after the text field
            $(".aval,.exists,.wait").remove();
            //Display a loading gif image
            $("<span class='wait'></span>").insertAfter("#userName");
            var userName = $(this).val();
            if (userName != "") {
                var len = userName.length;
                if (len >= 5 && len <= 25) {
                    //Username must be 5 to 10 characters long.
                    //Change accrodangly yours
                    $.ajax({
                        url: "checkUserName",
                        data: {userName: userName},
                        type: 'POST',
                        success: function (response) {
                            var resp = $.trim(response);
                            $(".aval,.exists, .wait").remove();
                            if (resp == "exists") {
                                //If username already exists it will display the following message
                                $("<br><span style='color: red' class='exists'>User name is already exists!</span>").insertAfter("#userName");
                            } else if (resp == "notexists") {
                                //If username is available it will display the following message
                                $("<br> <span style='color: green' class='aval'>User name  is available!</span>").insertAfter("#userName");
                            }
                        }
                    });
                } else {
                    //If the given username is less than 5 or greater than 10 this warning will display
                    $(".aval,.exists, .wait").remove();
                    $("<br><span style='color: red' class='exists'>User name must be 5 to 25 characters long!</span>").insertAfter("#userName");
                }
            } else {
                //If the field is empty then remove any span after the text field
                $(".aval,.exists, .wait").remove();
            }
        });
    });

</script>


</body>
</html>
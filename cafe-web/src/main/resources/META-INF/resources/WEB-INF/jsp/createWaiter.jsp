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

    <title>Create User</title>
</head>
<body>
<div class="container">
    <jsp:include page="managerChooser.jsp"/>
</div>
<h2>Create New Waiter</h2>
<form:form action="/manager/waiter/add" method="post" id="reg">
    <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter First Name:"/>
        <label for="lastName">Last Name:</label>
        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter LastName:"/>
        <label for="userName">Username:</label>
        <input type="text" class="form-control" id="userName" name="userName" placeholder="Enter Username:"/>
        <label for="password">Password:</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password:"/>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-sm btn-success">Add Waiter</button>
    </div>
</form:form>
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

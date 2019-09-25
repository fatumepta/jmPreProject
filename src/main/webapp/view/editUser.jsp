<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
    <style>
        form {
            width: 13%;
            margin: auto;
            border: 5px;
        }
        input {
            float: right;
        }
    </style>
</head>
<body>

<%@ include file="navi.jsp" %>

<form action="/add" method="post">
    <fieldset>
        <legend><h2><b>Add user</b></h2></legend>
        name <input type="text" name="name" value=<%= request.getAttribute("name")%>>
        <p>login <input type="text" name="login" required value=<%= request.getAttribute("login")%>></p>
        <p>password <input type="password" name="password" value=<%= request.getAttribute("password")%>></p>
        <p><button style="height:30px;width:220px"><b>edit</b></button></p>
    </fieldset>
</form>

</body>
</html>

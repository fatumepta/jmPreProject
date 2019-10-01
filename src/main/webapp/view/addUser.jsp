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

    <% if (request.getAttribute("message") != null) { %>
        <h2 style="color: crimson" align="middle"><p><%= request.getAttribute("message") %></p></h2>
    <% } %>

        <form action="/add" method="post">
            <fieldset>
                <legend><h2><b>ADD</b></h2></legend>
                name <input type="text" name="name">
                <p>login <input type="text" name="login" required></p>
                <p>password <input type="password" name="password" required></p>
                <p><button style="height:30px;width:220px"><b>add</b></button></p>
            </fieldset>
        </form>

</body>
</html>

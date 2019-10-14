<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization page</title>
    <style>
        form {
            width: 13%;
            margin: auto;
            border: 5px;
        }

        input {
            float: right;
        }

        .h2-err {
            color: crimson;
            text-align: center;
        }
    </style>
</head>
<body>

<% if (request.getAttribute("message") != null) { %>
<h2 class="h2-err"><p><%= request.getAttribute("message") %>
</p></h2>
<% } %>

<form action="/login" method="post">
    <fieldset>
        <legend><h2><b>Sign in</b></h2></legend>
        login <input type="text" name="login" required>
        <p>password <input type="password" name="password" required></p><br>
        <button style="height:30px;width:220px"><b>Sign in</b></button>
    </fieldset>
</form>

</body>
</html>

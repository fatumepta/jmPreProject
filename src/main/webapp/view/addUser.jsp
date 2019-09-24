<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
    <% if (request.getAttribute("message") != null) { %>
        <h3></h3><p><%= request.getAttribute("message") %></p></h3>
    <% } %>

    <form action="/add" method="post">
        <fieldset style="width:250px" >
            <legend><b>Sign Up</b></legend>
            <p><label>name</label>
                <input type="text" name="name"></p>
            <p><label>login</label>
                <input type="text" name="login"></p>
            <p><label>password</label>
                <input type="password" name="password"></p>
            <p><button type="submit">send</button></p>
        </fieldset>
    </form>

</body>
</html>

<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>

    <% List<User> users = (List<User>) request.getAttribute("users"); %>
    <fieldset style="width:250px">
    <table width="400" border="1" cellpadding="4" cellspacing="0">
        <legend><b>List of users</b></legend>
            <tr>
                <th> id </th><th> name </th><th> login </th><th> options </th>
            </tr>
            <% for (User user: users) { %>
            <% long id = user.getId(); %>
            <tr>
                <th> <%= id %></th>
                <th> <%= user.getName() %></th>
                <th> <%= user.getLogin() %></th>
                <th>
                    <a href="/edit">edit</a>
                    <a href="/delete">delete</a>
                </th>
            </tr>
            <% } %>
    </table>
    </fieldset>

    <p><hr></p>
    <%@ include file="addUser.jsp" %>


</body>
</html>

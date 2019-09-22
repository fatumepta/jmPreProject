<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>

    <% List<User> users = (List<User>) request.getAttribute("users"); %>
    <table width="100%" border="1" cellpadding="4" cellspacing="0">
        <caption>List of users</caption>
        <tr>
            <th> id </th><th> name </th><th> login </th><th> options </th>
        </tr>
        <% for (User user: users) { %>
        <tr>
            <th> <%= user.getId() %></th>
            <th> <%= user.getName() %></th>
            <th> <%= user.getLogin() %></th>
            <th> <%= "ACTION_1 ACTION_2" %></th>
        </tr>
        <% } %>
    </>

</body>
</html>

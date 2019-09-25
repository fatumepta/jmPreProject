<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
    <style>
        fieldset {
            width: 300px;
            border: 1px;
            margin: auto;   /* Выравниваем таблицу по центру окна  */
        }
        legend {
            border: 5px;
            margin: auto;
        }
    </style>
</head>
<body>

    <%@ include file="navi.jsp" %>

    <% List<User> users = (List<User>) request.getAttribute("users"); %>
    <fieldset>
        <table width="400" border="1" cellpadding="4" cellspacing="0" ma>
            <legend><h1><b>List of users</b></h1></legend>
                <tr bgcolor="#a9a9a9">
                    <th> id </th><th> name </th><th> login </th><th> options </th>
                </tr>
                <% for (User user: users) { %>
                <% long id = user.getId(); %>
                <tr>
                    <th> <%= id %> </th>
                    <th> <%= user.getName() %> </th>
                    <th> <%= user.getLogin() %> </th>
                    <th>
                        <a href=<%= "/edit?id=" + id %>> edit </a>
                        <a href=<%= "delete?id=" + id %>> delete </a>
                    </th>
                </tr>
                <% } %>
        </table>
    </fieldset>

</body>
</html>

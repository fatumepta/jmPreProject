<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
    <style>

        fieldset {
            width: 300px;
            border: 1px;
            margin: auto; /* Выравниваем таблицу по центру окна  */
        }

        legend {
            border: 5px;
            margin: auto;
        }

        .button {
            align-content: center;
        }

    </style>
</head>
<body>

<%@ include file="../navi.jsp" %>

<% User user = (User) session.getAttribute("user"); %>
<fieldset>
    <table width="400" border="1" cellpadding="4" cellspacing="0" ma>
        <legend><h1><b> Hello, <%= user.getName() %> !</b></h1></legend>
        <tr bgcolor="#a9a9a9">
            <th> id</th>
            <th> name</th>
            <th> login</th>
            <th> role</th>
        </tr>
        <% long id = user.getId(); %>
        <tr>
            <th><%= id %>
            </th>
            <th><%= user.getName() %>
            </th>
            <th><%= user.getLogin() %>
            </th>
            <th><%= user.getRole() %>
            </th>
        </tr>
    </table>

    <form action="/user" method="post">
        <p>
            <button style="height:30px;width:220px"><b> << LOGOUT >> </b></button>
        </p>
    </form>
</fieldset>

</body>
</html>

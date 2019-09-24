<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
    <form action="/edit" method="post" >
        <fieldset style="width:250px" >
            <legend><b>Edit</b></legend>
            <p><label>name</label>
                <input type="text" name="name" value=<%=request.getAttribute("name")%>></p>
            <p><label>login</label>
                <input type="text" name="login" value=<%=request.getAttribute("login")%>></p>
            <p><label>password</label>
                <input type="password" name="password" value=<%=request.getAttribute("name")%>></p>
            <p><button type="submit">send</button></p>
        </fieldset>
    </form>

</body>
</html>

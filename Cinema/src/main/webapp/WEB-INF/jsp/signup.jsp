<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>FWA / Sign Up</title>
</head>
<body>
<H1>FWA / Sign Up</H1>
<form method="post" action="signUp">
    <table>
        <tr>
            <td>First name:</td>
            <td><input name="firstName" type="text"/></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><input name="lastName" type="text"/></td>
        </tr>
        <tr>
            <td>Phone number:</td>
            <td><input name="phoneNumber" type="text"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input name="email" type="text"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input name="password" type="password"/></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button type="Submit">Send</button>
            </td>
        </tr>
    </table>
</form>
<%
    String error = (String)request.getAttribute("signUpError");
    if (error != null) {
        request.removeAttribute("signUpError");
%>
<b>Can't create user!</b>
<%
    }
%>
</body>
</html>
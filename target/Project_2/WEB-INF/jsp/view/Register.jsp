<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--@elvariable id="registerForm" type="POJOs.RegisterForm"--%>
<html>
<head>
    <style>
        .centered {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
        body {
            background-color: #ccc;
        }
        .button {
            font: bold 11px Arial;
            text-decoration: none;
            background-color: #EEEEEE;
            color: #333333;
            padding: 2px 6px 2px 6px;
            border-top: 1px solid #CCCCCC;
            border-right: 1px solid #333333;
            border-bottom: 1px solid #333333;
            border-left: 1px solid #CCCCCC;
        }
    </style>
    <title>Registration Page</title>
</head>
<body>
<div class = "centered">
<h2>Registration Page</h2>
<form:form modelAttribute="registerForm" method="POST">
    <form:label path="logonId">Username:</form:label><br>
    <form:input path="logonId" /><br>
    <form:label path="password">Password:</form:label><br>
    <form:password path="password" /><br>
    <form:label path="street1">Address Line 1:</form:label><br>
    <form:input path="street1" /><br>
    <form:label path="street2">Address Line 2:</form:label><br>
    <form:input path="street2"/><br>
    <form:label path="city">City:</form:label><br>
    <form:input path="city"/><br>
    <form:label path="state">State:</form:label><br>
    <form:input path="state"/><br>
    <form:label path="zipCode">Area Code:</form:label><br>
    <form:input path="zipCode"/><br>
    <form:select path="method">
        <form:option value="POST">POST</form:option>
        <form:option value="GET">GET</form:option>
    </form:select>
    <input type="submit" value="Continue">
</form:form><br>
Already have an account?
    <strong><a href="<c:out value="SignIn"/>" class="button">Login</a></strong><br>
</div>

</body>
</html>

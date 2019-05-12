<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="loginForm" type="POJOs.RegisterForm"--%>
<%--
  Created by IntelliJ IDEA.
  User: swood
  Date: 10/21/2018
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="registerForm" type="POJOs.RegisterForm"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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
    </style>
</head>
<body>
<div class = "centered">
    <h2>Login:</h2>
<form:form modelAttribute="loginForm" method="POST">
    <form:label path="logonId">Username:</form:label><br>
    <form:input path="logonId" /><br>
    <form:label path="password">Password:</form:label><br>
    <form:password path="password" /><br>
    <input type="submit" value="Submit">
</form:form>
</div>
</body>
</html>

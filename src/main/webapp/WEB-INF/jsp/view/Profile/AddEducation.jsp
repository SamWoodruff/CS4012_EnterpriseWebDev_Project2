<%--
  Created by IntelliJ IDEA.
  User: swood
  Date: 10/25/2018
  Time: 12:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        }</style>
    <title>Title</title>
</head>
<body>
<div class = "centered">
    <h2>Add Education:</h2>
    <%@include file="Education.jspf" %>
</div>
</body>
</html>

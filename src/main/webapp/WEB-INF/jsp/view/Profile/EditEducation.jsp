<%--
  Created by IntelliJ IDEA.
  User: swood
  Date: 10/25/2018
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            background-color: #f1f1f1;
        }</style>
    <title>Title</title>
</head>
<body>
<div class = "centered">
    <h2>Edit Education:</h2>
    <%@include file="Education.jspf" %>
</div>
</body>
</html>

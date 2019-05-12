<%--
  Created by IntelliJ IDEA.
  User: swood
  Date: 10/25/2018
  Time: 1:51 AM
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
            background-color: #ccc;
        }</style>
    <title>Work</title>
</head>
<body>
<div class = "centered">
    <h2>Add to Jobs:</h2>
    <%@include file="Work.jspf" %>
</div>
</body>
</html>

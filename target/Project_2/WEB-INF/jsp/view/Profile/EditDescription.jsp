<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="editForm" type="POJOs.EditForm"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
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
    <title>Profile Editor</title>
</head>
<body>
<div class = "centered">
    <h2>Profile Editor:</h2>
<form:form modelAttribute="editForm" method="POST" enctype="multipart/form-data">
    <form:label path="birthday">Birthday(MM-DD-YYYY):</form:label><br>
    <form:input path="birthday" value="${userDescription.birthday}"/><br>
    <form:label path="cellPhone">Cell Phone:</form:label><br>
    <form:input path="cellPhone" value="${userDescription.cellPhone}"/><br>
    <form:label path="phone">Other Phone:</form:label><br>
    <form:input path="phone" value="${userDescription.phone}"/><br>
    <form:label path="timeZone">Time Zone:</form:label><br>
    <form:select path="timeZone">Time Zone:<br>
        <form:option value="Hawaii Standard Time">Hawaii Standard Time</form:option>
        <form:option value="Alaska Daylight Time">Alaska Daylight Time</form:option>
        <form:option value="Pacific Daylight Time">Pacific Daylight Time</form:option>
        <form:option value="Mountain Standard Time">Mountain Standard Time</form:option>
        <form:option value="Mountain Daylight Time">Mountain Daylight Time</form:option>
        <form:option value="Central Daylight Time">Central Daylight Time</form:option>
        <form:option value="Eastern Daylight Time">Eastern Daylight Time</form:option>
    </form:select><br>
    <form:label path="street1">Address Line 1:</form:label><br>
    <form:input path="street1" value="${address.street1}"/><br>
    <form:label path="street2">Address Line 2:</form:label><br>
    <form:input path="street2" value="${address.street2}"/><br>
    <form:label path="city">City:</form:label><br>
    <form:input path="city" value="${address.city}"/><br>
    <form:label path="state">State:</form:label><br>
    <form:input path="state" value="${address.state}"/><br>
    <form:label path="zipCode">Area Code:</form:label><br>
    <form:input path="zipCode" value="${address.zipCode}"/><br>
    <form:label path="picture">Upload a picture:</form:label><br>
    <input type="file" name="picture"/><br>
    <input type="submit" value="Edit">
</form:form>
        <br>
</div>
</body>
</html>

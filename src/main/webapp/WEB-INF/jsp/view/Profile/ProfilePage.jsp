<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Profile Page</title>
    <style>
        .centered {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
        body {
            background-color: #666;
        }
        <%@include file="profilePage.css" %>
    </style>
</head>
<header><img src="/Project_2/image" /></header>
<page>
    <side>
        <ul>
            <li><strong><u>Education </u></strong><strong> <a href="<c:out value="Profile/AddEducation"/>" class="button">Add</a></strong></li>
            <c:forEach items="${educationHistory}" var="current">
                <li>Degree Type: <c:out value="${current.degreeType}"/></li>
                <li>Degree Discipline: <c:out value="${current.degreeDiscipline}"/></li>
                <li>Years Completed: <c:out value="${current.yearsAchieved}"/></li>
                <li><a href="<c:out value="Profile/EditEducation/${current.educationHistoryId}"/>" class="button">Edit</a>         <a href="<c:out value="Profile/DeleteEducation/${current.educationHistoryId}"/>" class="button">Delete</a></li><br>
            </c:forEach>
            <br>
            <br>
            <li><strong><u>Work History</u></strong><strong> <a href="<c:out value="Profile/AddWork"/>" class="button">Add</a></strong></li>
            <c:forEach items="${workHistory}" var="current">
                <li>Job Title: <c:out value="${current.jobTitle}"/></li>
                <li>Company: <c:out value="${current.companyName}"/></li>
                <li>Year: <c:out value="${current.yearsOfService}"/></li>
                <li><a href="<c:out value="Profile/EditWork/${current.workHistoryId}"/>" class="button">Edit</a>       <a href="<c:out value="Profile/DeleteWork/${current.workHistoryId}"/>" class="button">Delete</a></li><br>
            </c:forEach>
        </ul>
    </side>

    <middle>
        <p><strong>${user.logonId}</strong>        <strong><a href="<c:out value="Profile/EditDescription"/>" class="button">Edit Profile</a><br></strong></p>
        <p><strong><u>User Description</u><br>
            <c:if test="${not empty userDescription.birthday}">Birthday: ${userDescription.birthday}</c:if><br>
            <c:if test="${not empty userDescription.cellPhone}">Cell Phone: ${userDescription.cellPhone}</c:if><br>
            <c:if test="${not empty userDescription.phone}">Other Phone: ${userDescription.phone}</c:if><br>
            <c:if test="${not empty userDescription.timeZone}">Time Zone: ${userDescription.timeZone}</c:if><br>
        </p>
        </p>
        <p><strong><u>Address</u></strong><br>
            ${address.street1}<br>
            ${address.street2}<br>
            <c:if test="${not empty address.city}">${address.city}, </c:if>${address.state} ${address.zipCode}<br><br>

        </p>
    </middle>
</page

<footer>
    <p><a href="<c:out value="Register"/>" class="button">Logout</a></p>
</footer>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Possible Corona Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>XXX</span></h1>
        <nav>
            <ul>
                <jsp:include page="header.jsp">
                    <jsp:param name="page" value="PossibleCoronaOverview"/>
                </jsp:include>
            </ul>
        </nav>
        <h2>
            Possible Corona Positive Overview
        </h2>

    </header>
    <main>
    <table>
        <tr>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>GSM</th>
            <th>Email</th>
            <th>Fitness</th>
        </tr>
        <c:forEach var="possibleCorona" items="${possibleCorona}">
            <tr>
                <td><c:out value="${contact.firstName}"/></td>
                <td><c:out value="${contact.lastName}"/></td>
                <td><c:out value="${contact.GSM}"/></td>
                <td><c:out value="${contact.email}"/></td>
                <td><c:out value="${contact.fitness}"/></td>
            </tr>
        </c:forEach>
    </table>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
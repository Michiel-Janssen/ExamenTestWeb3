<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Corona Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>XXX</span></h1>
        <nav>
            <ul>
                <jsp:include page="header.jsp">
                    <jsp:param name="page" value="CoronaOverview"/>
                </jsp:include>
            </ul>
        </nav>
        <h2>
            Corona Positive Overview
        </h2>

    </header><main>
    <table>
        <tr>
            <th>ID</th>
            <th>Date</th>
        </tr>
        <c:forEach var="coronaPositive" items="${coronaPositives}">
            <tr>
                <td>${coronaPositive.id}</td>
                <td>${coronaPositive.date}</td>
            </tr>
        </c:forEach>
        <caption>Corona Positive Overview</caption>
    </table>

    <form method="POST" action="Controller?command=PossibleCoronaDate" novalidate="novalidate">
        <!-- novalidate in order to be able to run tests correctly -->
        <p><label for="date">Date</label><input type="text" id="date" name="date" value="${lastDate}" required> </p>
    </form>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
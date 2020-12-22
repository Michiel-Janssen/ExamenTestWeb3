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

    <form method="POST" action="Controller?command=filterContacts" novalidate="novalidate">
    <p>
        <label for="from">from</label>
        <input type="date" id="from" name="from">
        <label for="until">until</label>
        <input type="date" id="until" name="until">
        <label for="personId">Person ID</label>
        <input type="text" id="personId" name="personId">
        <input type="submit" id="filter" value="filter">
    </p>
    </form>

    <form method="POST" action="Controller?command=CoronaOverview" novalidate="novalidate">
        <input type="submit" id="clearfilter" value="clearfilter">
    </form>

    <table>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Date</th>
        </tr>
        <c:forEach var="date" items="${CoronaPatienten.keySet()}">
            <tr>
                <td><c:out value="${CoronaPatienten.get(date).firstName} ${CoronaPatienten.get(date).lastName}"/></td>
                <td><c:out value="${CoronaPatienten.get(date).email}"/></td>
                <td><c:out value="${date}"/></td>
        </c:forEach>
        <caption>Corona Positive Overview</caption>
    </table>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>

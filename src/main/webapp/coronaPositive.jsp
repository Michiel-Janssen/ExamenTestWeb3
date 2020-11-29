<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>CoronaPositive</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <c:if test = "${not empty result}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items = "${result}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <h1>
            <span>XXX</span>
        </h1>
        <nav>
            <ul>
                <jsp:include page="header.jsp">
                    <jsp:param name="page" value="CoronaPositive"/>
                </jsp:include>
            </ul>
        </nav>
        <h2>Corona positive</h2>

    </header>
    <main>
        <form method="POST" action="Controller?command=CoronaPositiveAdd" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="id">Id</label><input type="text" id="id" name="id" value="${lastId}" required> </p>
            <p><label for="date">Date</label><input type="date" id="date" name="date" value="${lastDate}" required> </p>
            <p><input type="submit" id="confirm" value="Confirm"></p>
        </form>
    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>

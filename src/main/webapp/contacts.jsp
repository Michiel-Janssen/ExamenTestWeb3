<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Contacts</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <c:if test = "${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items = "${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <h1><span>XXX</span></h1>
        <nav>
            <ul>
                <jsp:include page="header.jsp">
                    <jsp:param name="page" value="Contacts"/>
                </jsp:include>
            </ul>
        </nav>
        <h2>
            Contacts
        </h2>
        <p>De populairste fitness is ${populairsteFitness}</p>
    </header>
    <main>
    <table>
        <c:if test="${user.role=='ADMIN'}">
            <tr>
                <th>Date</th>
                <th>Name</th>
                <th>Fitness</th>
            </tr>
            <c:forEach var="contact" items="${contacts}">
                <tr>
                    <td><c:out value="${contact.date}"/></td>
                    <td><c:out value="${contact.firstName} ${contact.lastName}"/></td>
                    <td><c:out value="${contact.fitness}"/></td>
                </tr>
            </c:forEach>
            <form method="POST" action="Controller?command=SearchContact" novalidate="novalidate">
                <!-- novalidate in order to be able to run tests correctly -->
                <p><label for="datum">Date + Time</label><input type="text" id="datum" name="datum" value="${lastDate}" required> </p>
            </form>
        </c:if>
        <c:if test="${user.role=='CUSTOMER'}">
        <form method="POST" action="Controller?command=addContacts" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName" value="<c:out value="${voornaamVorige}"/>" required> </p>
            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName" value="<c:out value="${naamVorige}"/>" required> </p>
            <p><label for="hour">Hour</label><input type="text" id="hour" name="hour" value="<c:out value="${uurVorige}"/>" required > </p>
            <p><label for="date">Date</label><input type="text" id="date" name="date" value="<c:out value="${dateVorige}"/>" required></p>
            <p><label for="gsm">GSM</label><input type="text" id="gsm" name="gsm" value="<c:out value="${gsmVorige}"/>" required > </p>
            <p><label for="email">Email</label><input type="email" id="email" name="email" value="<c:out value="${emailVorige}"/>" required></p>
            <label for="fitness">Choose a fitness:</label>

            <select name="fitness" id="fitness">
                <option value="Centrum">Centrum</option>
                <option value="Vaart">Vaart</option>
                <option value="Ring">Ring</option>
                <option value="Heverlee">Heverlee</option>
            </select>
            <p><input type="submit" id="contact" value="Contact"></p>
        </form>
        </c:if>
        <caption>Contacts</caption>
    </table>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
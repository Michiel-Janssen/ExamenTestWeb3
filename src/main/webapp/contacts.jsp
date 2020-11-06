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
        <p><p>De populairste fitness is ${populairsteFitness}</p></p>
    </header>
    <main>
    <table>
        <tr>
            <th>Date</th>
            <th>Hour</th>
            <th>Name</th>
            <th>Fitness</th>
        </tr>
        <c:forEach var="contact" items="${contacts}">
            <tr>
                <td>${contact.date}</td>
                <td>${contact.hour}</td>
                <td>${contact.firstName} ${contact.lastName}</td>
                <td>${contact.fitness}</td>
            </tr>
        </c:forEach>

        <form method="POST" action="Controller?command=addContacts" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName" value="${voornaamVorige}" required> </p>
            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName" value="${naamVorige}" required> </p>
            <p><label for="date">Date</label><input type="date" id="date" name="date" value="${dateVorige}" required></p>
            <p><label for="hour">Time</label><input type="time" id="hour" name="hour" value="${hourVorige}" required></p>
            <p><label for="gsm">GSM</label><input type="gsm" id="gsm" name="gsm" value="${gsmVorige}" required > </p>
            <p><label for="email">Email</label><input type="email" id="email" name="email" value="${emailVorige}" required></p>
            <label for="fitness">Choose a fitness:</label>

            <select name="fitness" id="fitness">
                <option value="Centrum">Centrum</option>
                <option value="Vaart">Vaart</option>
                <option value="Ring">Ring</option>
                <option value="Heverlee">Heverlee</option>
            </select>
            <p><input type="submit" id="contact" value="Contact"></p>
        </form>
        <caption>Contacts</caption>
    </table>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
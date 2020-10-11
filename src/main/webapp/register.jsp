<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
<header>
<h1><span>XXX</span></h1>
<nav>
<ul>
    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Register"/>
    </jsp:include>
</ul>
</nav>
<h2>
Register
</h2>

</header><main>
<c:if test = "${not empty result}">
	<div class="alert-danger">
		<ul>
            <c:forEach items = "${result}" var="error">
                <li>${error}</li>
            </c:forEach>
		</ul>
	</div>
</c:if>
    <form method="POST" action="Controller?command=Register" novalidate="novalidate">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p><label for="userid">User id</label><input type="text" id="userid" name="userid" value="${idVorige}" required > </p>
        <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName" value="${voornaamVorige}" required> </p>
        <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName" value="${naamVorige}" required> </p>
        <p><label for="email">Email</label><input type="email" id="email" name="email" value="${emailVorige}" required></p>
        <p><label for="password">Password</label><input type="password" id="password"  name="password" value="${passwordVorige}" required> </p>
        <label for="fitness">Choose a fitness:</label>

        <select name="fitness" id="fitness">
            <option value="Centrum">Centrum</option>
            <option value="Vaart">Vaart</option>
            <option value="Ring">Ring</option>
            <option value="Heverlee">Heverlee</option>
        </select>
        <p><input type="submit" id="signUp" value="Sign Up"></p>
        
    </form>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>

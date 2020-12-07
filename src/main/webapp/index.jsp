<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
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
			<h1>
				<span>XXX</span>
			</h1>
			<nav>
				<ul>
					<jsp:include page="header.jsp">
						<jsp:param name="page" value="Home"/>
					</jsp:include>
				</ul>
			</nav>
			<h2>Home</h2>
			<c:if test="${notAutorized != null}">
				<p class="alert-danger" ${notAutorized} </p>
			</c:if>
		</header>
		<main>
			<c:choose>
				<c:when test = "${not empty user}">

					<h2>Welcome <c:out value="${user.firstName}"/>, you are registered</h2>

					<form method="POST" action="Controller?command=LogOut" novalidate="novalidate">

						<p><input type="submit" id="logOut" value="Log out"></p>

					</form>
					<form method="POST" action="Controller?command=CoronaPositive_Pag" novalidate="novalidate">

						<p><input type="submit" id="CoronaPositive_Pag" value="Corona Positive"></p>

					</form>
				</c:when>
				<c:otherwise>
					<c:if test="${not empty fout}">
						<div class="alert-danger">
							<ul>
								<li><c:out value="${fout}"/></li>
							</ul>
						</div>
					</c:if>
					<form method="POST" action="Controller?command=Login" novalidate="novalidate">

						<p><label for="userid">User id</label>
							<input type="text" id="userid" name="userid" value="<c:out value="${loginId}"/>" required > </p>

						<p><label for="password">Password</label>
							<input type="password" id="password"  name="password" required > </p>

						<p><input type="submit" id="logIn" value="Log in"></p>
					</form>
				</c:otherwise>
			</c:choose>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>
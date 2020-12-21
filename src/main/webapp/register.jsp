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
<c:if test = "${not empty errors}">
	<div class="alert-danger">
		<ul>
            <c:forEach items = "${errors}" var="error">
                <li>${error}</li>
            </c:forEach>
		</ul>
	</div>
</c:if>

    <form method="POST" action="Controller?command=Register" novalidate="novalidate">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p><label for="userid">User id</label><input type="text" id="userid" required pattern="^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$" name="userid" value="<c:out value="${lastId}"/>" required> </p>
        <p><label for="firstName">First Name</label><input type="text" required pattern="^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$" id="firstName" name="firstName" value="<c:out value="${lastFirstName}"/>" required> </p>
        <p><label for="lastName">Last Name</label><input type="text" required pattern="^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$" id="lastName" name="lastName" value="<c:out value="${lastLastName}"/>" required> </p>
        <p><label for="password">Password</label><input type="password" required pattern="^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$" id="password"  name="password" value="<c:out value="${lastPassword}"/>" required> </p>
        <p><label for="email">Email</label><input type="email" required pattern="^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$" id="email" name="email" value="<c:out value="${lastEmail}"/>" required></p>
        <p><label for="role">Role</label><input type="text" required pattern="^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$" id="role" name="role" value="<c:out value="${lastRole}"/>" required></p>
        <p><input type="submit" id="signUp" value="Sign Up"></p>
        
    </form>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
<script>
    window.addEventListener("load", initPage, false);

    function initPage() {

        document.addEventListener("blur", checkField, true);

        document.addEventListener("submit", finalValidation, false);
    }

    function finalValidation(event) {
        let fields = event.target.elements;
        let error, hasErrors;
        for (let i = 0; i < fields.length; i++) {
            error = hasError(fields[i]);
            if (error) {
                showError(fields[i], error);
                if (!hasErrors) {
                    hasErrors = fields[i];
                }
            }

        }

        if (hasErrors) {
            event.preventDefault();
            hasErrors.focus();
        }

    }

    function checkField(event) {
        let error = hasError(event.target);
        if (error)
            showError(event.target, error);
        else
            removeError(event.target);
    }

    function hasError(field) {
        if (field.disabled || field.type === "file" || field.type === "submit")
            return;

        let validity = field.validity;
        if (validity == null || validity.valid) {
            return;
        }

        if (validity.valueMissing) {
            return "Please fill out a value";
        }
        if (validity.typeMismatch) {
            return "Please use the correct input type";
        }
        if (validity.patternMismatch) {
            if (field.type === "email") {
                return "This is not a valid email.";
            }
            if (field.type === "tel") {
                return "This is not a valid phonenumber"
            }
        }
        return "Please complete the form correct";
    }

    function removeError(field) {
        if (field.classList != null && field.classList.length > 0) {
            field.classList.remove("error");
            let id = field.id;
            let message = document.getElementById("error-for-" + id);
            if (message != null)
                message.parentNode.removeChild(message);
        }
    }

    function showError(field, error) {
        field.classList.add("error");
        let id = field.id;
        if (!id)
            return;
        let message = document.getElementById("error-for-" + id);
        if (!message) {
            message = document.createElement("span");
            message.className = "error";
            message.id = "error-for-" + id;
            field.parentNode.insertBefore(message, field.nextSibling);
        }
        message.innerHTML = error;
    }

</script>
</body>
</html>

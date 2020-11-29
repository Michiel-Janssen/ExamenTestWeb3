<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="container">
<nav>
    <ul>
        <li><a href="index.jsp">Home</a></li>
        <c:if test="${user.role=='ADMIN'}">
            <li><a href="Controller?command=Overview">Overview</a></li>
            <li><a href="Controller?command=CoronaOverview">Corona overview</a></li>
        </c:if>
        <c:choose>
            <c:when test="${not empty user}">
                <c:if test="${!user.userid.equals(null)}">
                    <li><a href="Controller?command=Contacts">Contacts</a></li>
                </c:if>
            </c:when>
        </c:choose>
        <li><a href="Controller?command=Register">Register</a></li>
    </ul>
</nav>
</div>
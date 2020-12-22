<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="container">
<nav>
    <ul class="nav-list">
        <li><a href="index.jsp">Home</a></li>
        <c:if test="${user.role=='ADMIN'}">
            <li><a href="Controller?command=Overview">Overview</a></li>
            <li><a href="Controller?command=CoronaOverview">Corona overview</a></li>
        </c:if>
        <c:if test="${user.role=='ADMIN' || user.role=='CUSTOMER'}">
            <li><a href="Controller?command=Contacts">Contacts</a></li>
        </c:if>
        <c:if test="${user.role=='ADMIN'}">
            <li><a href="Controller?command=Register_Pag">Register</a></li>
        </c:if>
    </ul>
</nav>
</div>
<script>
    var element = document.getElementsByClassName("nav-list")[0];
    element.addEventListener("click", myFunction);
    function myFunction(e) {
        var elems = document.querySelector(".active");
        if (elems != null) {
            elems.classList.remove("active");
        }
        e.target.className = "active";
    }
</script>
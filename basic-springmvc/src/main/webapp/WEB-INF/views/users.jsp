<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="" method="POST">
    <p>Add a user</p>
    <p>Name: <input type="text" name="surname"></p>
    <p><input type="submit" value="Add"></p>
</form>

<c:forEach var="user" items="${users}">
    <c:out value="${user}"/>
</c:forEach>
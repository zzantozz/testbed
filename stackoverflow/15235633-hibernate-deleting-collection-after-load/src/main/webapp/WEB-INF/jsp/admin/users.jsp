<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table>
<c:forEach var="user" items="${users}">
    <tr>
        <td>${user.name}</td>
        <td>
            <c:forEach var="role" items="${user.roles}">
                ${role.name}
            </c:forEach>
        </td>
    </tr>
</c:forEach>
</table>
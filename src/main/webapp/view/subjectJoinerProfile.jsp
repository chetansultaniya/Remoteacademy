<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach var="items" items="${subjects}">
<p>${items.studentId.studentId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${items.studentId.email}</p>
</c:forEach>
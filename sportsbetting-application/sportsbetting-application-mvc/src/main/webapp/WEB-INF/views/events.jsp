<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-2"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Manager</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link href="<c:url value="/resources/js/validation.js" />" >

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	
	<table class="table">
		<c:forEach items="${events}" var="event">

			<thead>
				<tr>
					<th scope="col"><spring:message code="label.eventTitle" /></th>
					<th scope="col"><spring:message code="label.startDate" /></th>
					<th scope="col"><spring:message code="label.endDate" /></th>
					<th scope="col"><spring:message code="label.player1" /></th>
					<th scope="col"><spring:message code="label.player2" /></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value="${event.title}" /></td>
					<td><c:out value="${event.startDate}" /></td>
					<td><c:out value="${event.endDate}" /></td>
					<td><c:out value="${event.player1}" /></td>
					<td><c:out value="${event.player2}" /></td>
				</tr>
			</tbody>
		</c:forEach>
	</table>

</body>
</html>
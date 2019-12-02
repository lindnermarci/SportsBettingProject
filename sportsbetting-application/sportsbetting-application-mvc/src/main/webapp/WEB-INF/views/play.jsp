<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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


	<table class="table">
		<c:forEach items="${wagers}" var="wager">

			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">#</th>
					<th scope="col">Event type</th>
					<th scope="col">Bet type</th>
					<th scope="col">Outcome value</th>
					<th scope="col">Outcome odd</th>
					<th scope="col">Wager amount</th>
					<th scope="col">Winner</th>
					<th scope="col">Processed</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<form action="<spring:url value="removeWager"/>"  method="POST"
							method="post">
							<input type="hidden" class="form-control" name="wagerId"
						value="${wager.id}">
							<button class="btn btn-primary">Remove</button>
						</form>
					</td>
					<td><c:out value="${wager.id}" /></td>
					<td><c:out value="${wager.outcomeOdd}" /></td>
					<td><c:out value="${event.title}" /></td>
					<td><c:out value="${wager.amount}" /></td>
					<td><c:out value="${wager.player.name}" /></td>
				</tr>
			</tbody>
		</c:forEach>
	</table>

</body>
</html>
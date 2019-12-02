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
<link href="<c:url value="/resources/js/validation.js" />">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="col-md-8 order-md-1">
		<h4 class="success"><spring:message code="label.accountDetails" /></h4>
		<form action="<spring:url value="save"/>" method="post"
			class="col-md-8 col-md-offset-2">
			<div class="form-group row">
				<label class="col-sm-2 col-form-label"><spring:message code="label.email" /></label>
				<div class="col-sm-10">
					<input type="email" class="form-control" name="email"
						placeholder="${player.email}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label"><spring:message code="label.lastName" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						placeholder="${player.name}">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label"><spring:message code="label.dateBirth" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="birth"
						placeholder="${player.birth}">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label"><spring:message code="label.account" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="accountNumber"
						placeholder="${player.accountNumber}">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label"><spring:message code="label.currency" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="currency"
						placeholder="${player.currencyName}">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label"><spring:message code="label.balance" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="balance"
						placeholder="${player.balance}">
				</div>
			</div>

			<div class="form-group row">
				<div class="col-sm-10 offset-sm-2">
					<button type="submit" class="btn btn-primary"><spring:message code="label.save" /></button>
				</div>
			</div>
		</form>
	</div>


	<table class="table">
		<c:forEach items="${wagers}" var="wager">

			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col"><spring:message code="label.eventTitle" /></th>
					<th scope="col"><spring:message code="label.eventDate" /></th>
					<th scope="col"><spring:message code="label.eventDate" /></th>
					<th scope="col"><spring:message code="label.betType" /></th>
					<th scope="col"><spring:message code="label.outcomeValue" /></th>
					<th scope="col"><spring:message code="label.outcomeOdd" /></th>
					<th scope="col"><spring:message code="label.wagerAmount" /></th>
					<th scope="col"><spring:message code="label.winner" /></th>
					<th scope="col"><spring:message code="label.processed" /></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<form action="<spring:url value="removeWager"/>" method="POST"
							method="post">
							<input type="hidden" class="form-control" name="wagerId"
								value="${wager.id}">
							<button class="btn btn-primary"><spring:message code="label.remove" /></button>
						</form>
					</td>
					<td><c:out value="${event.title}" /></td>
					<td><c:out value="${event.startDate}" /></td>
					<td><c:out value="${event.endDate}" /></td>
					<td><c:out value="${wager.outcomeOdd.outcome.bet.description}" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="${wager.outcomeOdd.value}" /></td>
					<td><c:out value="${wager.amount}" /></td>
					<td><c:out value="${wager.win}" /></td>
					<td><c:out value="${wager.processed}" /></td>
				</tr>
			</tbody>
		</c:forEach>
	</table>

</body>
</html>
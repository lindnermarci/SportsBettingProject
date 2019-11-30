<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Manager</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"
	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>"
	type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script
	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>


	<div class="container">
		<div class="row">

			<form action="<spring:url value="/add"/>" method="post"
				class="col-md-8 col-md-offset-2">

				<div class="form-group">
					<label for="email">Email</label> <input type="email"
						class="form-control" name="email" />
				</div>

				<div class="form-group">
					<label for="password">Password</label> <input type="text"
						class="form-control" name="password" />
				</div>

				<div class="form-group">
					<label for="project-name">Name</label> <input type="text"
						class="form-control" name="name" />
				</div>


				<div class="form-group">
					<label for="accountNumber">AccountNumber</label> <input
						type="text" class="form-control"
						name="accountNumber" />
				</div>

				<div class="form-group">
					<label for="balance">Balance Funds</label> <input
						type="text" class="form-control" name="balance" />
				</div>
				
				<label>Currency</label>
				<div class="radio">
					<label><input type="radio" name="currency" value="EUR" checked>EUR</label>
				</div>
				<div class="radio">
					<label><input type="radio" name="currency" value="HUF">HUF</label>
				</div>
				<div class="radio">
					<label><input type="radio" name="currency" value="USD">USD</label></div>

				<div class="form-group">
					<label for="birth">Birth date(yyyy.MM.dd)</label> <input
						type="text" class="form-control" name="birth" />
				</div>

				<button type="submit" class="btn btn-default">Submit</button>

			</form>

		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="label.title" /></title>

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
	<form action="/sportsbetting-application-mvc/register">
		<button class="float-right btn btn-outline-primary" type="submit"
			value="Sign up">
			<spring:message code="label.signUp" />
		</button>
	</form>


	<div class="row">
		<aside class="col-sm-4">
			<div class="card">
				<article class="card-body">
					
					<form action="<spring:url value="/login"/>" method="post"
						class="col-md-8 col-md-offset-2">
						<h4 class="card-title mb-4 mt-1">
							<spring:message code="label.signIn" />
						</h4>
						<div class="form-group">
							<label><spring:message code="label.email" /></label> <input
								name="username" class="form-control" placeholder="Email"
								type="email">
						</div>
						<!-- form-group// -->
						<div class="form-group">
							<label><spring:message code="label.password" /></label> <input
								name="password" class="form-control" placeholder="******"
								type="password">
						</div>
						<!-- form-group// -->
						<div class="form-group"></div>
						<!-- form-group// -->
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">
								<spring:message code="label.login" />
							</button>
						</div>
						<!-- form-group// -->
					</form>
				</article>
			</div>
			<!-- card.// -->

		</aside>
		<!-- col.// -->
	</div>
</body>
</html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<nav class="navbar navbar-default">
	<div class="container-fluid">

		<div class="navbar-header">
			<a class="navbar-brand" href="#">SportsBetting</a>
		</div>

		<ul class="nav navbar-nav">

			<li><a href="#">Home</a></li>

			<li><a href="events">Events</a></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Language
					<span class="caret"></span>
			</a>

				<ul class="dropdown-menu" role="menu">
					<li><a href="#">English</a></li>
					<li><a href="#">Magyar</a></li>
				</ul></li>

		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="logout">Logout</a></li>
		</ul>

	</div>
</nav>
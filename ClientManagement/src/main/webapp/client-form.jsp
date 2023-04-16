

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CLIENT MANAGEMENT APPLICATION</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="#" class="navbar-brand"> CLIENT MANAGEMENT APPLICATION</a>
			</div>

			<ul class="navbar-nav ms-auto">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">CLIENTS</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${client != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${client == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${client != null}">
            			EDIT CLIENT
            		</c:if>
						<c:if test="${client == null}">
            			ADD CLIENT
            		</c:if>
					</h2>
				</caption>

				<c:if test="${client != null}">
					<input type="hidden" name="id" value="<c:out value='${client.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>CLIENT NAME</label> <input type="text"
						value="<c:out value='${client.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>CLIENT EMAIL</label> <input type="text"
						value="<c:out value='${client.email}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>CLIENT COUNTRY</label> <input type="text"
						value="<c:out value='${client.country}' />" class="form-control"
						name="country">
				</fieldset>

				<button type="submit" class="btn btn-success">SAVE</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
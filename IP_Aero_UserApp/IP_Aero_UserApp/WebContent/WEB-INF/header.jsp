<jsp:useBean id="userBean" class="beans.UserBean" scope="session" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/png"
	href="http://localhost:8080/images/favicon.png" />
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, inital-scale=1.0">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<title>Aero</title>
<script type="text/javascript" src="js/reservation.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>
<body>
	<div class="header">
		<h1>ETF IP AERO</h1>
		<p>Rezervisite svoj let na vrijeme!</p>

		<%
			if (!userBean.isLoggedIn()) {
		%>
		<form action="login?action=login" method="post">
			<div class="login-form">
				<input class="test" type="text" placeholder="Korisnicko ime"
					name="username" required> 
					<input class="test" type="password" placeholder="Lozinka" name="password" required>

				<button class="myButton" type="submit">Prijavi se</button><br/>
				
				 <% 
				 	String contextPath = request.getRequestURL().toString();
				 	if (!contextPath.equals("http://localhost:8080/IP_Aero_UserApp/registration.jsp")) {
				 %>
				<a href="registration.jsp" class="registration">Registruj se</a>
				<%
			}
		%>
		 <div class="error">
				${error }
			</div>
			</div>
		</form>
		<%
			}
		%>
		<%
			if (userBean.isLoggedIn()) {
		%>

		<div class="login-form">

			<form action="login?action=logout" method="post">
				<h2>
					Dobro dosao,
					<%=userBean.getUser().getName() + " " + userBean.getUser().getLastName()%></h2>
				<button class="myButton" type="submit">Odjavi se</button>
			</form>
		</div>

		<%
			}
		%>


	</div>


	<ul class="topnav">
		<li><a href="index.jsp">POCETNA</a></li>
		<li><a href="departures.jsp">ODLASCI</a></li>
		<li><a href="arrivals.jsp">DOLASCI</a></li>
		<%
			if (userBean.isLoggedIn()) {
		%>
		<li><a href="login?action=reservation">REZERVACIJA LETA</a></li>
		<%
			}
		%>
		<%
			if (userBean.isLoggedIn()) {
		%>
		<li><a href="login?action=allreservations">SVE REZERVACIJE</a></li>
		<%
			}
		%>


	</ul>


	<script>
		const currentLocation = location.href;
		const menuItem = document.querySelectorAll('a');
		const menuLength = menuItem.length;
		for (let i = 0; i < menuLength; i++) {
			if (menuItem[i].href === currentLocation) {
				menuItem[i].className = "active";
			}
		}
	</script>
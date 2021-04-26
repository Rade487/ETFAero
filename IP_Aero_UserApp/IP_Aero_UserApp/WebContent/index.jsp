<jsp:include flush="true" page="WEB-INF/header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="now" class="java.util.Date"/>
<jsp:useBean id="flightBean" class="beans.FlightBean" scope="session"/>
<jsp:useBean id="userBean" class="beans.UserBean" scope="session" />
<%@ page import="dto.Flight" %>
<!-- content -->
<%
session.setAttribute("country", userBean.getUser().getCountry());
%>
<div class="row-container">
	<div class="column-container">

		<table id="arrivals">
			<caption class="caption">Dolazeci letovi</caption>
			<thead>
				<tr>
					<th>Polazna lokacija</th>
					<th>Odrediste</th>
					<th>Vrijeme</th>
					<th>Status</th>
					<th>Vrsta leta</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="flight" begin="0" end="4"  items="${flightBean.getAllSerbia()}">

					<tr>
						<td><c:out
								value="${flight.route.originCountry} (${flight.route.originTown})" />
						</td>
						<td><c:out
								value="${flight.route.destinationCountry} (${flight.route.destinationTown})" />
						</td>				
						<td><c:out value="${flight.return_}" /></td>
						<td>
						<c:choose>
							<c:when test="${flight.return_ lt now}">  Sletio </c:when>
							<c:otherwise> Ceka </c:otherwise>
						</c:choose>
						</td>
						<td>
						<c:choose>
							<c:when test="${flight.passengers==0}">  Teretni </c:when>
							<c:otherwise> Putnicki </c:otherwise>
						</c:choose>
						</td>
						
				</c:forEach>

				<!-- } -->
			</tbody>

		</table>


	</div>
	<div class="column-container">
		<table id="departure">
			<caption class="caption">Odlazeci letovi</caption>
			<thead>
				<tr>
					<th>Polazna lokacija</th>
					<th>Odrediste</th>
					<th>Vrijeme</th>
					<th>Status</th>
					<th>Vrsta leta</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="flight" begin="0" end="4" items="${flightBean.getAllSerbia2()}">

					<tr>
						<td><c:out
								value="${flight.route.originCountry} (${flight.route.originTown})" />
						</td>
						<td><c:out
								value="${flight.route.destinationCountry} (${flight.route.destinationTown})" />
						</td>
						<td><c:out value="${flight.departure}" /></td>
						<td>
						<c:choose>
							<c:when test="${flight.departure lt now}">  Poletio </c:when>
							<c:otherwise> Ceka </c:otherwise>
						</c:choose>
						</td>
						<td>
						<c:choose>
							<c:when test="${flight.passengers==0}">  Teretni </c:when>
							<c:otherwise> Putnicki </c:otherwise>
						</c:choose>
						</td>
				</c:forEach>

				<!-- } -->
			</tbody>

		</table>
		<div id="ajaxGetUserServletResponse"></div>
	</div>
</div>
<div class="row-container1">
	<div class="column-container">
				 <iframe style="border-style: none;width: 60%; height: 220px;" src="http://maps.google.com/maps?q=44.82339882930382, 20.291001830296242&z=15&output=embed"></iframe>
	</div>
	<div class="column-container">
		<h4>
			<b>Telefon:</b> 065/165-372<br/><br/>
			<b>Faks: </b>0531859482<br/><br/>
			<b>Email: radetoprek.1996@gmail.com</b>
		</h4>
	</div>
	<div class="column-container">
		<h4>
			<b>Kontakt: </b>
		</h4>
		<form action="Controller?action=message" method="post">
		<input class="contact" type="text" placeholder="Unesite email"
			name="email" required> <br /><input class="contact" type="text"
			placeholder="Unesite naslov" name="title" required> <br />
		<textarea placeholder="Unesite poruku" name="content" cols="30" rows="5" required></textarea><br/>
		<input class="myButton" type="submit" value="Posalji poruku">
		</form>
	</div>
</div>
<script>
setInterval(ajax_call, 1000 * 60);

</script>
<jsp:include flush="true" page="WEB-INF/footer.jsp" />
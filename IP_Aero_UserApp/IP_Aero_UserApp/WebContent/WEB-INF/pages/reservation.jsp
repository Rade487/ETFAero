
<jsp:include flush="true" page="../header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="beans.UserBean"%>
<jsp:useBean id="now" class="java.util.Date" />
<jsp:useBean id="routeBean" class="beans.RouteBean" scope="session" />
<jsp:useBean id="userBean" class="beans.UserBean" scope="session" />
<jsp:useBean id="reservationBean" class="beans.ReservationBean"
	scope="session" />
<!-- content -->

	<div class="registration-container">
		<form id="myForm" action="ReservationServlet?action=reservation" enctype="multipart/form-data" method="post">
			<div class="row">
				<div class="col-25">
					<label for="origin_country"><b>Polazna lokacija
							(drzava) </b></label>
				</div>
				<div class="col-75">
					<select class="registration-select" id="origin_country"
						name="origin_country"
						onchange="javascript:setCities('origin_town')" required>
						<option>Izaberite drzavu</option>
						<c:forEach var="route" items="${routeBean.getAll()}">
							<option>${route.originCountry}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="origin_town"><b>Polazna lokacija (grad) </b></label>
				</div>
				<div class="col-75">
					<select class="registration-select" id="origin_town"
						name="origin_town"
						onchange="javascript:setCities('destination_country')" required>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="destination_country"><b>Odrediste (drzava)
					</b></label>
				</div>
				<div class="col-75">
					<select class="registration-select" id="destination_country"
						name="destination_country"
						onchange="javascript:setCities('destination_town')" required>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="destination_town"><b>Odrediste (grad) </b></label>
				</div>
				<div class="col-75">
					<select class="registration-select" id="destination_town"
						name="destination_town" onchange="javascript:setDateAgain()"
						required>
					</select>
				</div>
			</div>

			<div class="row">
				<div class="col-25">
					<label for="date"><b>Izaberi datum leta</b></label>
				</div>
				<div class="col-75">
					<!-- 				<input list="days" class="registration" name="date" id="date" onchange="jasvascript:setReturnDate()" required disabled> -->
					<input type="text" class="registration" name="date" id="datepicker"
						readonly="readonly" size="12" required disabled />

					<datalist id="days">

					</datalist>
				</div>
			</div>

			<div class="row">
				<div class="col-25">
					<label for="date"><b>Datum povratka </b></label>
				</div>
				<div class="col-75">
					<input type="text" class="registration" name="returndate"
						id="datepickerreturn" readonly="readonly" size="12" required
						disabled />

				</div>
			</div>
			<%
			if (userBean.getUser().getType().equals("putnicki")) {
			%>
			<div class="row">
				<div class="col-25">
					<label for="passengers"><b>Broj putnika </b></label>
				</div>
				<div class="col-75">
					<input class="registration" type="number" name="passengers" min="0"
						max="10" required>
				</div>
			</div>
			<%
			}
			%>
			<%
			if (userBean.getUser().getType().equals("teretni")) {
			%>
			<div class="row">
				<div class="col-25">
					<label for="description"><b>Opis tereta </b></label>
				</div>
				<div class="col-75">
					<input class="registration" type="text" id="description"
						name="description" name="date">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="file"><b>Fajl sa specifikacijom tereta </b></label>
				</div>
				<div class="col-75">
					<input class="registration" type="file" id="file" name="filename">
				</div>
			</div>
			<%
			}
			%>
			<br />
			<div class="testButton">
				<input type="submit" class="myButton" onclick="javascript:reservation()" value="Rezervisi let">
			</div>
			<div class="row">
			
			</div>
			<br />
		</form><form action="ReservationServlet?action=reset" method="post">
				<input class="myButton1" type="submit" value="Resetuj" class="reset">
				</form>
			
			
		

	</div>

<script>
	deleteDuplicate('origin_country');
</script>
<jsp:include flush="true" page="../footer.jsp" />
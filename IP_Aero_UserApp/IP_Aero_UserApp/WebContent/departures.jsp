<jsp:include flush="true" page="WEB-INF/header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="flightBean" class="beans.FlightBean" scope="session"/>
<jsp:useBean id="now" class="java.util.Date"/>
<!-- content -->
<div class="row-containerAD">
	<div class="column-container">
	<div class="col-25">
        <label for="country"><b>Izbor dana</b></label>
      </div>
    <div class="col-75">
    	<input type="radio" id="yesterday" name="type" onchange="javascript:handleClick(this)" value="yesterday" >
		<label for="yesterday">Juce</label>
		<input type="radio" id="today" name="type" onchange="javascript:handleClick(this)" value="today" checked="checked">
		<label for="today">Danas</label>
		<input type="radio" id="tomorrow" name="type" onchange="javascript:handleClick(this)" value="tomorrow">
		<label for="tomorrow">Sutra</label>
	</div>
		<table id="table">
			<caption>Svi danasnji odlasci</caption>
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
				<c:forEach var="flight" items="${flightBean.getAll('todayD')}">

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
		<table id="yesterdaytable" style="display:none">
			<caption>Svi jucerasnji odlasci</caption>
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
				<c:forEach var="flight" items="${flightBean.getAll('yesterdayD')}">

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
		<table id="tomorrowtable" style="display:none" >
			<caption>Svi sutrasnji odlasci</caption>
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
				<c:forEach var="flight" items="${flightBean.getAll('tomorrowD')}">

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
	</div>
</div>
<script>
setInterval(ajax_call_departure, 1000 * 60);
</script>
<jsp:include flush="true" page="WEB-INF/footer.jsp" />
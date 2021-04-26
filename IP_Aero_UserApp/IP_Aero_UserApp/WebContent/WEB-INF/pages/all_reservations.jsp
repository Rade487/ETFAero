<jsp:include flush="true" page="../header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="reservationBean" class="beans.ReservationBean" scope="session"/>
<jsp:useBean id="userBean" class="beans.UserBean" scope="session" />
<!-- content -->
<div class="row-containerAD">
	<div class="column-container">
		<table>
			<caption>Sve rezervacije</caption>
			<thead>
				<tr>
					<th>Polazak</th>
					<%
			if (userBean.getUser().getType().equals("putnicki")) {
			%>
					<th>Broj mjesta</th>
						<%} %>
						<%
			if (userBean.getUser().getType().equals("teretni")) {
			%>
					<th>Opis tereta</th>
					<%} %>
					<th>Status rezervacije</th>
					<th>Datum kreiranja</th>
					<th>Komentar</th>
					<th>Akcija</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="res" items="${reservationBean.getAll(userBean.getUser().getId())}">

					<tr>
						<td><c:out
								value="${res.flight.departure}" />
						</td>
							<%
			if (userBean.getUser().getType().equals("putnicki")) {
			%>
						<td><c:out
								value="${res.numOfPass}" />
						</td><%} %>
							<% if (userBean.getUser().getType().equals("teretni")) {
							%>
						<td><c:out value="${res.weight}" /></td>
						<%} %>
						<td><c:out value="${res.status}" />
						</td>
						<td><c:out value="${res.createdDate}" />
						</td>
						<td><c:out value="${res.comment}" /></td>
						<td>
						<c:choose>
							<c:when test="${res.status=='nova'}"><form action="ReservationServlet?action=cancel&id=<c:out value='${res.createdDate}' />" method="post"><input type="submit" class="myButton" value="Ponisti rezervaciju" ></form> </c:when>
							<c:otherwise> </c:otherwise>
						</c:choose>
						</td>
						
				</c:forEach>

				<!-- } -->
			</tbody>

		</table>
		
	</div>
</div>
<jsp:include flush="true" page="../footer.jsp" />
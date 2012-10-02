<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/jsp/templates/declarations.jsp" %>

<c:if test="${param.status == 'created'}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">×</button>
		Ticket successfully created.		
	</div>
</c:if>

<section>
	<form class="form-search pull-right">
		<div class="input-append">
			<input type="text" class="search-query" />
			<button type="submit" class="btn">Search</button>
		</div>
	</form>
	<a href="${newTicketUrl}" class="btn btn-info">New Ticket</a>
</section>
<section>
	<h2>Recent Tickets</h2>
	<c:choose>
		<c:when test="${empty ticketList}">
			<p>There aren't currently any tickets.</p>
		</c:when>
		<c:otherwise>
			<table id="ticketList" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Date</th>
						<th>Status</th>
						<th>User</th>
						<th>Description</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ticket" items="${ticketList}">
						<%-- FIXME Use JSP tag here so we can source URL from Sitemap --%>
						<c:url var="ticketUrl" value="/tickets/${ticket.id}" />
						<tr>
							<td><c:out value="${ticket.id}" /></td>
							<td>
								<c:if test="${not empty ticket.dateCreated}">
									<span class="iconx calendar"><fmt:formatDate value="${ticket.dateCreated}" type="both" /></span>
								</c:if>
							</td>
							<td><c:out value="${ticket.status.name}" /></td>
							<td><span class="iconx user"><a href="#"><c:out value="${ticket.userName}" /></a></span></td>
							<td><c:out value="${ticket.description}" /></td>
							<td><a href="${ticketUrl}">View</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</section>

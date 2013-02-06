<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="/WEB-INF/jsp/templates/declarations.jsp" %>

<c:if test="${param.status == 'created'}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">×</button>
		Ticket successfully created.		
	</div>
</c:if>

<section>
	<a href="${newTicketUrl}" class="btn">New Ticket</a>
</section>
<section>
	<c:choose>
		<c:when test="${empty ticketList}">
			<p>There aren't currently any tickets.</p>
		</c:when>
		<c:otherwise>
			<table id="ticketList" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Created</th>
						<th>Status</th>
						<th>Customer</th>
						<th>Category</th>
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
									<nobr><fmt:formatDate value="${ticket.dateCreated}" type="both" /></nobr>
								</c:if>
							</td>
							<td><c:out value="${ticket.status.name}" /></td>
							<td>
								<c:out value="${ticket.customerFullName}" /><br />
								<a href="mailto:<c:out value="${ticket.customerEmail}" />"><c:out value="${ticket.customerEmail}" /></a>
							</td>
							<td><c:out value="${ticket.category.name}" /></td>
							<td>
								<c:choose>
									<c:when test="${fn:length(ticket.description) <= 80}">
										<c:out value="${ticket.description}" />
									</c:when>
									<c:otherwise>
										<c:out value="${fn:substring(ticket.description, 0, 80)}" />...
									</c:otherwise>
								</c:choose>
							</td>
							<td><a href="${ticketUrl}">Details</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</section>

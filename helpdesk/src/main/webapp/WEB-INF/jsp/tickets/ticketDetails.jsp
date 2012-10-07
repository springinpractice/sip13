<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/templates/declarations.jsp" %>

<table class="table">
	<colgroup>
		<col span="1" style="width: 25%" />
		<col span="1" style="width: 75%" />
	</colgroup>
	<tr>
		<td>ID</td>
		<td><c:out value="${ticket.id}" /></td>
	</tr>
	<tr>
		<td>Status</td>
		<td><c:out value="${ticket.status.name}" /></td>
	</tr>
	<tr>
		<td>Date created</td>
		<td><span class="iconx calendar"><fmt:formatDate value="${ticket.dateCreated}" type="both" /></span></td>
	</tr>
	<tr>
		<td>Customer name</td>
		<td><c:out value="${customer.firstNameLastName}" /></td>
	</tr>
	<tr>
		<td>Customer username</td>
		<td><c:out value="${customer.username}" /></td>
	</tr>
	<tr>
		<td>Customer e-mail</td>
		<td><span class="iconx mail"><a href="mailto:${customer.email}"><c:out value="${customer.email}" /></a></span></td>
	</tr>
	<tr>
		<td>Description</td>
		<td><c:out value="${ticket.description}" /></td>
	</tr>
</table>

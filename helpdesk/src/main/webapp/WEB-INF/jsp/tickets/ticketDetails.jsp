<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/templates/declarations.jsp" %>

<section>
	<p><span class="label label-info">Note</span> The Edit links don't actually do anything.</p>
</section>
<section>
	<table class="table">
		<colgroup>
			<col span="1" style="width: 25%" />
			<col span="1" style="width: 65%" />
			<col span="1" style="width: 10%" />
		</colgroup>
		<%--
		<tr>
			<td class="field-label">ID</td>
			<td><c:out value="${ticket.id}" /></td>
			<td class="actions"></td>
		</tr>
		--%>
		<tr>
			<td class="field-label">Status</td>
			<td><c:out value="${ticket.status.name}" /></td>
			<td class="actions"><a href="#">Edit</a></td>
		</tr>
		<tr>
			<td class="field-label">Created</td>
			<td><span class="iconx calendar"><fmt:formatDate value="${ticket.dateCreated}" type="both" /></span></td>
			<td class="actions"></td>
		</tr>
		<tr>
			<td class="field-label">Customer username</td>
			<td><c:out value="${ticket.customerUsername}" /></td>
			<td class="actions"></td>
		</tr>
		<tr>
			<td class="field-label">Customer full name</td>
			<td><span class="iconx user"><c:out value="${ticket.customerFullName}" /></span></td>
			<td class="actions"></td>
		</tr>
		<tr>
			<td class="field-label">Customer e-mail</td>
			<td><span class="iconx mail"><a href="mailto:<c:out value="${ticket.customerEmail}" />"><c:out value="${ticket.customerEmail}" /></a></span></td>
			<td class="actions"></td>
		</tr>
		<tr>
			<td class="field-label">Category</td>
			<td><c:out value="${ticket.category.name}" /></td>
			<td class="actions"><a href="#">Edit</a></td>
		</tr>
		<tr>
			<td class="field-label">Description</td>
			<td><c:out value="${ticket.description}" /></td>
			<td class="actions"><a href="#">Edit</a></td>
		</tr>
	</table>
</section>

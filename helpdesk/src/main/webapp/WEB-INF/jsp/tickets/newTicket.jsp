<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/templates/declarations.jsp" %>

<p>Please use this form to create a support ticket for the user.</p>

<div class="row-fluid">
	<div class="span8">
		<form:form modelAttribute="ticket" action="${ticketsUrl}">
			<div class="well">
				<div class="control-group">
					<label class="control-label" for="userName">Customer username:</label>
					<div class="controls">
						<form:input path="customerUsername" class="span4" />
						<form:errors path="customerUsername">
							<div><span class="iconx warning"><form:errors path="customerUsername" /></span></div>
						</form:errors>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="category">Category:</label>
					<div class="controls">
						<form:select path="category">
							<form:option value="" label="-- Choose one --" />
							<form:options items="${ticketCategoryList}" itemValue="id" itemLabel="name" />
						</form:select>
						<form:errors path="category">
							<div><span class="iconx warning"><form:errors path="category" /></span></div>
						</form:errors>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="description">Description:</label>
					<div class="controls">
						<form:textarea path="description" class="span12" rows="8" placeholder="Detailed description of the issue" />
						<form:errors path="description">
							<div><span class="iconx warning"><form:errors path="description" /></span></div>
						</form:errors>
					</div>
				</div>
			</div>
			<div>
				<button type="submit" class="btn btn-primary">Submit</button>
				<a href="${ticketsUrl}" class="btn btn-link">Cancel</a> 
			</div>
		</form:form>
	</div>
</div>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="/WEB-INF/jsp/templates/declarations.jsp" %>

<p>Please use this form to create a support ticket for the user.</p>

<div class="row-fluid">
	<div class="span8">
		<div class="well">
			<form:form modelAttribute="ticket" action="${ticketsUrl}">
				<div class="control-group">
					<label class="control-label" for="userName">User name:</label>
					<div class="controls">
						<form:input path="userName" class="span8" placeholder="First and last name" />
						<form:errors path="userName">
							<div><span class="iconx warning"><form:errors path="userName" /></span></div>
						</form:errors>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="userEmail">User e-mail:</label>
					<div class="controls">
						<form:input path="userEmail" class="span8" placeholder="Valid e-mail address" />
						<form:errors path="userEmail">
							<div><span class="iconx warning"><form:errors path="userEmail" /></span></div>
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
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-primary">Submit</button>
						<a href="${ticketsUrl}" class="btn btn-link">Cancel</a> 
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>

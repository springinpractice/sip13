<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/templates/declarations.jsp" %>

<c:url var="postLoginUrl" value="/j_spring_security_check" />

<div class="page-header">
	<h1>Log In</h1>
</div>

<c:if test="${param.status == 'failed'}">
	<div class="alert alert-warning">
		<button type="button" class="close" data-dismiss="alert">×</button>
		Your login attempt failed. Please try again, or contact technical support for further assistance.
	</div>
</c:if>

<p><span class="label label-info">Note</span> Use paul/paul or aimee/aimee.</p>

<div class="row-fluid">
	<div class="span4">
		<form action="${postLoginUrl}" method="post">
			<div class="well">
				<div class="control-group">
					<label class="control-label" for="userName">Username:</label>
					<div class="controls">
						<input type="text" name="j_username" class="span12" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="userEmail">Password:</label>
					<div class="controls">
						<input type="password" name="j_password" class="span12" />
					</div>
				</div>
				<div class="control-group">
					<label class="checkbox">
						<input type="checkbox" name="_spring_security_remember_me" /> Remember me
					</label>
				</div>
			</div>
<!-- 			<div class="form-actions"> -->
			<div>
				<button type="submit" class="btn btn-primary">Submit</button>
				<a href="${homeUrl}" class="btn btn-link">Cancel</a> 
			</div>
		</form>
	</div>
</div>

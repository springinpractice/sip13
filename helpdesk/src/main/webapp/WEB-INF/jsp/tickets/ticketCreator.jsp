<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/urls.jspf" %>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Create a support ticket</title>
	</head>
	<body>
		<ul id="breadcrumbs">
			<li><a href="${homeUrl}">Home</a></li>
			<li><a href="${ticketListUrl}">Tickets</a></li>
		</ul>
		
		<h1>Create a support ticket</h1>
		
		<p>Please use this form to create a support ticket for the user.</p>
		
		<form:form modelAttribute="ticket" action="${createTicketUrl}" cssClass="main">
			<div class="panel grid">
				<div class="gridRow yui-gf">
					<div class="fieldLabel yui-u first">User name:</div>
					<div class="yui-u">
						<div><form:input path="userName" cssClass="medium" /></div>
						<form:errors path="userName">
							<div class="errorMessage"><form:errors path="userName" /></div>
						</form:errors>
					</div>
				</div>
				<div class="gridRow yui-gf">
					<div class="fieldLabel yui-u first">User e-mail:</div>
					<div class="yui-u">
						<div><form:input path="userEmail" cssClass="medium" /></div>
						<form:errors path="userEmail">
							<div class="errorMessage"><form:errors path="userEmail" /></div>
						</form:errors>
					</div>
				</div>
				<div class="gridRow yui-gf">
					<div class="fieldLabel yui-u first">Issue description:</div>
					<div class="yui-u">
						<div><form:textarea path="description" rows="8" /></div>
						<form:errors path="description">
							<div class="errorMessage"><form:errors path="description" /></div>
						</form:errors>
					</div>
				</div>
				<div class="gridRow yui-gf">
					<div class="yui-u first"></div>
					<div class="yui-u">
						<input type="submit" value="Create" />
					</div>
				</div>
			</div>
		</form:form>
	</body>
</html>

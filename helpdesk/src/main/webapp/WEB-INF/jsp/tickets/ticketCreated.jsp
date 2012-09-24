<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/urls.jspf" %>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Ticket created</title>
	</head>
	<body>
		<ul id="breadcrumbs">
			<li><a href="${homeUrl}">Home</a></li>
			<li><a href="${ticketListUrl}">Tickets</a></li>
		</ul>
		
		<h1>Ticket created</h1>
		
		<p>You have successfully created a trouble ticket.</p>
		
		<p>Please inform the user that he or she should receive a confirmation e-mail shortly.</p>
	</body>
</html>

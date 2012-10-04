<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/templates/declarations.jsp" %>

<div class="hero-unit">
	<h1>SIP Portal</h1>
	<p>Very possibly the world's simplest portal.</p>
</div>

<security:authorize access="isAnonymous()">
	<h2>Please log in</h2>
	<p>Please <a href="${loginUrl}">log in</a> so you can create a ticket. That's really the only thing you can do around
	here, so make it a good one.</p>
</security:authorize>

<security:authorize access="isAuthenticated()">
	<h2>Welcome, <security:authentication property="principal.username" /></h2>
	<p>Now that you're logged in, why not <a href="${newTicketUrl}">create a ticket?</a></p>
</security:authorize>

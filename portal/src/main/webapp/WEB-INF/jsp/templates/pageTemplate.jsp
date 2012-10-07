<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ include file="/WEB-INF/jsp/templates/declarations.jsp" %>

<c:set var="staticPath" value="/static" />
<c:set var="bootstrapPath" value="${staticPath}/bootstrap-2.1.1" />
<c:url var="bootstrapCssUrl" value="${bootstrapPath}/css/bootstrap.min.css" />
<c:url var="bootstrapResponsiveCssUrl" value="${bootstrapPath}/css/bootstrap-responsive.min.css" />
<c:url var="bootstrapJsUrl" value="${bootstrapPath}/js/bootstrap.min.js" />
<c:url var="portalCssUrl" value="${staticPath}/css/portal.css" />

<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>SIP Portal</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${bootstrapCssUrl}" rel="stylesheet">
		<style>
			body { padding-top: 60px; padding-bottom: 40px; }
			footer { text-align: center; }
		</style>
		<link href="${bootstrapResponsiveCssUrl}" rel="stylesheet">
		<link href="${portalCssUrl}" rel="stylesheet">
		
		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
					<a class="brand" href="${homeUrl}">SIP Portal</a>
					<div class="nav-collapse collapse">
						<p class="navbar-text pull-right">
							<security:authorize access="isAnonymous()">
								<a href="${loginUrl}" class="navbar-link">Log in</a>
							</security:authorize>
							<security:authorize access="isAuthenticated()">
								Logged in as <security:authentication property="principal.firstNameLastName" />.
								<a href="${logoutUrl}" class="navbar-link">Log out</a>
							</security:authorize>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<tiles:insertAttribute name="content" />
				</div>
			</div>
			<hr />
			<footer>
				<p><span class="iconx phone">Call 555-SUPPORT for support.</span></p>
				<p>
					<span class="iconx leaf"><a href="http://springinpractice.com/">Spring in Practice blog</a></span> |
					<a href="http://p.yusukekamiyamane.com/">Fugue Icons</a>
				</p>
			</footer>
		</div>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="${bootstrapJsUrl}"></script>
	</body>
</html>

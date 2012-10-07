<%@ include file="/WEB-INF/jsp/templates/declarations.jsp" %>

<div id="home-hero-unit" class="hero-unit">
	<h1>SIP Help Desk</h1>
	<p>Welcome to the Spring in Practice Help Desk.</p>
</div>
<div class="row-fluid">
	<div class="span4">
		<h2><span class="iconx24 ticket"><c:out value="${ticketsNode.name}" /></span></h2>
		<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
		<p><a class="btn" href="${ticketsUrl}">View details &raquo;</a></p>
	</div>
	<div class="span4">
		<h2><span class="iconx24 database"><c:out value="${knowledgeBaseNode.name}" /></span></h2>
		<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
		<p><a class="btn" href="${knowledgeBaseUrl}">View details &raquo;</a></p>
	</div>
	<div class="span4">
		<h2><span class="iconx24 tick"><c:out value="${approvalsNode.name}" /></span></h2>
		<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
		<p><a class="btn" href="${approvalsUrl}">View details &raquo;</a></p>
	</div>
</div>
<div class="row-fluid">
	<div class="span4">
		<h2><span class="iconx24 alarm-clock"><c:out value="${tasksNode.name}" /></span></h2>
		<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
		<p><a class="btn" href="${tasksUrl}">View details &raquo;</a></p>
	</div>
	<div class="span4">
		<h2><span class="iconx24 question"><c:out value="${surveysNode.name}" /></span></h2>
		<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
		<p><a class="btn" href="${surveysUrl}">View details &raquo;</a></p>
	</div>
	<div class="span4">
		<h2><span class="iconx24 document"><c:out value="${reportingNode.name}" /></span></h2>
		<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
		<p><a class="btn" href="${reportingUrl}">View details &raquo;</a></p>
	</div>
</div>

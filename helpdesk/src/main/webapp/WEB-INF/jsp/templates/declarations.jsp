<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Nodes --%>
<c:set var="homeNode" value="${nodes['home']}" />
<c:set var="loginNode" value="${nodes['login']}" />
<c:set var="logoutNode" value="${nodes['logout']}" />
<c:set var="ticketsNode" value="${nodes['tickets']}" />
<c:set var="newTicketNode" value="${nodes['newTicket']}" />
<c:set var="knowledgeBaseNode" value="${nodes['knowledgeBase']}" />
<c:set var="approvalsNode" value="${nodes['approvals']}" />
<c:set var="tasksNode" value="${nodes['tasks']}" />
<c:set var="surveysNode" value="${nodes['surveys']}" />
<c:set var="reportingNode" value="${nodes['reporting']}" />

<%-- URLs --%>
<c:url var="homeUrl" value="${homeNode.path}" />
<c:url var="loginUrl" value="${loginNode.path}" />
<c:url var="logoutUrl" value="${logoutNode.path}" />
<c:url var="ticketsUrl" value="${ticketsNode.path}" />
<c:url var="newTicketUrl" value="${newTicketNode.path}" />
<c:url var="knowledgeBaseUrl" value="${knowledgeBaseNode.path}" />
<c:url var="approvalsUrl" value="${approvalsNode.path}" />
<c:url var="tasksUrl" value="${tasksNode.path}" />
<c:url var="surveysUrl" value="${surveysNode.path}" />
<c:url var="reportingUrl" value="${reportingNode.path}" />

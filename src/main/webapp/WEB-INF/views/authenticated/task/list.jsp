<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="authenticated.task.list.label.title" path="title" width="30%" />
	<acme:list-column code="authenticated.task.list.label.start-execution-period" path="startExecutionPeriod" width="30%" />
	<acme:list-column code="authenticated.task.list.label.end-execution-period" path="endExecutionPeriod" width="30%" />
	<acme:list-column code="authenticated.task.list.label.workload" path="workload" width="10%" />
	<acme:list-column code="authenticated.task.list.label.isPublic" path="isPublic" />
	<acme:list-column code="authenticated.task.list.label.link" path="link" />
	<acme:list-column code="authenticated.task.list.label.description" path="description" />
	
</acme:list>
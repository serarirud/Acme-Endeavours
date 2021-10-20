<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.duties.list.label.title" path="title" width="30%" />
	<acme:list-column code="authenticated.duties.list.label.start-execution-period" path="startExecutionPeriod" width="30%" />
	<acme:list-column code="authenticated.duties.list.label.end-execution-period" path="endExecutionPeriod" width="30%" />
	<acme:list-column code="authenticated.duties.list.label.workload" path="workload" width="10%" />
	
</acme:list>
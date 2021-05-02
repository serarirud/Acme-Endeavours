<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
<<<<<<< HEAD
	<acme:list-column code="anonymous.work-plan.list.label.id" path="id" width="20%" />
	<acme:list-column code="anonymous.work-plan.list.label.start-execution-period" path="startExecutionPeriod" width="30%" />
	<acme:list-column code="anonymous.work-plan.list.label.end-execution-period" path="endExecutionPeriod" width="30%" />
	<acme:list-column code="anonymous.work-plan.list.label.workload" path="workload" width="20%" />
=======
	<acme:list-column code="anonymous.workPlan.list.label.id" path="id" width="30%" />
	<acme:list-column code="anonymous.workPlan.list.label.start-execution-period" path="startExecutionPeriod" width="30%" />
	<acme:list-column code="anonymous.workPlan.list.label.end-execution-period" path="endExecutionPeriod" width="30%" />
	<acme:list-column code="anonymous.workPlan.list.label.workload" path="Workload" width="10%" />
>>>>>>> branch 'tarea-008' of https://github.com/Francisco-Borrego/Acme-Planner.git
</acme:list>
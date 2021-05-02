<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<acme:form-selectField code="tasks" path="tasks">
		<jstl:forEach var="task" items="${tasks}">
			<acme:form-option code="${task.title}" value="${task}" />
		</jstl:forEach>
	</acme:form-selectField>

	
	<acme:form-moment code="anonymous.work-plan.form.label.start-execution-period" path="startExecutionPeriod" />
	<acme:form-moment code="anonymous.work-plan.form.label.end-execution-period" path="endExecutionPeriod" />
	<acme:form-textbox code="anonymous.work-plan.form.label.workload" path="workload"/>
	<acme:form-checkbox code="anonymous.work-plan.form.label.is-public" path="isPublic" />
	<acme:form-checkbox code="anonymous.work-plan.form.label.is-published" path="isPublished" />
	<acme:form-return code="manager.work-plan.form.button.return" />

</acme:form>
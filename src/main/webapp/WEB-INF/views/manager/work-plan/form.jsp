<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-selectField code="tasks" path="tasks">
		<jstl:forEach var="task" items="${tasks}">
			<acme:form-option code="${task.title}" value="${task.id}" />
		</jstl:forEach>
	</acme:form-selectField>

	
	<acme:form-moment code="manager.work-plan.form.label.start-execution-period" path="startExecutionPeriod" />
	<acme:form-moment code="manager.work-plan.form.label.end-execution-period" path="endExecutionPeriod" />
	<acme:form-checkbox code="manager.work-plan.form.label.is-public" path="isPublic" />
	<acme:form-checkbox code="manager.work-plan.form.label.is-published" path="isPublished" />
	
	<acme:form-submit test="${command == 'show'}" code="manager.work-plan.form.button.update" action="/manager/work-plan/update"/>
	<acme:form-submit test="${command == 'show'}" code="manager.work-plan.form.button.delete" action="/manager/work-plan/delete"/>
	<acme:form-submit test="${command == 'create'}" code="manager.work-plan.form.button.create" action="/manager/work-plan/create"/>
	<acme:form-submit test="${command == 'update'}" code="manager.work-plan.form.button.update" action="/manager/work-plan/update"/>
	<acme:form-submit test="${command == 'delete'}" code="manager.work-plan.form.button.delete" action="/manager/work-plan/delete"/>		
	<acme:form-return code="manager.work-plan.form.button.return" />
</acme:form>
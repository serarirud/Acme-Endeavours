<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textarea code="anonymous.workPlan.form.label.tasks" path="tasks" />
	<acme:form-moment code="anonymous.workPlan.form.label.start-execution-period" path="startExecutionPeriod" />
	<acme:form-moment code="anonymous.workPlan.form.label.end-execution-period" path="endExecutionPeriod" />
	<acme:form-double code="anonymous.workPlan.form.label.workload" path="workload" />
	<acme:form-checkbox code="anonymous.workPlan.form.label.is-public" path="isPublic" />
	<acme:form-checkbox code="anonymous.workPlan.form.label.is-published" path="isPublished" />
	
	<acme:form-return code="anonymous.workPlan.form.button.return" />
</acme:form>
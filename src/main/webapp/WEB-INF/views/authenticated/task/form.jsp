<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.task.form.label.title" path="title" />
	<acme:form-moment code="authenticated.task.form.label.start-execution-period" path="startExecutionPeriod" />
	<acme:form-moment code="authenticated.task.form.label.end-execution-period" path="endExecutionPeriod" />
	<acme:form-double code="authenticated.task.form.label.workload" path="workload" />
	<acme:form-textarea code="authenticated.task.form.label.description" path="description" />
	<acme:form-textbox code="authenticated.task.form.label.link" path="link" />
	<acme:form-checkbox code="authenticated.task.form.label.is-public" path="isPublic" />
	
	<acme:form-return code="anonymous.task.form.button.return" />
</acme:form>
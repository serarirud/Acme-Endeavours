<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="officer.task.form.label.title" path="title" />
	<acme:form-moment code="officer.task.form.label.start-execution-period" path="startExecutionPeriod" />
	<acme:form-moment code="officer.task.form.label.end-execution-period" path="endExecutionPeriod" />
	<acme:form-double code="officer.task.form.label.workload" path="workload" />
	<acme:form-textarea code="officer.task.form.label.description" path="description" />
	<acme:form-textbox code="officer.task.form.label.link" path="link" />
	<acme:form-checkbox code="officer.task.form.label.is-public" path="isPublic" />
	
	<acme:form-submit test="${command == 'show'}" code="officer.task.form.button.update" action="/manager/task/update"/>
	<acme:form-submit test="${command == 'show'}" code="officer.task.form.button.delete" action="/manager/task/delete"/>
	<acme:form-submit test="${command == 'create'}" code="officer.task.form.button.create" action="/manager/task/create"/>
	<acme:form-submit test="${command == 'update'}" code="officer.task.form.button.update" action="/manager/task/update"/>
	<acme:form-submit test="${command == 'delete'}" code="officer.task.form.button.delete" action="/manager/task/delete"/>		
	<acme:form-return code="officer.task.form.button.return" />
</acme:form>
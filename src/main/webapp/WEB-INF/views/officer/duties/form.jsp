<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="officer.duties.form.label.title" path="title" />
	<acme:form-moment code="officer.duties.form.label.start-execution-period" path="startExecutionPeriod" />
	<acme:form-moment code="officer.duties.form.label.end-execution-period" path="endExecutionPeriod" />
	<acme:form-double code="officer.duties.form.label.workload" path="workload" />
	<acme:form-textarea code="officer.duties.form.label.description" path="description" />
	<acme:form-textbox code="officer.duties.form.label.link" path="link" />
	<acme:form-checkbox code="officer.duties.form.label.is-public" path="isPublic" />
	
	<acme:form-submit test="${command == 'show'}" code="officer.duties.form.button.update" action="/officer/duties/update"/>
	<acme:form-submit test="${command == 'show'}" code="officer.duties.form.button.delete" action="/officer/duties/delete"/>
	<acme:form-submit test="${command == 'create'}" code="officer.duties.form.button.create" action="/officer/duties/create"/>
	<acme:form-submit test="${command == 'update'}" code="officer.duties.form.button.update" action="/officer/duties/update"/>
	<acme:form-submit test="${command == 'delete'}" code="officer.duties.form.button.delete" action="/officer/duties/delete"/>		
	<acme:form-return code="officer.duties.form.button.return" />
</acme:form>
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.duties.form.label.title" path="title" />
	<acme:form-moment code="authenticated.duties.form.label.start-execution-period" path="startExecutionPeriod" />
	<acme:form-moment code="authenticated.duties.form.label.end-execution-period" path="endExecutionPeriod" />
	<acme:form-double code="authenticated.duties.form.label.workload" path="workload" />
	<acme:form-textarea code="authenticated.duties.form.label.description" path="description" />
	<acme:form-textbox code="authenticated.duties.form.label.link" path="link" />
	<acme:form-checkbox code="authenticated.duties.form.label.is-public" path="isPublic" />
	
	<acme:form-return code="anonymous.duties.form.button.return" />
</acme:form>
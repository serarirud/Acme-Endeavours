<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text"/>
	<acme:form-textbox code="anonymous.shout.form.label.info" path="info"/>
	<acme:form-textbox code="anonymous.shout.form.label.pustemi.label" path="pustemi.label" placeholder="yy-mmdd-xxxx, donde dd es el día actual, mm es el mes actual, yy es el año actual y xxxx son cualquier número de 2 a 4 dígitos."/>
	<acme:form-moment code="anonymous.shout.form.label.pustemi.deadline" path="pustemi.deadline"/>
	<acme:form-money code="anonymous.shout.form.label.pustemi.budget" path="pustemi.budget"/>
	<acme:form-checkbox code="anonymous.shout.form.label.pustemi.important" path="pustemi.important" />
	
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>
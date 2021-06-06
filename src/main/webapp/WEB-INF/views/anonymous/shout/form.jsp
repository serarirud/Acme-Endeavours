<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text"/>
	<acme:form-textbox code="anonymous.shout.form.label.info" path="info"/>
	
	<acme:form-textbox placeholder="dd/mm/yyyy" code="atributo1" path="sheet.atributo1"/>
	<acme:form-moment code="atributo2" path="sheet.atributo2"/>
	<acme:form-money code="atributo3" path="sheet.atributo3"/>
	<acme:form-checkbox code="atributo4" path="sheet.atributo4"/>
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox placeholder="John Doe" code="anonymous.shout.form.label.author" path="author"/>
	<acme:form-textarea placeholder="Say something" code="anonymous.shout.form.label.text" path="text"/>
	<acme:form-textbox placeholder="http://example.org" code="anonymous.shout.form.label.info" path="info"/>
		
	<acme:form-textbox placeholder="dd/mm/yyyy" code="atr1" path="sheet.atr1"/>
	<acme:form-moment code="atr2" path="sheet.atr2" />
	<acme:form-money code="atr3" path="sheet.atr3"/>
	<acme:form-checkbox code="atr4" path="sheet.atr4"/>
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>
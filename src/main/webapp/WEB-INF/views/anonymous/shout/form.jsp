<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author" placeholder="John Smith"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text" placeholder="Texto de prueba..."/>
	<acme:form-textbox code="anonymous.shout.form.label.info" path="info" placeholder="http://example1.com"/>
	
	<acme:form-textbox code="anonymous.shout.form.label.atr1" path="kolem.ticker" placeholder="aaaa:yy:mmddd" />
	<acme:form-moment code="anonymous.shout.form.label.atr2" path="kolem.deadline" placeholder="YYYY/MM/DD hh:mm ó DD/MM/YYYY hh:mm" />
	<acme:form-money code="anonymous.shout.form.label.atr3" path="kolem.budget" placeholder="EUR/USD 100.00" />
	<acme:form-checkbox code="anonymous.shout.form.label.atr4" path="kolem.important" />
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="info.author"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="info.text"/>
	<acme:form-textbox code="anonymous.shout.form.label.info" path="info.info"/>
	<acme:form-textbox code="anonymous.shout.form.label.sheet.pattern" path="pattern" placeholder="De 2 a 4 letras o números seguido de /dd/mm/yy. Ej: ab/10/09/2021 o ab2d/10/09/2021"/>
	<acme:form-textbox code="anonymous.shout.form.label.sheet.moment" path="moment" placeholder="YYYY/MM/DD hh:mm"/>
	<acme:form-textbox code="anonymous.shout.form.label.sheet.money" path="money" placeholder="100 EUR o 100 USD o 100 GBP"/>
	<acme:form-checkbox code="anonymous.shout.form.label.sheet.important" path="important"/>
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>
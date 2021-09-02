<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text"/>
	<acme:form-textbox code="anonymous.shout.form.label.info" path="info"/>
	<acme:form-textbox code="anonymous.shout.form.label.xxx.xxx1" path="xxx.xxx1" placeholder="xxxx/dd/mm/yy, donde xxxx son cualquier letra de 2 a 4, dd es el día actual, mm es el mes actual y yy es el año actual."/>
	<acme:form-moment code="anonymous.shout.form.label.xxx.xxx2" path="xxx.xxx2"/>
	<acme:form-money code="anonymous.shout.form.label.xxx.xxx3" path="xxx.xxx3"/>
	<acme:form-checkbox code="anonymous.shout.form.label.xxx.xxx4" path="xxx.xxx4" />
	
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>
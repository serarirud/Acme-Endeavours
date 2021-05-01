<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="administrator.configuration.form.label.total" path="total"/>
	<acme:form-textbox code="administrator.configuration.form.label.finalizados" path="finalizados"/>
	<acme:form-textbox code="administrator.configuration.form.label.noFinalizados" path="noFinalizados"/>
	<acme:form-textbox code="administrator.configuration.form.label.publicados" path="publicados"/>
	<acme:form-textbox code="administrator.configuration.form.label.noPublicados" path="noPublicados"/>
	<acme:form-textbox code="administrator.configuration.form.label.maximoPeriodo" path="maximoPeriodo"/>
	<acme:form-textbox code="administrator.configuration.form.label.minimoPeriodo" path="minimoPeriodo"/>
	<acme:form-textbox code="administrator.configuration.form.label.maximoCarga" path="maximoCarga"/>
	<acme:form-textbox code="administrator.configuration.form.label.minimoCarga" path="minimoCarga"/>
	<acme:form-textbox code="administrator.configuration.form.label.mediaPeriodo" path="mediaPeriodo"/>
	<acme:form-textbox code="administrator.configuration.form.label.desviacionPeriodo" path="desviacionPeriodo"/>
	<acme:form-textbox code="administrator.configuration.form.label.mediaCarga" path="mediaCarga"/>
	<acme:form-textbox code="administrator.configuration.form.label.desviacionCarga" path="desviacionCarga"/>
	<acme:form-return code="administrator.configuration.form.button.return"/>
</acme:form>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment"  />
	<acme:list-column code="anonymous.shout.list.label.author" path="author"  />
	<acme:list-column code="anonymous.shout.list.label.text" path="text"  />
	<acme:list-column code="anonymous.shout.list.label.info" path="info"  />
	<acme:list-column code="atributo1" path="sheet.atributo1" />
	<acme:list-column code="atributo2" path="sheet.atributo2" />
	<acme:list-column code="atributo3" path="sheet.atributo3" />
	<acme:list-column code="atributo4" path="sheet.atributo4" />
</acme:list>
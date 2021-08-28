<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment"  />
	<acme:list-column code="anonymous.shout.list.label.author" path="author"  />
	<acme:list-column code="anonymous.shout.list.label.text" path="text"  />
	<acme:list-column code="anonymous.shout.list.label.info" path="info"  />
	<acme:list-column code="anonymous.shout.list.label.atributo1" path="sheet.name" />
	<acme:list-column code="anonymous.shout.list.label.atributo2" path="sheet.deadline" />
	<acme:list-column code="anonymous.shout.list.label.atributo3" path="sheet.budget" />
	<acme:list-column code="anonymous.shout.list.label.atributo4" path="sheet.important" />
</acme:list>
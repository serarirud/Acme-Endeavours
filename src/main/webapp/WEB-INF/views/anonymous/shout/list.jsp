<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.text" path="text"  width="25%"/>
	<acme:list-column code="anonymous.shout.list.label.info" path="info" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.sheet.pattern" path="sheet.pattern" width="20%" />
	<acme:list-column code="anonymous.shout.list.label.sheet.moment" path="sheet.moment" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.sheet.money" path="sheet.money"  width="10%"/>
	<acme:list-column code="anonymous.shout.list.label.sheet.important" path="sheet.important" width="5%" />
</acme:list>
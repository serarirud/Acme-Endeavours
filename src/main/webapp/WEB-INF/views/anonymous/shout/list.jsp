<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.text" path="text"  width="50%"/>
	<acme:list-column code="anonymous.shout.list.label.info" path="info" width="30" />

	<acme:list-column code="anonymous.shout.list.label.atr1" path="sheet.atr1" />
	<acme:list-column code="anonymous.shout.list.label.atr2" path="sheet.atr2" />
	<acme:list-column code="anonymous.shout.list.label.atr3" path="sheet.atr3" />
	<acme:list-column code="anonymous.shout.list.label.atr4" path="sheet.atr4" />
	
</acme:list>
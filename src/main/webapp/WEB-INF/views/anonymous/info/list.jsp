<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="shout.moment" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.author" path="shout.author" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.text" path="shout.text"  width="25%"/>
	<acme:list-column code="anonymous.shout.list.label.info" path="shout.info" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.pattern" path="pattern" width="20%" />
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.money" path="money"  width="10%"/>
	<acme:list-column code="anonymous.shout.list.label.important" path="important" width="5%" />
</acme:list>
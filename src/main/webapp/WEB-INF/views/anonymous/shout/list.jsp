<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.text" path="text"  width="25%"/>
	<acme:list-column code="anonymous.shout.list.label.info" path="info" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.pustemi.label" path="pustemi.label" width="20%" />
	<acme:list-column code="anonymous.shout.list.label.pustemi.deadline" path="pustemi.deadline" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.pustemi.budget" path="pustemi.budget" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.pustemi.important" path="pustemi.important" width="5%" />
</acme:list>
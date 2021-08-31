<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.text" path="text"  width="50%"/>
	<acme:list-column code="anonymous.shout.list.label.info" path="info" width="30%" />
	<acme:list-column code="anonymous.shout.list.label.keylem" path="dolemite.keylem" width="33%"/>
	<acme:list-column code="anonymous.shout.list.label.deadline" path="dolemite.deadline" width="33%"/>
	<acme:list-column code="anonymous.shout.list.label.budget" path="dolemite.budget" width="33%"/>
	<acme:list-column code="anonymous.shout.list.label.important" path="dolemite.important" width="33%"/>
</acme:list>
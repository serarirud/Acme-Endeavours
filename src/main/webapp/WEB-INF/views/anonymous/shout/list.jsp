<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.text" path="text"  width="25%"/>
	<acme:list-column code="anonymous.shout.list.label.info" path="info" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.xxx.xxx1" path="xxx.xxx1" width="20%" />
	<acme:list-column code="anonymous.shout.list.label.xxx.xxx2" path="xxx.xxx2" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.xxx.xxx3" path="xxx.xxx3" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.xxx.xxx4" path="xxx.xxx4" width="5%" />
</acme:list>
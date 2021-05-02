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

<h2>
	<acme:message code="administrator.work-plan-data.form.title.general-indicators"/>
</h2>

<table class="table table-sm">	
	<tr>
		<th scope="row">
			<acme:message code="administrator.configuration.form.label.total"/>
		</th>
		<td>
			<acme:print value="${total}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.configuration.form.label.finalizados"/>
		</th>
		<td>
			<acme:print value="${finalizados}"/>
		</td>
	
	</tr>	

	<tr>
		<th scope="row">
			<acme:message code="administrator.configuration.form.label.noFinalizados"/>
		</th>
		<td>
			<acme:print value="${noFinalizados}"/>
		</td>
	
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.configuration.form.label.publicados"/>
		</th>
		<td>
			<acme:print value="${publicados}"/>
		</td>
	
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.configuration.form.label.noPublicados"/>
		</th>
		<td>
			<acme:print value="${noPublicados}"/>
		</td>
	
	</tr>
</table>
	
	<script type="text/javascript">
	$(document).ready(function() {

		var data = {
			labels : [
				"Total" , "Finished", "Unfinished", "Published", 'Not published'
				],
			datasets : [
				{
					backgroundColor: [
						 "#EB8974", 
						 "#E52800",
						 "#7699E2",
						 "#0049E5",
						 "#00AA00"],
					data : [
						<jstl:out value="${total}"/>, 
						<jstl:out value="${finalizados}"/>,
						<jstl:out value="${noFinalizados}"/>,
						<jstl:out value="${publicados}"/>,
						<jstl:out value="${noPublicados}"/>
					]
					 
				
				}
			]
		};
		var options = {
			responsive: true,
		    maintainAspectRatio: false,
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>
<div>
	<canvas id="canvas" width=520 height=320 ></canvas>
</div>

<h2>
	<acme:message code="administrator.work-plan-data.form.title.executionPeriods"/>
</h2>

<table class="table table-sm">	
	<tr>
		<th scope="row">
			<acme:message code="administrator.configuration.form.label.mediaPeriodo"/>
		</th>
		<td>
			<acme:print value="${mediaPeriodo}"/> 
			<acme:message code="administrator.work-plan-data.form.result.days"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.configuration.form.label.desviacionPeriodo"/>
		</th>
		<td>
			<acme:print value="${desviacionPeriodo}"/> 
			<acme:message code="administrator.work-plan-data.form.result.days"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.configuration.form.label.maximoPeriodo"/>
		</th>
		<td>
			<acme:print value="${maximoPeriodo}"/> 
			<acme:message code="administrator.work-plan-data.form.result.days"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.configuration.form.label.minimoPeriodo"/>
		</th>
		<td>
			<acme:print value="${minimoPeriodo}"/> 
			<acme:message code="administrator.work-plan-data.form.result.days"/>
		</td>
	</tr>
	
</table>

<h2>
	<acme:message code="administrator.work-plan-data.form.title.workloads"/>
</h2>

<table class="table table-sm">	
	<tr>
		<th scope="row">
			<acme:message code="administrator.configuration.form.label.mediaCarga"/>
		</th>
		<td>
			<acme:print value="${mediaCarga}"/> 
			<acme:message code="administrator.work-plan-data.form.result.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.configuration.form.label.desviacionCarga"/>
		</th>
		<td>
			<acme:print value="${desviacionCarga}"/> 
			<acme:message code="administrator.work-plan-data.form.result.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.configuration.form.label.maximoCarga"/>
		</th>
		<td>
			<acme:print value="${maximoCarga}"/> 
			<acme:message code="administrator.work-plan-data.form.result.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.configuration.form.label.minimoCarga"/>
		</th>
		<td>
			<acme:print value="${minimoCarga}"/> 
			<acme:message code="administrator.work-plan-data.form.result.hours"/>			
		</td>
	</tr>
	
</table>

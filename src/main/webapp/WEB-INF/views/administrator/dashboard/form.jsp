<%--
- form.jsp
-
- Copyright (c) 2012-2021 Rafael Corchuelo.
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
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">	
	<caption><acme:message code="administrator.dashboard.form.title.general-indicators"/></caption>
	
	<!-- CC -->
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.ratio1"/>
		</th>
		<td>
			<acme:print value="${ratio1}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.ratio2"/>
		</th>
		<td>
			<acme:print value="${ratio2}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.avgSheetsUSD"/>
		</th>
		<td>
			<acme:print value="${avgSheetsUSD}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.avgSheetsEUR"/>
		</th>
		<td>
			<acme:print value="${avgSheetsEUR}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.avgSheetsGBP"/>
		</th>
		<td>
			<acme:print value="${avgSheetsGBP}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.devSheetsUSD"/>
		</th>
		<td>
			<acme:print value="${devSheetsUSD}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.devSheetsEUR"/>
		</th>
		<td>
			<acme:print value="${devSheetsEUR}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.devSheetsGBP"/>
		</th>
		<td>
			<acme:print value="${devSheetsGBP}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-task-private"/>
		</th>
		<td>
			<acme:print value="${nPrivateTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-task-public"/>
		</th>
		<td>
			<acme:print value="${nPublicTask}"/>
		</td>
	
	</tr>	

	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-finished-task"/>
		</th>
		<td>
			<acme:print value="${nFinishedTask}"/>
		</td>
	
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-unfinished-task"/>
		</th>
		<td>
			<acme:print value="${nNotFinishedTask}"/>
		</td>
	
	</tr>
</table>
	
	<script type="text/javascript">
	$(document).ready(function() {

		var data = {
			labels : [
				"Public" , "Private", "Finished", "Unfinished"
				],
			datasets : [
				{
					backgroundColor: [
						 "#EB8974", 
						 "#E52800",
						 "#7699E2",
						 "#0049E5"],
					data : [
						<jstl:out value="${nPublicTask}"/>, 
						<jstl:out value="${nPrivateTask}"/>,
						<jstl:out value="${nFinishedTask}"/>,
						<jstl:out value="${nNotFinishedTask}"/>
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
	<acme:message code="administrator.dashboard.form.title.executionPeriods"/>
</h2>

<table class="table table-sm">	
	<caption><acme:message code="administrator.dashboard.form.title.executionPeriods"/></caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average"/>
		</th>
		<td>
			<acme:print value="${averageTaskExecutionPeriods}"/> 
			<acme:message code="administrator.dashboard.form.result.days"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation"/>
		</th>
		<td>
			<acme:print value="${deviationTaskExecutionPeriods}"/> 
			<acme:message code="administrator.dashboard.form.result.days"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum"/>
		</th>
		<td>
			<acme:print value="${minimumTaskExecutionPeriods}"/> 
			<acme:message code="administrator.dashboard.form.result.days"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum"/>
		</th>
		<td>
			<acme:print value="${maximumTaskExecutionPeriods}"/> 
			<acme:message code="administrator.dashboard.form.result.days"/>
		</td>
	</tr>
	
</table>

<h2>
	<acme:message code="administrator.dashboard.form.title.task-workloads"/>
</h2>

<table class="table table-sm">	
	<caption><acme:message code="administrator.dashboard.form.title.task-workloads"/></caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average"/>
		</th>
		<td>
			<acme:print value="${averageTaskWorkloads}"/> 
			<acme:message code="administrator.dashboard.form.result.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation"/>
		</th>
		<td>
			<acme:print value="${deviationTaskWorkloads}"/> 
			<acme:message code="administrator.dashboard.form.result.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum"/>
		</th>
		<td>
			<acme:print value="${minimumTaskWorkloads}"/> 
			<acme:message code="administrator.dashboard.form.result.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum"/>
		</th>
		<td>
			<acme:print value="${maximumTaskWorkloads}"/> 
			<acme:message code="administrator.dashboard.form.result.hours"/>			
		</td>
	</tr>
	
</table>




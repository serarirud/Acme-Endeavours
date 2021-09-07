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
<h2>
	<acme:message code="administrator.dashboard.form.title.sheet"/>
</h2>

<table class="table table-sm">	
	<caption><acme:message code="administrator.dashboard.form.title.sheet"/></caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.sheet.importantRatio"/>
		</th>
		<td>
			<acme:print value="${ratioImportantSheet}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.sheet.zeroBudgetRatio"/>
		</th>
		<td>
			<acme:print value="${ratioZeroBudgetSheet}"/> 
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.avarage.eur"/>
		</th>
		<td>
			<acme:print value="${avarageEUR}"/> 
			<acme:message code="administrator.dashboard.form.result.eur"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation.eur"/>
		</th>
		<td>
			<acme:print value="${deviationEUR}"/> 
			<acme:message code="administrator.dashboard.form.result.eur"/>			
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.avarage.usd"/>
		</th>
		<td>
			<acme:print value="${avarageUSD}"/> 
			<acme:message code="administrator.dashboard.form.result.usd"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation.usd"/>
		</th>
		<td>
			<acme:print value="${deviationUSD}"/> 
			<acme:message code="administrator.dashboard.form.result.usd"/>			
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.avarage.gbp"/>
		</th>
		<td>
			<acme:print value="${avarageGBP}"/> 
			<acme:message code="administrator.dashboard.form.result.gbp"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation.gbp"/>
		</th>
		<td>
			<acme:print value="${deviationGBP}"/> 
			<acme:message code="administrator.dashboard.form.result.gbp"/>			
		</td>
	</tr>
	
</table>
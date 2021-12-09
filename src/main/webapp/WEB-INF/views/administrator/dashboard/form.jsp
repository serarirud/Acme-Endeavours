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
	<acme:message code="administrator.dashboard.form.title.info"/>
</h2>

<table class="table table-sm">	
	<caption><acme:message code="administrator.dashboard.form.title.info"/></caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.info.importantRatio"/>
		</th>
		<td>
			<acme:print value="${ratioImportantInfo}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.info.zeroBudgetRatio"/>
		</th>
		<td>
			<acme:print value="${ratioZeroBudgetInfo}"/> 
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

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">	
	<caption><acme:message code="administrator.dashboard.form.title.general-indicators"/></caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-duties-private"/>
		</th>
		<td>
			<acme:print value="${nPrivateDuties}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-duties-public"/>
		</th>
		<td>
			<acme:print value="${nPublicDuties}"/>
		</td>
	
	</tr>	

	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-finished-duties"/>
		</th>
		<td>
			<acme:print value="${nFinishedDuties}"/>
		</td>
	
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-unfinished-duties"/>
		</th>
		<td>
			<acme:print value="${nNotFinishedDuties}"/>
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
						<jstl:out value="${nPublicDuties}"/>, 
						<jstl:out value="${nPrivateDuties}"/>,
						<jstl:out value="${nFinishedDuties}"/>,
						<jstl:out value="${nNotFinishedDuties}"/>
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
			<acme:print value="${averageDutiesExecutionPeriods}"/> 
			<acme:message code="administrator.dashboard.form.result.days"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation"/>
		</th>
		<td>
			<acme:print value="${deviationDutiesExecutionPeriods}"/> 
			<acme:message code="administrator.dashboard.form.result.days"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum"/>
		</th>
		<td>
			<acme:print value="${minimumDutiesExecutionPeriods}"/> 
			<acme:message code="administrator.dashboard.form.result.days"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum"/>
		</th>
		<td>
			<acme:print value="${maximumDutiesExecutionPeriods}"/> 
			<acme:message code="administrator.dashboard.form.result.days"/>
		</td>
	</tr>
	
</table>

<h2>
	<acme:message code="administrator.dashboard.form.title.duties-workloads"/>
</h2>

<table class="table table-sm">	
	<caption><acme:message code="administrator.dashboard.form.title.duties-workloads"/></caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average"/>
		</th>
		<td>
			<acme:print value="${averageDutiesWorkloads}"/> 
			<acme:message code="administrator.dashboard.form.result.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation"/>
		</th>
		<td>
			<acme:print value="${deviationDutiesWorkloads}"/> 
			<acme:message code="administrator.dashboard.form.result.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum"/>
		</th>
		<td>
			<acme:print value="${minimumDutiesWorkloads}"/> 
			<acme:message code="administrator.dashboard.form.result.hours"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum"/>
		</th>
		<td>
			<acme:print value="${maximumDutiesWorkloads}"/> 
			<acme:message code="administrator.dashboard.form.result.hours"/>			
		</td>
	</tr>
	
</table>


/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (c) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {


	@Autowired
	protected AdministratorDashboardRepository repository;



	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,
			"nPrivateTask", "nPublicTask", "nNotFinishedTask", "nFinishedTask", "averageTaskExecutionPeriods",
			"deviationTaskExecutionPeriods", "minimumTaskExecutionPeriods", "maximumTaskExecutionPeriods",
			"averageTaskWorkloads", "deviationTaskWorkloads", "minimumTaskWorkloads", "maximumTaskWorkloads",
			"ratio1", "ratio2", "avgSheetsUSD", "avgSheetsEUR", "devSheetsUSD", "devSheetsEUR");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		
		final Date today = new Date(System.currentTimeMillis());

		final Dashboard result;
		Integer nPrivateTask;
		Integer nPublicTask;
		Integer nNotFinishedTask;
		Integer nFinishedTask;
		Double averageTaskExecutionPeriods;
		Double deviationTaskExecutionPeriods;
		Integer minimumTaskExecutionPeriods;
		Integer	maximumTaskExecutionPeriods;
		
		Double averageTaskWorkloads;
		Double deviationTaskWorkloads;
		Double	minimumTaskWorkloads;
		Double	maximumTaskWorkloads;
		
		//CC
		Double ratio1;
		final Double ratio2;
		final Double avgSheetsUSD;
		final Double avgSheetsEUR;
		final Double devSheetsUSD;
		final Double devSheetsEUR;

		nPrivateTask = this.repository.nPrivateTask();
		nPublicTask = this.repository.nPublicTask();
		nNotFinishedTask = this.repository.nNotFinishedTask(today);
		nFinishedTask = this.repository.nFinishedTask(today);
		averageTaskExecutionPeriods = this.repository.averageTaskExecutionPeriods();
		deviationTaskExecutionPeriods = this.repository.deviationTaskExecutionPeriods();
		minimumTaskExecutionPeriods = this.repository.minimumTaskExecutionPeriods();
		maximumTaskExecutionPeriods = this.repository.maximumTaskExecutionPeriods();
		
		final List<Task> tasks = this.repository.findAllTask().stream().collect(Collectors.toList());
		Double sum = 0.;
		for(final Task a:tasks) {
			sum += a.getMinutes();
		}
		final Double averageHoursWorkloads = sum/tasks.size()/60; 
		final Integer parteEntera = averageHoursWorkloads.intValue(); 
		final Double parteFracional = (averageHoursWorkloads - parteEntera)*0.6;

		averageTaskWorkloads = parteEntera + parteFracional;
		
		Double sum1=0.;
		for(final Task a:tasks) {
			sum1 += Math.pow(a.getMinutes() - averageHoursWorkloads*60, 2); 
		}
		final Double desviation = Math.sqrt(sum1 / tasks.size())/60;
		final Integer parteEntera1 = desviation.intValue();
		final Double parteFracional1 = (desviation - parteEntera1)*0.6;
		deviationTaskWorkloads = parteEntera1 + parteFracional1;
		minimumTaskWorkloads = this.repository.minimumTaskWorkloads();
		maximumTaskWorkloads = this.repository.maximumTaskWorkloads();
		
		//CC
		final Integer numShoutsAtributo4OK = this.repository.numShoutsAtributo4();
		final Integer numShouts = this.repository.numShouts();
		ratio1=((numShoutsAtributo4OK+0.0)/(numShouts+0.0));
		
		ratio2=0.0; //FALTA PQ NO SE SABE QUE ATRIBUTO SE PIDE
		
		avgSheetsUSD = this.repository.avgSheetsByCurrency("USD");
		avgSheetsEUR = this.repository.avgSheetsByCurrency("EUR");
		devSheetsUSD = this.repository.devSheetsByCurrency("USD");
		devSheetsEUR = this.repository.devSheetsByCurrency("EUR");
		
		result = new Dashboard();
		result.setNPrivateTask(nPrivateTask);
		result.setNPublicTask(nPublicTask);
		result.setNFinishedTask(nFinishedTask);
		result.setNNotFinishedTask(nNotFinishedTask);
		result.setAverageTaskExecutionPeriods(averageTaskExecutionPeriods);
		result.setDeviationTaskExecutionPeriods(deviationTaskExecutionPeriods);
		result.setMinimumTaskExecutionPeriods(minimumTaskExecutionPeriods);
		result.setMaximumTaskExecutionPeriods(maximumTaskExecutionPeriods);
		
		result.setAverageTaskWorkloads(averageTaskWorkloads);
		result.setDeviationTaskWorkloads(deviationTaskWorkloads);
		result.setMinimumTaskWorkloads(minimumTaskWorkloads);
		result.setMaximumTaskWorkloads(maximumTaskWorkloads);
		
		//CC
		result.setRatio1(ratio1);
		result.setRatio2(ratio2);
		result.setAvgSheetsUSD(avgSheetsUSD);
		result.setAvgSheetsEUR(avgSheetsEUR);
		result.setDevSheetsUSD(devSheetsUSD);
		result.setDevSheetsEUR(devSheetsEUR);
		
		return result;
	}
	


}

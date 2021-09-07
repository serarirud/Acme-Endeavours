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
			"ratioShoutsFlaggedAsImportant", "ratioShoutsWithBudgetZero", 
			"averageShoutsGroupedByEUR", "deviationShoutsGroupedByEUR",
			"averageShoutsGroupedByUSD", "deviationShoutsGroupedByUSD",
			"averageShoutsGroupedByGBP","deviationShoutsGroupedByGBP",
			"nPrivateTask", "nPublicTask", "nNotFinishedTask", "nFinishedTask", "averageTaskExecutionPeriods",
			"deviationTaskExecutionPeriods", "minimumTaskExecutionPeriods", "maximumTaskExecutionPeriods",
			"averageTaskWorkloads", "deviationTaskWorkloads", "minimumTaskWorkloads", "maximumTaskWorkloads");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		
		final Date today = new Date(System.currentTimeMillis());

		Dashboard result;
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
		
		Integer shouts;
		Double ratioShoutsFlaggedAsImportant;
		Double ratioShoutsWithBudgetZero;
		Double averageShoutsGroupedByEUR;
		Double deviationShoutsGroupedByEUR;
		Double averageShoutsGroupedByUSD;
		Double deviationShoutsGroupedByUSD;
		Double averageShoutsGroupedByGBP;
		Double deviationShoutsGroupedByGBP;

		nPrivateTask = this.repository.nPrivateTask();
		nPublicTask = this.repository.nPublicTask();
		nNotFinishedTask = this.repository.nNotFinishedTask(today);
		nFinishedTask = this.repository.nFinishedTask(today);
		averageTaskExecutionPeriods = this.repository.averageTaskExecutionPeriods();
		deviationTaskExecutionPeriods = this.repository.deviationTaskExecutionPeriods();
		minimumTaskExecutionPeriods = this.repository.minimumTaskExecutionPeriods();
		maximumTaskExecutionPeriods = this.repository.maximumTaskExecutionPeriods();
		shouts = this.repository.shouts();
		ratioShoutsFlaggedAsImportant = this.repository.shoutsFlaggedAsImportant()*100./shouts;
		ratioShoutsWithBudgetZero = this.repository.shoutsWithBudgetZero()*100./shouts;
		averageShoutsGroupedByEUR = this.repository.averageShoutsGroupedByCurrency("EUR");
		deviationShoutsGroupedByEUR = this.repository.deviationShoutsGroupedByCurrency("EUR");
		averageShoutsGroupedByUSD = this.repository.averageShoutsGroupedByCurrency("USD");
		deviationShoutsGroupedByUSD = this.repository.deviationShoutsGroupedByCurrency("USD");
		averageShoutsGroupedByGBP = this.repository.averageShoutsGroupedByCurrency("GBP");
		deviationShoutsGroupedByGBP = this.repository.deviationShoutsGroupedByCurrency("GBP");
		
		ratioShoutsFlaggedAsImportant = this.roundToTwoDecimals(ratioShoutsFlaggedAsImportant);
		ratioShoutsWithBudgetZero = this.roundToTwoDecimals(ratioShoutsWithBudgetZero);
		averageShoutsGroupedByEUR = this.roundToTwoDecimals(averageShoutsGroupedByEUR);
		deviationShoutsGroupedByEUR = this.roundToTwoDecimals(deviationShoutsGroupedByEUR);
		averageShoutsGroupedByUSD = this.roundToTwoDecimals(averageShoutsGroupedByUSD);
		deviationShoutsGroupedByUSD = this.roundToTwoDecimals(deviationShoutsGroupedByUSD);
		averageShoutsGroupedByGBP = this.roundToTwoDecimals(averageShoutsGroupedByGBP);
		deviationShoutsGroupedByGBP = this.roundToTwoDecimals(deviationShoutsGroupedByGBP);
		
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
		
		result = new Dashboard();
		
		result.setRatioShoutsFlaggedAsImportant(ratioShoutsFlaggedAsImportant);
		result.setRatioShoutsWithBudgetZero(ratioShoutsWithBudgetZero);
		result.setAverageShoutsGroupedByEUR(averageShoutsGroupedByEUR);
		result.setDeviationShoutsGroupedByEUR(deviationShoutsGroupedByEUR);
		result.setAverageShoutsGroupedByUSD(averageShoutsGroupedByUSD);
		result.setDeviationShoutsGroupedByUSD(deviationShoutsGroupedByUSD);
		result.setAverageShoutsGroupedByGBP(averageShoutsGroupedByGBP);
		result.setDeviationShoutsGroupedByGBP(deviationShoutsGroupedByGBP);
		
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
		
		return result;
	}
	
	public Double roundToTwoDecimals(final Double n) {
		return (double) Math.round(n*100) / 100;
	}


}

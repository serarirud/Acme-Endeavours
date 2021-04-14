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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		nPrivateTask = this.repository.nPrivateTask();
		nPublicTask = this.repository.nPublicTask();
		nNotFinishedTask = this.repository.nNotFinishedTask(today);
		nFinishedTask = this.repository.nFinishedTask(today);
		averageTaskExecutionPeriods = this.repository.averageTaskExecutionPeriods();
		deviationTaskExecutionPeriods = this.repository.deviationTaskExecutionPeriods();
		minimumTaskExecutionPeriods = this.repository.minimumTaskExecutionPeriods();
		maximumTaskExecutionPeriods = this.repository.maximumTaskExecutionPeriods();
		averageTaskWorkloads = this.repository.averageTaskWorkloads();
		deviationTaskWorkloads = this.repository.deviationTaskWorkloads();
		minimumTaskWorkloads = this.repository.minimumTaskWorkloads();
		maximumTaskWorkloads = this.repository.maximumTaskWorkloads();
		
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
		
		return result;
	}

}

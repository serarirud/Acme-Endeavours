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

import acme.entities.duties.Duties;
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
			"nPrivateDuties", "nPublicDuties", "nNotFinishedDuties", "nFinishedDuties", "averageDutiesExecutionPeriods",
			"deviationDutiesExecutionPeriods", "minimumDutiesExecutionPeriods", "maximumDutiesExecutionPeriods",
			"averageDutiesWorkloads", "deviationDutiesWorkloads", "minimumDutiesWorkloads", "maximumDutiesWorkloads");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		
		final Date today = new Date(System.currentTimeMillis());

		Dashboard result;
		Integer nPrivateDuties;
		Integer nPublicDuties;
		Integer nNotFinishedDuties;
		Integer nFinishedDuties;
		Double averageDutiesExecutionPeriods;
		Double deviationDutiesExecutionPeriods;
		Integer minimumDutiesExecutionPeriods;
		Integer	maximumDutiesExecutionPeriods;
		
		Double averageDutiesWorkloads;
		final Double deviationDutiesWorkloads;
		final Double	minimumDutiesWorkloads;
		final Double	maximumDutiesWorkloads;

		nPrivateDuties = this.repository.nPrivateDuties();
		nPublicDuties = this.repository.nPublicDuties();
		nNotFinishedDuties = this.repository.nNotFinishedDuties(today);
		nFinishedDuties = this.repository.nFinishedDuties(today);
		averageDutiesExecutionPeriods = this.repository.averageDutiesExecutionPeriods();
		deviationDutiesExecutionPeriods = this.repository.deviationDutiesExecutionPeriods();
		minimumDutiesExecutionPeriods = this.repository.minimumDutiesExecutionPeriods();
		maximumDutiesExecutionPeriods = this.repository.maximumDutiesExecutionPeriods();
		
		final List<Duties> duties = this.repository.findAllDuties().stream().collect(Collectors.toList());
		Double sum = 0.;
		for(final Duties a:duties) {
			sum += a.getMinutes();
		}
		final Double averageHoursWorkloads = sum/duties.size()/60; 
		final Integer parteEntera = averageHoursWorkloads.intValue(); 
		final Double parteFracional = (averageHoursWorkloads - parteEntera)*0.6;

		averageDutiesWorkloads = parteEntera + parteFracional;
		
		Double sum1=0.;
		for(final Duties a:duties) {
			sum1 += Math.pow(a.getMinutes() - averageHoursWorkloads*60, 2); 
		}
		final Double desviation = Math.sqrt(sum1 / duties.size())/60;
		final Integer parteEntera1 = desviation.intValue();
		final Double parteFracional1 = (desviation - parteEntera1)*0.6;
		deviationDutiesWorkloads = parteEntera1 + parteFracional1;
		minimumDutiesWorkloads = this.repository.minimumDutiesWorkloads();
		maximumDutiesWorkloads = this.repository.maximumDutiesWorkloads();
		
		result = new Dashboard();
		result.setNPrivateDuties(nPrivateDuties);
		result.setNPublicDuties(nPublicDuties);
		result.setNFinishedDuties(nFinishedDuties);
		result.setNNotFinishedDuties(nNotFinishedDuties);
		result.setAverageDutiesExecutionPeriods(averageDutiesExecutionPeriods);
		result.setDeviationDutiesExecutionPeriods(deviationDutiesExecutionPeriods);
		result.setMinimumDutiesExecutionPeriods(minimumDutiesExecutionPeriods);
		result.setMaximumDutiesExecutionPeriods(maximumDutiesExecutionPeriods);
		
		result.setAverageDutiesWorkloads(averageDutiesWorkloads);
		result.setDeviationDutiesWorkloads(deviationDutiesWorkloads);
		result.setMinimumDutiesWorkloads(minimumDutiesWorkloads);
		result.setMaximumDutiesWorkloads(maximumDutiesWorkloads);
		
		return result;
	}
	


}

/*
 * AdministratorUserAccountShowService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.workPlanData;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlan.WorkPlan;
import acme.forms.WorkPlanData;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorWorkPlanDataShowService implements AbstractShowService<Administrator, WorkPlanData> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorWorkPlanRepository repository;

	// AbstractShowService<Administrator, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<WorkPlanData> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<WorkPlanData> request, final WorkPlanData entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "total", "finalizados", "noFinalizados", "publicados", "noPublicados"
			, "maximoPeriodo", "minimoPeriodo", "maximoCarga", "minimoCarga", "mediaPeriodo", "desviacionPeriodo"
			, "mediaCarga", "desviacionCarga");
	}

	@Override
	public WorkPlanData findOne(final Request<WorkPlanData> request) {
		assert request != null;

		final WorkPlanData result = new WorkPlanData();
		final WorkPlan wp = new WorkPlan();
		
		final Integer total = this.repository.workPlanAmount();
		final Integer finalizados = this.repository.finishedWorkPlanAmount(Date.valueOf(LocalDate.now()));
		final Integer publicos = this.repository.publishedWorkPlanAmount();
		result.setTotal(total);
		result.setFinalizados(finalizados);
		result.setNoFinalizados(total - finalizados);
		result.setPublicados(publicos);
		result.setNoPublicados(total - publicos);
		
		final Set<WorkPlan> workPlans = this.repository.findAllWorkPlans();
		
		result.setMaximoPeriodo(this.getMaxPeriod(workPlans));
		result.setMinimoPeriodo(this.getMinPeriod(workPlans));
		result.setMaximoCarga(workPlans.stream().map(WorkPlan::getWorkload).max(Double::compare).get());
		result.setMinimoCarga(workPlans.stream().map(WorkPlan::getWorkload).min(Double::compare).get());
		result.setMediaPeriodo(this.repository.averageExecutionPeriods());
		result.setDesviacionPeriodo(this.repository.deviationExecutionPeriods());
		final List<Double> workloads = workPlans.stream().map(WorkPlan::getWorkload).collect(Collectors.toList());
		result.setMediaCarga(this.media(workloads));
		result.setDesviacionCarga(this.deviation(workloads));

		return result;
	}
	
	private Double media(final List<Double> workloads) {
		Double sum = 0.;
		for(final Double workload : workloads) {
			sum += workload;
		}
		
		return sum/workloads.size();
	}
	
	private Double deviation(final List<Double> workloads) {
		final Double media = this.media(workloads);
		Double acum = 0.;
		
		for(final Double workload : workloads) {
			acum += (workload - media)*(workload - media);
		}
		
		return Math.sqrt(acum/workloads.size());
	}

	private String getMaxPeriod(final Set<WorkPlan> workPlans) {
		String res = "";
		Long max = 0L;
		
		for(final WorkPlan wp : workPlans) {
			final Long tempMax = wp.getEndExecutionPeriod().getTime() - wp.getStartExecutionPeriod().getTime();
			if(max < tempMax) {
				res = wp.getStartExecutionPeriod().toString() + " - " + wp.getEndExecutionPeriod().toString();
				max = tempMax;
			}
		}
		return res;
	}
	
	private String getMinPeriod(final Set<WorkPlan> workPlans) {
		String res = "";
		Long min = null;
		
		for(final WorkPlan wp : workPlans) {
			final Long tempMin = wp.getEndExecutionPeriod().getTime() - wp.getStartExecutionPeriod().getTime();
			if(min == null || min > tempMin) {
				res = wp.getStartExecutionPeriod().toString() + " - " + wp.getEndExecutionPeriod().toString();
				min = tempMin;
			}
		}
		return res;
	}
}

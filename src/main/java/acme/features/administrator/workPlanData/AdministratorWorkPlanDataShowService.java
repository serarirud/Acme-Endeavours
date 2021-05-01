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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlanData.WorkPlanData;
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
		
		result.setTotal(10);
		result.setFinalizados(9);
		result.setNoFinalizados(1);
		result.setPublicados(8);
		result.setNoPublicados(2);
		result.setMaximoPeriodo(10);
		result.setMinimoPeriodo(8);
		result.setMaximoCarga(100);
		result.setMinimoCarga(8);
		result.setMediaPeriodo(40.);
		result.setDesviacionPeriodo(50.);
		result.setMediaCarga(50.);
		result.setDesviacionCarga(40.);

		return result;
	}

}

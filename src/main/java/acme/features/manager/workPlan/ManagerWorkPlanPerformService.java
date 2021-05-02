package acme.features.manager.workPlan;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.services.AbstractPerformService;

@Service
public class ManagerWorkPlanPerformService implements AbstractPerformService<Manager, WorkPlan> {

	@Autowired
	private ManagerWorkPlanRepository repository;
	
	@Override
	public boolean authorise(Request<WorkPlan> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(Request<WorkPlan> request, WorkPlan entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(Request<WorkPlan> request, WorkPlan entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "isPublic", "isPublished",
			"startExecutionPeriod", "endExecutionPeriod");
		
	}

	@Override
	public WorkPlan instantiate(Request<WorkPlan> request) {
		assert request != null;

		final WorkPlan result;
		Manager manager;
		
		manager= this.repository.findOneManagerById(request.getPrincipal().getActiveRoleId());
		result= new WorkPlan();
		result.setManager(manager);

		return result;
	}

	@Override
	public void validate(Request<WorkPlan> request, WorkPlan entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("endExecutionPeriod")) {
			errors.state(request, entity.getEndExecutionPeriod().after(entity.getStartExecutionPeriod()), "endExecutionPeriod", "manager.work-plan.form.error.end");
		}
		if(!errors.hasErrors("startExecutionPeriod")) {
			final Date today = Calendar.getInstance().getTime();
			errors.state(request, entity.getStartExecutionPeriod().after(today), "startExecutionPeriod", "manager.work-plan.form.error.start");
		}
		
	}

	@Override
	public void perform(Request<WorkPlan> request, WorkPlan entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setStartExecutionPeriodAndEndExecutionPeriodRand();
		
	}

}

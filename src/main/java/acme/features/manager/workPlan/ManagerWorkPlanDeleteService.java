package acme.features.manager.workPlan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasksWorkPlan.TasksWorkPlan;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagerWorkPlanDeleteService implements AbstractDeleteService<Manager, WorkPlan>{

	@Autowired
	private ManagerWorkPlanRepository repository;
	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;
		
		int workplanId;
		WorkPlan workplan;
		Manager manager;
		Principal principal;
		
		workplanId = request.getModel().getInteger("id");
		workplan = this.repository.findOneById(workplanId);
		manager = workplan.getManager();
		principal = request.getPrincipal();
		
		return manager.getUserAccount().getId() == principal.getAccountId();
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "startExecutionPeriod", "endExecutionPeriod",
			"workload", "isPublic", "isPublished");	
		
	}

	

	@Override
	public void bind(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
		
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		return this.repository.findOneById(id);
	}

	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity !=null;
		
		final Collection<TasksWorkPlan> intermedios = this.repository.findIntermediosByWorkplanId(entity.getId());
		for (final TasksWorkPlan i: intermedios) {
			this.repository.delete(i);
		}
		
		this.repository.delete(entity);
		
	}

}

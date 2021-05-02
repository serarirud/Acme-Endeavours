package acme.features.manager.workPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkPlanUpdateService implements AbstractUpdateService<Manager, WorkPlan>{

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
	public void bind(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
//		final Set<Task> tasks = this.repository.findTasks(request.getPrincipal().getActiveRoleId()).stream().collect(Collectors.toSet());
//		entity.setTasks(tasks);
		
		request.unbind(entity, model, "tasks", "startExecutionPeriod", 
			"endExecutionPeriod", "isPublic", "isPublished");
		
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
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
	


}

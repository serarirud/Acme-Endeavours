package acme.features.manager.workPlan;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerWorkPlanShowService implements AbstractShowService<Manager, WorkPlan>{

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
		
		final Set<Task> tasks = this.repository.findTasks(request.getPrincipal().getActiveRoleId()).stream().collect(Collectors.toSet());
		entity.setTasks(tasks);
		
		request.unbind(entity, model, "tasks", "startExecutionPeriod", 
			"endExecutionPeriod", "isPublic", "isPublished");
		
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		
		assert request != null;
		
		int id;
		WorkPlan w;
		id = request.getModel().getInteger("id");
		w = this.repository.findOneById(id);
	
		
		return w;
	}

}

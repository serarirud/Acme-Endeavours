package acme.features.officer.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Officer;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class OfficerTaskShowService implements AbstractShowService<Officer, Task> {
	
	
	@Autowired
	private OfficerTaskRepository managerTaskRepository;

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		int taskId;
		Task task;
		Officer manager;
		Principal principal;
		
		taskId = request.getModel().getInteger("id");
		task = this.managerTaskRepository.findOneById(taskId);
		manager = task.getOfficer();
		principal = request.getPrincipal();
		
		return manager.getUserAccount().getId() == principal.getAccountId();
		
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "startExecutionPeriod", 
			"endExecutionPeriod", "workload", "description", "link", "isPublic");
		
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;

		int id;
		Task t;
		id = request.getModel().getInteger("id");
		t = this.managerTaskRepository.findOneById(id);
		
		return t;
	}

	
}

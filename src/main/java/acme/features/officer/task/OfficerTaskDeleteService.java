package acme.features.officer.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Officer;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class OfficerTaskDeleteService implements AbstractDeleteService<Officer, Task>{
	
	@Autowired
	protected OfficerTaskRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		int taskId;
		Task task;
		Officer manager;
		Principal principal;
		
		taskId = request.getModel().getInteger("id");
		task = this.repository.findOneById(taskId);
		manager = task.getOfficer();
		principal = request.getPrincipal();
		
		return manager.getUserAccount().getId() == principal.getAccountId();
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "startExecutionPeriod", "endExecutionPeriod",
			"workload", "description", "link", "isPublic");	
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		return this.repository.findOneById(id);
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity !=null;
		
		this.repository.delete(entity);
		
	}

}

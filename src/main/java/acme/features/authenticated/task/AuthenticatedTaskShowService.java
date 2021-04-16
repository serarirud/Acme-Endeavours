package acme.features.authenticated.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedTaskShowService implements AbstractShowService<Authenticated, Task> {
	
	
	@Autowired
	private AuthenticatedTaskRepository authenticatedTaskRepository;

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		return true;
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
		t = this.authenticatedTaskRepository.findOneById(id);
		
		return t;
	}

	
}

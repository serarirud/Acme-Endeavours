package acme.features.officer.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Officer;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class OfficerTaskListService implements AbstractListService<Officer, Task> {

	// Internal state
	
	@Autowired
	private OfficerTaskRepository repository;

	
	// AbstractListService<Administrator, Task> interface
	
	
	//No hay que comprobar el manager pues la url no usa parámetros, solo puede obtener sus tareas
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
			"endExecutionPeriod", "workload");
		
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		assert request != null;
		
		Collection<Task> result;
		Principal principal;
		
		principal = request.getPrincipal();
		result = this.repository.findMany(principal.getActiveRoleId());
		
		return result;
	}
	
	
	
	
}

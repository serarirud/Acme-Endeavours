package acme.features.manager.task;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task>{

	
	@Autowired
	protected ManagerTaskRepository repository;
	
	@Override
	public boolean authorise(Request<Task> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(Request<Task> request, Task entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<Task> request, Task entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "startExecutionPeriod", "endExecutionPeriod");
		request.unbind(entity, model, "workload", "description", "link", "isPublic");
		
	}

	@Override
	public Task instantiate(Request<Task> request) {
		assert request != null;
		
		Task result;
		Manager manager;
		
		manager= this.repository.findOneManagerById(request.getPrincipal().getActiveRoleId());
		result= new Task();
		result.setManager(manager);
		
		return result;
	}

	@Override
	public void validate(Request<Task> request, Task entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("endExecutionPeriod")) {
			errors.state(request, entity.getEndExecutionPeriod().after(entity.getStartExecutionPeriod()), "endExecutionPeriod", "manager.task.form.error.end");
		}
		if(!errors.hasErrors("startExecutionPeriod")) {
			Date today = Calendar.getInstance().getTime();
			errors.state(request, entity.getStartExecutionPeriod().after(today), "startExecutionPeriod", "manager.task.form.error.start");
		}
		if(!errors.hasErrors("workload")) {
			Double workload = entity.getWorkload();
			String str = String.valueOf(workload);
			
			int decNumberInt = Integer.parseInt(str.substring(str.indexOf(".")+1));
			errors.state(request, decNumberInt<60, "workload", "manager.task.form.error.workload");
		}
	}

	@Override
	public void create(Request<Task> request, Task entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}

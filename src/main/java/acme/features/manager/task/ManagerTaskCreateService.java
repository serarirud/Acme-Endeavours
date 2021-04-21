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
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		return true;
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
	public Task instantiate(final Request<Task> request) {
		assert request != null;
		
		Task result;
		Manager manager;
		
		manager= this.repository.findOneManagerById(request.getPrincipal().getActiveRoleId());
		result= new Task();
		result.setManager(manager);
		
		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("endExecutionPeriod")) {
			errors.state(request, entity.getEndExecutionPeriod().after(entity.getStartExecutionPeriod()), "endExecutionPeriod", "manager.task.form.error.end");
		}
		if(!errors.hasErrors("startExecutionPeriod")) {
			final Date today = Calendar.getInstance().getTime();
			errors.state(request, entity.getStartExecutionPeriod().after(today), "startExecutionPeriod", "manager.task.form.error.start");
		}
		if(!errors.hasErrors("workload")) {
			final Double workload = entity.getWorkload();			
			final Integer parteEntera = workload.intValue();
			final Double parteDecimal = workload - parteEntera;
			errors.state(request, parteDecimal<0.6, "workload", "manager.task.form.error.workload");
			
			final Date startExecutionPeriod = entity.getStartExecutionPeriod();
			final Date endExecutionPeriod = entity.getEndExecutionPeriod();
			final long diferencia = endExecutionPeriod.getTime() - startExecutionPeriod.getTime();
			final Integer minutosDiferencia = (int) (diferencia/(1000*60));
			final Integer minutosWorkload = (int) (parteEntera*60 + parteDecimal*100);
			errors.state(request, minutosDiferencia>=minutosWorkload, "workload", "manager.task.form.error.workload2");

		}
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}

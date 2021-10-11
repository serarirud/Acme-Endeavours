package acme.features.officer.duties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duties;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Officer;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class OfficerDutiesDeleteService implements AbstractDeleteService<Officer, Duties>{
	
	@Autowired
	protected OfficerDutiesRepository repository;

	@Override
	public boolean authorise(final Request<Duties> request) {
		assert request != null;
		
		int taskId;
		Duties task;
		Officer manager;
		Principal principal;
		
		taskId = request.getModel().getInteger("id");
		task = this.repository.findOneById(taskId);
		manager = task.getOfficer();
		principal = request.getPrincipal();
		
		return manager.getUserAccount().getId() == principal.getAccountId();
	}

	@Override
	public void bind(final Request<Duties> request, final Duties entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Duties> request, final Duties entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "startExecutionPeriod", "endExecutionPeriod",
			"workload", "description", "link", "isPublic");	
	}

	@Override
	public Duties findOne(final Request<Duties> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		return this.repository.findOneById(id);
	}

	@Override
	public void validate(final Request<Duties> request, final Duties entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Duties> request, final Duties entity) {
		assert request != null;
		assert entity !=null;
		
		this.repository.delete(entity);
		
	}

}

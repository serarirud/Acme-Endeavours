package acme.features.officer.duties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duties;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Officer;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class OfficerDutiesShowService implements AbstractShowService<Officer, Duties> {
	
	
	@Autowired
	private OfficerDutiesRepository officerTaskRepository;

	@Override
	public boolean authorise(final Request<Duties> request) {
		assert request != null;
		
		int taskId;
		Duties task;
		Officer officer;
		Principal principal;
		
		taskId = request.getModel().getInteger("id");
		task = this.officerTaskRepository.findOneById(taskId);
		officer = task.getOfficer();
		principal = request.getPrincipal();
		
		return officer.getUserAccount().getId() == principal.getAccountId();
		
	}

	@Override
	public void unbind(final Request<Duties> request, final Duties entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "startExecutionPeriod", 
			"endExecutionPeriod", "workload", "description", "link", "isPublic");
		
	}

	@Override
	public Duties findOne(final Request<Duties> request) {
		assert request != null;

		int id;
		Duties t;
		id = request.getModel().getInteger("id");
		t = this.officerTaskRepository.findOneById(id);
		
		return t;
	}

	
}

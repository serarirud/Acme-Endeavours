package acme.features.anonymous.duties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duties;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousDutiesShowService implements AbstractShowService<Anonymous, Duties> {
	
	
	@Autowired
	private AnonymousDutiesRepository anonymousTaskRepository;

	@Override
	public boolean authorise(final Request<Duties> request) {
		assert request != null;
		
		return true;
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
		t = this.anonymousTaskRepository.findOneById(id);
		
		return t;
	}

	
}

package acme.features.anonymous.workPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousWorkPlanShowService implements AbstractShowService<Anonymous, WorkPlan>{

	@Autowired
	private AnonymousWorkPlanRepository repo;
	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "startExecutionPeriod", 
			"endExecutionPeriod", "isPublic", "isPublished");
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		assert request != null;

		int id;
		id = request.getModel().getInteger("id");
		final WorkPlan w = this.repo.findOneById(id);
		
		return w;
	}

}

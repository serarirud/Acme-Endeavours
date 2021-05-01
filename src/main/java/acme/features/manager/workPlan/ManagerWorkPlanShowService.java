package acme.features.manager.workPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerWorkPlanShowService implements AbstractShowService<Manager, WorkPlan>{

	@Autowired
	private ManagerWorkPlanRepository repository;
	
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
		
		request.unbind(entity, model, "tasks", "startExecutionPeriod", 
			"endExecutionPeriod", "workload", "isPublic", "isPublished");
		
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		
		assert request != null;
		
		int id;
		WorkPlan w;
		id = request.getModel().getInteger("id");
		w = this.repository.findOneById(id);
		
		return w;
	}

}

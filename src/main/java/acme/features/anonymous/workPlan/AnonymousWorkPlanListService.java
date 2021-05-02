package acme.features.anonymous.workPlan;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousWorkPlanListService implements AbstractListService<Anonymous, WorkPlan>{

	@Autowired
	private AnonymousWorkPlanRepository repository;
	
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

		entity.setTasks(this.repository.findTasksByWorkPlan(entity.getId()).stream().collect(Collectors.toSet()));
		System.out.println(entity.getTasks().size());
		
		request.unbind(entity, model, "id", "startExecutionPeriod", 
			"endExecutionPeriod", "workload");
		
	}
		
	

	@Override
	public Collection<WorkPlan> findMany(final Request<WorkPlan> request) {
		assert request != null;
		
		Collection<WorkPlan> result;
		
		final Date today = new Date(System.currentTimeMillis());
		
		result = this.repository.findMany(today);
		
		return result;
	}

}

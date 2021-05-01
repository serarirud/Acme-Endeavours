package acme.features.manager.workPlan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workPlan.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.services.AbstractListService;

@Service
public class ManagerWorkPlanDeleteService implements AbstractListService<Manager, WorkPlan>{

	@Autowired
	private ManagerWorkPlanRepository repository;
	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<WorkPlan> findMany(final Request<WorkPlan> request) {
		// TODO Auto-generated method stub
		return null;
	}

}

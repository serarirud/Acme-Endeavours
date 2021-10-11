package acme.features.officer.duties;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duties;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Officer;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class OfficerDutiesListService implements AbstractListService<Officer, Duties> {

	// Internal state
	
	@Autowired
	private OfficerDutiesRepository repository;

	
	// AbstractListService<Administrator, Task> interface
	
	
	//No hay que comprobar el manager pues la url no usa parámetros, solo puede obtener sus tareas
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
			"endExecutionPeriod", "workload");
		
	}

	@Override
	public Collection<Duties> findMany(final Request<Duties> request) {
		assert request != null;
		
		Collection<Duties> result;
		Principal principal;
		
		principal = request.getPrincipal();
		result = this.repository.findMany(principal.getActiveRoleId());
		
		return result;
	}
	
	
	
	
}

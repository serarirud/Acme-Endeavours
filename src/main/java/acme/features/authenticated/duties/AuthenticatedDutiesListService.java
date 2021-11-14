package acme.features.authenticated.duties;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duties;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedDutiesListService implements AbstractListService<Authenticated, Duties> {

	// Internal state
	
	@Autowired
	private AuthenticatedDutiesRepository repository;

	
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
		
		final Date today = new Date(System.currentTimeMillis());
		
		result = this.repository.findMany(today);
		
		return result;
	}
	
	
	
	
}

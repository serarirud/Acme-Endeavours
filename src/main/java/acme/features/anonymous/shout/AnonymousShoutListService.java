package acme.features.anonymous.shout;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.info.Info;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousShoutListService implements AbstractListService<Anonymous, Info> {
	
	// Internal state -------------------------------------------------------------------
	
	@Autowired
	AnonymousShoutRepository repository;
	
	// AbstractListService<Administrator, Shout> interface ------------------------------
	
	@Override
	public boolean authorise(final Request<Info> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public void unbind(final Request<Info> request, final Info entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		//################################################ CAMBIAR ################################################################	
		request.unbind(entity, model, "shout.author", "shout.text", "shout.moment", "shout.info", "pattern", "moment", "money", "important");
		//#########################################################################################################################	
	}
	
	@Override
	public Collection<Info> findMany(final Request<Info> request) {
		assert request != null;
		
		Collection<Info> result;
		Calendar calendar;
		Date deadline;
		
		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		deadline = calendar.getTime();
		
		result = this.repository.findMany(deadline);
		
		return result;
	}

}

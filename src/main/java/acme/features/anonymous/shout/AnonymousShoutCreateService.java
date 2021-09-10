package acme.features.anonymous.shout;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.pomel.Pomel;
import acme.entities.shouts.Shout;
import acme.features.configuration.ConfigurationService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout>{
	
	// Internal state -------------------------------------------------------------------
	
	@Autowired
	protected AnonymousShoutRepository shoutRepository;

	@Autowired
	protected ConfigurationService confService;
	
	
	// AbstractCreateService<Administrator, Shout> interface ------------------------
		
	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;
		return true;
	}
		
	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
			
		request.bind(entity, errors);
	}
		
	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("codePlaceholder", Pomel.getCodeRegExp(entity.getMoment()));
			
		request.unbind(entity, model, "author", "text", "info");
	}
		
	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;
		
		Shout result;
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		
		result = new Shout();
		result.setMoment(moment);
		
		return result;
	}
	
	@Override 
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Calendar cal = Calendar.getInstance();			
		Pomel pomel = entity.getPomel();
		Integer day = cal.get(Calendar.DAY_OF_MONTH);
		Integer month = cal.get(Calendar.MONTH) + 1;
		Integer year = cal.get(Calendar.YEAR);
		
		if(!errors.hasErrors("text")) {
			final boolean umbralSuperado = this.confService.spamFilter(entity.getText());
			errors.state(request, !umbralSuperado,"text", "manager.task.error.umbral-superado");
		}
		
		if(!errors.hasErrors("pomel.budget")) {
			final String currency = entity.getPomel().getBudget().getCurrency();
			errors.state(request, currency.equals("EUR") || currency.equals("USD") || currency.equals("GBP"), "pomel.budget", "anonymous.shout.error.money");
		}
		if(!errors.hasErrors("pomel.tracker")) {
			final Optional<Pomel> opt = this.shoutRepository.findPomelByDate(entity.getPomel().getTracker());
			errors.state(request, !opt.isPresent(), "pomel.tracker", "anonymous.shout.error.trackerFind");
			
			
			String dayS = "";
			String monthS = "";
			if(day<10) {
				dayS = String.format("%02d", day);
			}else {
				dayS = day.toString();
			}
			if(month<10) {
				monthS = String.format("%02d", month);
			}else {
				monthS = month.toString();
			}
			
			String yearTracker = pomel.getTracker().substring(0,2);
			
			String mmddTracker = pomel.getTracker().substring(8,12);
			
			String op = monthS + dayS;
			
			errors.state(request, year.toString().substring(2,4).equals(yearTracker) && mmddTracker.equals(op) , "pomel.tracker", "anonymous.shout.error.tracker");
		}
		if(!errors.hasErrors("pomel.deadline")) {
			final Date deadline = pomel.getDeadline();
			cal.add(Calendar.DAY_OF_MONTH, 7);
			errors.state(request, deadline.after(cal.getTime()), "pomel.deadline", "anonymous.shout.error.deadline");
		}
		
		

	}
	
	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		final Pomel pomel = entity.getPomel();
		
		this.shoutRepository.save(pomel);
		this.shoutRepository.save(entity);
	}

}

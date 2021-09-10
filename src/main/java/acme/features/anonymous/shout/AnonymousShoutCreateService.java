package acme.features.anonymous.shout;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.features.configuration.ConfigurationService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
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
			
		request.unbind(entity, model, "author", "text", "info", "pustemi.label", "pustemi.deadline", "pustemi.budget", "pustemi.important");
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
		
		if(!errors.hasErrors("text")) {
			final boolean umbralSuperado = this.confService.spamFilter(entity.getText());
			errors.state(request, !umbralSuperado, "text", "anonymous.shout.error.umbral-superado");
		}
		
		if(!errors.hasErrors("pustemi.label")) {
			
			final String label = entity.getPustemi().getLabel();
			
			final String[] split = label.split("-");
			
			final Integer year = Integer.valueOf("20" + split[0]);
			final Integer month = Integer.valueOf(split[1].substring(0,2));
			final Integer day = Integer.valueOf(split[1].substring(2));
	
			final Boolean correctPattern = LocalDate.now().getYear() == year && LocalDate.now().getMonthValue() == month && LocalDate.now().getDayOfMonth() == day;
			final Optional<Shout> opt = this.shoutRepository.findShoutByLabel(label);

			errors.state(request, correctPattern, "pustemi.label", "anonymous.shout.error.incorrect-label");
			errors.state(request, !opt.isPresent(), "pustemi.label", "anonymous.shout.error.repeated-label");

		}
		
		if(!errors.hasErrors("pustemi.deadline")) {
			
			final Date deadline = entity.getPustemi().getDeadline();
			
			final Calendar condition = Calendar.getInstance();
			condition.add(Calendar.WEEK_OF_MONTH, +1);
			
			errors.state(request, deadline.after(condition.getTime()) , "pustemi.deadline", "anonymous.shout.error.deadline");
		}
		
		if(!errors.hasErrors("pustemi.budget")) {
			
			final Money budget = entity.getPustemi().getBudget();
			
			final Boolean condition = budget.getCurrency().equals("EUR") || budget.getCurrency().equals("USD") || budget.getCurrency().equals("GBP");
			
			errors.state(request, condition, "pustemi.budget", "anonymous.shout.error.budget");
		}
	}
	
	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.shoutRepository.save(entity.getPustemi());
		this.shoutRepository.save(entity);
	}


}

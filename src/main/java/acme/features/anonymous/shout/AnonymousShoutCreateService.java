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
			
		request.unbind(entity, model, "author", "text", "info", "xxx.xxx1", "xxx.xxx2", "xxx.xxx3", "xxx.xxx4");
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
		
		if(!errors.hasErrors("xxx.xxx1")) {
			
			final String xxx1 = entity.getXxx().getXxx1();
			
			final String[] split = xxx1.split("/");
			
			final Integer day = Integer.valueOf(split[1]);
			final Integer month = Integer.valueOf(split[2]);
			final Integer year = Integer.valueOf("20" + split[3]);
	
			final Boolean correctPattern = LocalDate.now().getYear() == year && LocalDate.now().getMonthValue() == month && LocalDate.now().getDayOfMonth() == day;
			final Optional<Shout> opt = this.shoutRepository.findShoutByXXX1(xxx1);

			errors.state(request, correctPattern, "xxx.xxx1", "anonymous.shout.error.incorrect-xxx1");
			errors.state(request, !opt.isPresent(), "xxx.xxx1", "anonymous.shout.error.repeated-xxx1");

		}
		
		if(!errors.hasErrors("xxx.xxx2")) {
			
			final Date xxx2 = entity.getXxx().getXxx2();
			
			final Calendar condition = Calendar.getInstance();
			condition.add(Calendar.WEEK_OF_MONTH, +1);
			
			errors.state(request, xxx2.after(condition.getTime()) , "xxx.xxx2", "anonymous.shout.error.xxx2");
		}
		
		if(!errors.hasErrors("xxx.xxx3")) {
			
			final Money xxx3 = entity.getXxx().getXxx3();
			
			final Boolean condition = xxx3.getCurrency().equals("EUR") || xxx3.getCurrency().equals("USD") || xxx3.getCurrency().equals("GBP");
			
			errors.state(request, condition, "xxx.xxx3", "anonymous.shout.error.xxx3");
		}
	}
	
	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.shoutRepository.save(entity.getXxx());
		this.shoutRepository.save(entity);
	}


}

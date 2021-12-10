package acme.features.anonymous.info;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.info.Info;
import acme.entities.shouts.Shout;
import acme.features.configuration.ConfigurationService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Info>{
	
	// Internal state -------------------------------------------------------------------
	
	@Autowired
	protected AnonymousShoutRepository shoutRepository;

	@Autowired
	protected ConfigurationService confService;
		
	// AbstractCreateService<Administrator, Shout> interface ------------------------
		
	@Override
	public boolean authorise(final Request<Info> request) {
		assert request != null;
			
		return true;
	}
		
	@Override
	public void bind(final Request<Info> request, final Info entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
			
		request.bind(entity, errors);
	}
		
	@Override
	public void unbind(final Request<Info> request, final Info entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		//################################################ CAMBIAR ################################################################	
		request.unbind(entity, model, "shout.author", "shout.text", "shout.info", "pattern", "moment", "money", "important");
		//#########################################################################################################################	
	}
		
	@Override
	public Info instantiate(final Request<Info> request) {
		assert request != null;
		
		final Info result;
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		
		final Shout shout = new Shout();
		shout.setMoment(moment);
		result = new Info();
		result.setShout(shout);
		
		return result;
	}
	
	@Override 
	public void validate(final Request<Info> request, final Info entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if(!errors.hasErrors("shout.text")) {
			final boolean umbralSuperado = this.confService.spamFilter(entity.getShout().getText());
			errors.state(request, !umbralSuperado, "shout.text", "anonymous.shout.error.umbral-superado");
		}
		final String anonymousError = "anonymous.shout.error.";
		//################################################ CAMBIAR ################################################################
		final String patternName = "pattern"; // REFACTORIZAR EL NOMBRE DE LA VARIABLE
		//#########################################################################################################################	
		// ^\\w{2,4}/dd/mm/yy$
		if(!errors.hasErrors(patternName)) {
			//################################################ CAMBIAR ################################################################	
			final String pattern = entity.getPattern();

			final String date = pattern.split("#")[0];
			final String day = date.substring(4, 6);
			final String month = date.substring(2, 4);
			final String year = "20" + date.substring(0, 2);	
			//#########################################################################################################################			
			final LocalDate today = LocalDate.now();
			final Boolean condicion = today.getYear() == Integer.valueOf(year) && today.getMonthValue() == Integer.valueOf(month) && today.getDayOfMonth() == Integer.valueOf(day);
	
			errors.state(request, condicion, patternName, anonymousError + patternName + ".day");
			//################################################ CAMBIAR ################################################################			
			errors.state(request, !this.shoutRepository.findSheetByPattern(pattern).isPresent(), patternName, anonymousError + patternName + ".duplicated");
			//#########################################################################################################################		
		}
		//################################################ CAMBIAR ################################################################	
		final String momentName = "moment"; // REFACTORIZAR EL NOMBRE DE LA VARIABLE
		//#########################################################################################################################	
		if(!errors.hasErrors(momentName)) {
			//################################################ CAMBIAR ################################################################	
			final Date moment = entity.getMoment();
			//#########################################################################################################################		
			
			final Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.WEEK_OF_MONTH, +1);
			errors.state(request, moment.after(calendar.getTime()), momentName, anonymousError + momentName);
		}
		//################################################ CAMBIAR ################################################################	
		final String moneyName = "money"; // REFACTORIZAR EL NOMBRE DE LA VARIABLE
		//#########################################################################################################################	
		if(!errors.hasErrors(moneyName)) {
			//################################################ CAMBIAR ################################################################	
			final String currency = entity.getMoney().getCurrency();
			//#########################################################################################################################		
            errors.state(request, currency.equals("EUR") || currency.equals("USD") || currency.equals("GBP"), moneyName, anonymousError + moneyName);
		}
		try {
			errors.state(request, entity.getMoney().getAmount()>=0, moneyName, anonymousError + moneyName + ".amount");
		}catch (final Exception e) {
		}
	}
	
	@Override
	public void create(final Request<Info> request, final Info entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		entity.getShout().setMoment(moment);
		//################################################ CAMBIAR ################################################################	
		this.shoutRepository.save(entity.getShout());
		//#########################################################################################################################	
		this.shoutRepository.save(entity);
	}


}
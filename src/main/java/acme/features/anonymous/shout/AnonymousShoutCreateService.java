package acme.features.anonymous.shout;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		//################################################ CAMBIAR ################################################################	
		request.unbind(entity, model, "author", "text", "info", "sheet.pattern", "sheet.moment", "sheet.money", "sheet.important");
		//#########################################################################################################################	
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
			errors.state(request, !umbralSuperado,"text", "manager.task.error.umbral-superado");
		}
		final String anonymousError = "anonymous.shout.error.";
		//################################################ CAMBIAR ################################################################
		final String patternName = "sheet.pattern"; // REFACTORIZAR EL NOMBRE DE LA VARIABLE
		//#########################################################################################################################	
		// ^\\w{2,4}/dd/mm/yy$
		if(!errors.hasErrors(patternName)) {
			//################################################ CAMBIAR ################################################################	
			final String pattern = entity.getSheet().getPattern();

			final String[] split = pattern.split("/");
			final String day = split[1];
			final String month = split[2];
			final String year = "20" + split[3];
			//#########################################################################################################################			
			final LocalDate today = LocalDate.now();
			final Boolean condicion = today.getYear() == Integer.valueOf(year) && today.getMonthValue() == Integer.valueOf(month) && today.getDayOfMonth() == Integer.valueOf(day);
	
			errors.state(request, condicion, patternName, anonymousError + patternName + ".day");
			//################################################ CAMBIAR ################################################################			
			errors.state(request, this.shoutRepository.findSheetByPattern(pattern).isEmpty(), patternName, anonymousError + patternName + ".duplicated");
			//#########################################################################################################################		
		}
		//################################################ CAMBIAR ################################################################	
		final String momentName = "sheet.moment"; // REFACTORIZAR EL NOMBRE DE LA VARIABLE
		//#########################################################################################################################	
		if(!errors.hasErrors(momentName)) {
			//################################################ CAMBIAR ################################################################	
			final Date moment = entity.getSheet().getMoment();
			//#########################################################################################################################		
			
			final Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.WEEK_OF_MONTH, +1);
			errors.state(request, moment.after(calendar.getTime()), momentName, anonymousError + momentName);
		}
		//################################################ CAMBIAR ################################################################	
		final String moneyName = "sheet.money"; // REFACTORIZAR EL NOMBRE DE LA VARIABLE
		//#########################################################################################################################	
		if(!errors.hasErrors(moneyName)) {
			//################################################ CAMBIAR ################################################################	
			final String currency = entity.getSheet().getMoney().getCurrency();
			//#########################################################################################################################		
            errors.state(request, currency.equals("EUR") || currency.equals("USD") || currency.equals("GBP"), moneyName, anonymousError + moneyName);
		}
		try {
			errors.state(request, entity.getSheet().getMoney().getAmount()>=0, moneyName, anonymousError + moneyName + ".amount");
		}catch (final Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		//################################################ CAMBIAR ################################################################	
		this.shoutRepository.save(entity.getSheet());
		//#########################################################################################################################	
		this.shoutRepository.save(entity);
	}


}

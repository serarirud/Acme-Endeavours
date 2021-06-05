package acme.features.anonymous.shout;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.sheets.Sheet;
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
		
		if(!errors.hasErrors("text")) {
			final boolean umbralSuperado = this.confService.spamFilter(entity.getText());
			errors.state(request, !umbralSuperado,"text", "manager.task.error.umbral-superado");
		}
		
		if(!errors.hasErrors("sheet.atr1")) {
			final Optional<Sheet> opt = this.shoutRepository.findSheetByAtr1(entity.getSheet().getAtr1());
			errors.state(request, !opt.isPresent(), "sheet.atr1", "anonymous.shout.error.atr1");
		}
		
		//atr2 debe ser futuro en el momento de ser creado
		if(!errors.hasErrors("sheet.atr2") && entity.getSheet().getAtr2()!=null) {
			final Date today = Calendar.getInstance().getTime();
			errors.state(request, entity.getSheet().getAtr2().after(today), 
				"sheet.atr2", "anonymous.shout.error.atr2");
		}
		
		//atr3 solo debe aceptar 2 tipos de dinero (ej: USD, EUR).
		if(!errors.hasErrors("sheet.atr3")) {
			final String currency = entity.getSheet().getAtr3().getCurrency();
			errors.state(request, currency.equals("EUR") || currency.equals("USD"), "sheet.atr3", "anonymous.shout.error.atr3");
		}
		
		
	}
	
	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		final Sheet sheet = entity.getSheet();
		
		this.shoutRepository.save(sheet);
		this.shoutRepository.save(entity);
	}


}

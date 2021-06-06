package acme.features.anonymous.shout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.sheet.Sheet;
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
			
		request.unbind(entity, model, "author", "text", "info", "sheet.atributo1", 
			"sheet.atributo2","sheet.atributo3", "sheet.atributo4");
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
		
		if(!errors.hasErrors("sheet.atributo1") && entity.getSheet().getAtributo1()!=null) {
			final Optional<Sheet> sheet = this.shoutRepository.findSheetByAtributo1(entity.getSheet().getAtributo1());			
			errors.state(request, entity.getSheet().getAtributo1().equals(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/mm/yyyy"))), "sheet.atributo1", "error-atributo1-validacion1");
			errors.state(request, !sheet.isPresent(), "sheet.atributo1", "error-atributo1-validacion2");
		}
		
		
		if(!errors.hasErrors("sheet.atributo2") && entity.getSheet().getAtributo2()!=null) {
			final Date hoy = Calendar.getInstance().getTime();
			errors.state(request, entity.getSheet().getAtributo2().before(hoy), "sheet.atributo2", "error-atributo2-validacion");
		}
		
		if(!errors.hasErrors("sheet.atributo3") && entity.getSheet().getAtributo3()!=null) {
			final String currency = entity.getSheet().getAtributo3().getCurrency();
			errors.state(request, currency.equals("EUR")||currency.equals("USD"), "sheet.atributo3", "error-atributo3-validacion-currency");
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

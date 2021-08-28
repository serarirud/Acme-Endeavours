package acme.features.anonymous.shout;

import java.time.LocalDate;
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
			
		request.unbind(entity, model, "author", "text", "info", "sheet.name", 
			"sheet.deadline","sheet.budget", "sheet.important");
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
		
		//Comprobación del atributo name con la fecha actual
		if(!errors.hasErrors("sheet.name")) {
			final Optional<Sheet> sheet = this.shoutRepository.findSheetByName(entity.getSheet().getName());
			
			final String name = entity.getSheet().getName();
			final String dia = name.split("-")[0].substring(0, 2);
			final String mes = name.split("-")[2].substring(0, 2);
			final String anyo = "20"+name.split("-")[2].substring(2);
			
			final LocalDate currentDay = LocalDate.now();
			final Boolean diaActual = currentDay.getDayOfMonth()==Integer.valueOf(dia)
					&& currentDay.getMonthValue()==Integer.valueOf(mes)
					&& currentDay.getYear()==Integer.valueOf(anyo);
			
			errors.state(request, diaActual, "sheet.name", "error-atributo1-validacion1");
			errors.state(request, !sheet.isPresent(), "sheet.name", "error-atributo1-validacion2");
		}
		
		if(!errors.hasErrors("sheet.deadline")) {
			Calendar calendar;
			Date deadline;
			
			calendar = Calendar.getInstance();
			calendar.add(Calendar.WEEK_OF_YEAR, 1);
			deadline = calendar.getTime();
			errors.state(request, entity.getSheet().getDeadline().after(deadline), "sheet.deadline", "error-atributo2-validacion");
		}
		
		if(!errors.hasErrors("sheet.budget")) {
			final String currency = entity.getSheet().getBudget().getCurrency();
			errors.state(request, currency.equals("USD")||currency.equals("EUR")||currency.equals("GBP"), "sheet.budget", "error-atributo3-validacion-currency");
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

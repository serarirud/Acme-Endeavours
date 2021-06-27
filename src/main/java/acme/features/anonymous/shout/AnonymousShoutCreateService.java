package acme.features.anonymous.shout;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.kolems.Kolem;
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
			
		request.unbind(entity, model);
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
		
		if(!errors.hasErrors("kolem.ticker")) {
			final Optional<Kolem> opt = this.shoutRepository.findKolemByTicker(entity.getKolem().getTicker());
			errors.state(request, !opt.isPresent(), "kolem.ticker", "anonymous.shout.error.atr1");
			
			final Calendar calendar = Calendar.getInstance();
			final String year = Integer.toString(calendar.get(Calendar.YEAR)).substring(2);
			String month = Integer.toString(calendar.get(Calendar.MONTH)+1);
			String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
			
			if(Integer.valueOf(day)<10) {
				day ="0"+day;
			}
			
			if(Integer.valueOf(month)<10) {
				month ="0"+month;
			}
			
			final String str = entity.getKolem().getTicker();
			final String[] array = str.split(":");
			final String monthTicker = array[2].substring(0, 2);
			final String dayTicker = array[2].substring(2);
			errors.state(request, array[1].equals(year) && monthTicker.equals(month) && dayTicker.equals(day), 
				"kolem.ticker", "anonymous.shout.error2.atr1");
		}
		
		
		/* VALIDACION ATR1 SE CORRESPONDE CON EL DÍA ACTUAL
		 * final Date today = Calendar.getInstance().getTime();
			final Calendar calendar = Calendar.getInstance();
			final String year = Integer.valueOf(calendar.get(Calendar.YEAR)).toString();
			final String month = Integer.valueOf(calendar.get(Calendar.MONTH)+1).toString();
			final String day = Integer.valueOf(calendar.get(Calendar.DAY_OF_MONTH)+1).toString();

			final String str = entity.getSheet().getAtr1();
			final String[] array = str.split("-");
			errors.state(request, array[0].equals(year) && array[1].equals(month) && array[2].equals(day), 
				"sheet.atr1", "anonymous.shout.error2.atr1");
		 * 
		 */
		
		//atr2 debe ser futuro en el momento de ser creado
		//Si es pasado simplemente eliminar esto
		if(!errors.hasErrors("kolem.deadline")) {
			final Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, 7);
			final Date weekAfter = calendar.getTime();
			errors.state(request, entity.getKolem().getDeadline().after(weekAfter), 
				"kolem.deadline", "anonymous.shout.error.atr2");
		}
		
		//atr3 solo debe aceptar 2 tipos de dinero (ej: USD, EUR).
		if(!errors.hasErrors("kolem.budget")) {
			final String currency = entity.getKolem().getBudget().getCurrency();
			errors.state(request, currency.equals("EUR") || currency.equals("USD"), "kolem.budget", "anonymous.shout.error.atr3");
		}
		
		
	}
	
	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		final Kolem kolem = entity.getKolem();
		
		this.shoutRepository.save(kolem);
		this.shoutRepository.save(entity);
	}


}

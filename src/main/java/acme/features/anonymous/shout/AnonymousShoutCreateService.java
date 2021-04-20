package acme.features.anonymous.shout;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.features.spamWord.SpamWordFilterService;
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
	protected SpamWordFilterService spamService;
		
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
		result.setAuthor("John Doe");
		result.setText("Lorem ipsum!");
		result.setMoment(moment);
		result.setInfo("http://example.org");
		
		return result;
	}
	
	@Override 
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
//		final List<SpamWord> words = this.spamRepo.findMany();
//		final Integer palabrasShout = entity.getText().toLowerCase().split(" ").length;
//		final String text =" " +entity.getText().toLowerCase().replace(",", " ").replace(".", " ").replace(";", " ")
//			.replace(":", " ").replace("(", " ").replace(")", " ").replace("-", " ").replace("_", " ")
//			.replace("<", " ").replace(">", " ").replace("¿", " ").replace("?", " ").replace("¡", " ")
//			.replace("!", " ").replace("'", " ")+" ";
//		
//		Integer contador=0;
//		
//		for(int i=0; i<words.size();i++) {
//			final String palabra = " "+words.get(i).getWord()+" ";
//			contador+=text.split(palabra).length-1; 
//		}
//		
//		final Integer umbral=contador*100/palabrasShout;
//		
//		final boolean umbralSuperado=umbral>=10;
		
		final boolean umbralSuperado = this.spamService.spamFilter(entity.getText(), 10);
		
		errors.state(request, !umbralSuperado, "umbral", "anonymous.shout.error.umbral-superado");
	}
	
	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.shoutRepository.save(entity);
	}


}

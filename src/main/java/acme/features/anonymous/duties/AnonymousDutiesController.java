package acme.features.anonymous.duties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.duties.Duties;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/task/")
public class AnonymousDutiesController extends AbstractController<Anonymous, Duties>{

	// Internal state
	
	@Autowired
	private AnonymousDutiesListService listService;
	
	@Autowired
	private AnonymousDutiesShowService showService;
	
	// Constructors
	
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
	
}

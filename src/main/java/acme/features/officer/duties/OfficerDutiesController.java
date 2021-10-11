package acme.features.officer.duties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.duties.Duties;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Officer;

@Controller
@RequestMapping("/officer/task/")
public class OfficerDutiesController extends AbstractController<Officer, Duties>{

	// Internal state
	
	@Autowired
	private OfficerDutiesListService listService;
	
	@Autowired
	private OfficerDutiesShowService showService;
	
	@Autowired
	private OfficerDutiesCreateService createService;
	
	@Autowired
	private OfficerDutiesUpdateService updateService;
	
	@Autowired
	private OfficerDutiesDeleteService deleteService;
	
	// Constructors
	
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
	
}

package acme.features.officer.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Officer;

@Controller
@RequestMapping("/officer/task/")
public class OfficerTaskController extends AbstractController<Officer, Task>{

	// Internal state
	
	@Autowired
	private OfficerTaskListService listService;
	
	@Autowired
	private OfficerTaskShowService showService;
	
	@Autowired
	private OfficerTaskCreateService createService;
	
	@Autowired
	private OfficerTaskUpdateService updateService;
	
	@Autowired
	private OfficerTaskDeleteService deleteService;
	
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

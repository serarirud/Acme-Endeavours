package acme.entities.tasksWorkPlan;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TasksWorkPlan extends DomainEntity {
	
	protected static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	private WorkPlan workplan;
	
	@ManyToOne(optional=false)
	private Task task;
}

package acme.entities.workPlan;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class WorkPlan extends DomainEntity  {
	
	//Serialisation identifier
	
	protected static final long serialVersionUID = 1L;
	
	//Attributes
	
	@OneToMany
	private Set<Task> tasks;
	
	@NotNull
	private Boolean isPublic;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date startExecutionPeriod;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date endExecutionPeriod;
	
	@Transient
	public Double getWorkload() {
		Double res = 0.;
		
		for(final Task task: this.tasks) {
			res += task.getWorkload();
		}
		
		return res;
	}
	
	public Boolean isValid() {
		Boolean res = true;
		
		// Restriccion 1, si es privado no puede contener tasks publicas
		if(!this.isPublic) {
			for(final Task task: this.tasks) {
				res = res && !task.getIsPublic();
			}
		}
		
		// Restriccion 2, el tiempo de ejecución tiene que contener los tiempos de ejecucion de cada tarea
		for(final Task task: this.tasks) {
			res = res && this.startExecutionPeriod.before(task.getStartExecutionPeriod())
				&& this.endExecutionPeriod.after(task.getEndExecutionPeriod());
		}
		
		return res;
	}
}

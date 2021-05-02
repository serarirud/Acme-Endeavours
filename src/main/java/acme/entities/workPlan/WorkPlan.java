package acme.entities.workPlan;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.Manager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class WorkPlan extends DomainEntity  {
	
	//Serialisation identifier
	
	protected static final long serialVersionUID = 1L;
	
	//Attributes
	
	@ManyToOne
	private Manager manager;
		
	@NotNull
	private Boolean isPublic;
	
	@NotNull
	private Boolean isPublished;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date startExecutionPeriod;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date endExecutionPeriod;
	
	
//	@NotNull
//	@Digits(integer=Integer.MAX_VALUE,fraction=2)
	@Transient
	public Double getWorkload() {
//		Double res = 0.;
//		Integer parteEntera;
//		Integer parteFraccional;
//		
//		for(final Task task: this.tasks) {
//			final Double workload = task.getWorkload();
//			parteEntera = workload.intValue(); 
//			parteFraccional = (int) ((workload - parteEntera)*100);
//			res += (parteEntera*60 + parteFraccional);
//		}
//		
//		Integer horas=0;
//		Double minutos;
//		while(res>60) {
//			res-=60;
//			horas+=1;
//		}
//		minutos = res/100;
//		res=horas+minutos;
//		return res;
		return 100.0;
			
	}
	
	public Boolean isValid() {
		final Boolean res = true;
		
		// Restriccion 1, si es privado no puede contener tasks publicas
//		if(!this.isPublic) {
//			for(final Task task: this.tasks) {
//				res = res && !task.getIsPublic();
//				if(!res) {
//					return res;
//				}
//			}
//		}
//		
//		// Restriccion 2, el tiempo de ejecución tiene que contener los tiempos de ejecucion de cada tarea
//		for(final Task task: this.tasks) {
//			res = res && this.startExecutionPeriod.before(task.getStartExecutionPeriod())
//				&& this.endExecutionPeriod.after(task.getEndExecutionPeriod());
//			if(!res) {
//				return res;
//			}
//		}
//		
		return res;
	}
}

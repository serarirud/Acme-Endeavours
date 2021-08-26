package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.Manager;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {
	
	//Serialisation identifier
	
	protected static final long serialVersionUID = 1L;
	
	//Attributes
	
	@NotBlank
	@Length(min=1, max=80)
	protected String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startExecutionPeriod;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date endExecutionPeriod;
	
	@NotNull
	@Min(0)
	@Digits(integer=2,fraction=2)
	protected Double workload;
	
	@NotBlank
	@Length(min=1, max=500)
	protected String description;
	
	@URL
	protected String link;
	
	@NotNull
	protected Boolean isPublic;
	
	// Relationships -----------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Manager manager;
	
	// Others ------------------------
	
	public Integer getMinutes() {
		final String str = String.valueOf(this.workload);
		final Integer parteEntera = Integer.parseInt(str.substring(0, str.indexOf('.')));
		final Double parteDecimal = (this.workload - parteEntera)*100;
		return (int) (parteEntera*60 + parteDecimal);
	}
	

}

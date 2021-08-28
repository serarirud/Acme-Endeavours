package acme.entities.sheet;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sheet extends DomainEntity{
	
	// Serialisation identifier ------------------------
	
	protected static final long serialVersionUID = 1L;
	
	// Attributes --------------------------------------
	
	@Pattern(regexp="([012]\\d|3[01])-\\w{2}\\d{2}-(0[1-9]|1[12])\\d{2}") //Hay que cambiar el patrón
	@Column(unique=true)
	@NotNull
	protected String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Future
	@NotNull
	protected Date deadline;
	
	@NotNull
	@Valid
	protected Money budget;
	
	@NotNull
	protected Boolean important;
	

}

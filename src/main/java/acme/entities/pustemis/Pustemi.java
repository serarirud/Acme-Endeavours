package acme.entities.pustemis;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pustemi extends DomainEntity {
	
	// Serialisation identifier ------------------------
	
	protected static final long serialVersionUID = 1L;
	
	// Attributes --------------------------------------
	
	@NotNull
	@NotEmpty
	@Column(unique = true)
	@Pattern(regexp = "^\\d{2}-(0[1-9]|1[012])\\w{2,4}-\\d{2,4}$") // ^yy-mmdd-\d{2,4}$
	private String label;
		
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date deadline;
		
	@NotNull
	@Valid
	private Money budget;
		
	@NotNull
	private Boolean important;
		
	// Derived attributes ------------------------------
		
	// Relationships -----------------------------------

}

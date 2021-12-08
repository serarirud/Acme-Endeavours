package acme.entities.info;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.entities.shouts.Shout;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Info extends DomainEntity {
	
	// Serialisation identifier ------------------------
	
	protected static final long serialVersionUID = 1L;
	
	// Attributes --------------------------------------
	
	@NotNull
	@NotEmpty
	@Column(unique = true)
	@Pattern(regexp = "^\\w{2,4}/([012]\\d|3[01])/(0[1-9]|1[012])/\\d{2}$") // ^\\w{2,4}/dd/mm/yy$
	private String pattern;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date moment;
	
	@NotNull
	@Valid
	private Money money;
	
	@NotNull
	private Boolean important;
	
	// Derived attributes ------------------------------
	
	// Relationships -----------------------------------
	@NotNull
	@Valid
	@OneToOne(optional = false)
	private Shout shout;
}
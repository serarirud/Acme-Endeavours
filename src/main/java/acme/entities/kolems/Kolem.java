package acme.entities.kolems;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Kolem extends DomainEntity {


	// Serialisation identifier ------------------------
	
	protected static final long serialVersionUID = 1L;
	
	// Attributes --------------------------------------
	
	@Column(unique=true)
	//Patrón día:([0-2][0-9]|(3)[0-1])
	//Patrón mes:(((0)[0-9])|((1)[0-2])) --> 01, 02, ...
	//Patrón año:\\d{4}
	//@Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))") -> "YYYY-MM-DD"
	//@Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$") //-> "DD/MM/YYYY"
	@Pattern(regexp = "^\\w{2,4}:\\d{2}:\\d{4}$")
	@NotBlank
	protected String ticker;
	
	//@Past, quito un milisegundo al crear para asegurar
	//Futuro -> comprobarlo al crear
	//Aquí supongo que va a ser en futuro
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date deadline;
	
	//Comprobar la moneda al crear/actualizar
	@Valid
	@NotNull
	protected Money budget;
	
	@NotNull
	protected Boolean important;
	
}

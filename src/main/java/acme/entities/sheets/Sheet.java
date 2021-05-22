package acme.entities.sheets;

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
public class Sheet extends DomainEntity {
	
	// Serialisation identifier ------------------------
	
	protected static final long serialVersionUID = 1L;
		
	// Attributes --------------------------------------
		
	//Debe adherirse a un patrón el cual tiene año, mes y día, podría ser String o Date
	
	
	//@Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))") -> "YYYY-MM-DD"
	@Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$") //-> "DD/MM/YYYY"
	@NotBlank
	@Column(unique=true)
	protected String atr1;
	
	//@Past, quitar un milisegundo antes de crear para asegurar
	//Si es futuro, añadir un milisegundo antes de crear
	//Supongo que es FUTURO
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date atr2;
	
	//EJ: solo acepta EUR o USD
	//Habría que añadir validación personaliza al crear/actualizar
	@NotNull
	@Valid
	protected Money atr3;
	
	@NotNull
	protected Boolean atr4;
	
}

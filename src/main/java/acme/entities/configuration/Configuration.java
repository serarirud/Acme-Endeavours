package acme.entities.configuration;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends DomainEntity {
	
	//Serialisation identifier
	
	protected static final long serialVersionUID = 1L;
	
	// Atributos
	
	@NotBlank
	private String spamWords;
	
	@NotNull
	@Range(min=0, max=100)
	@Digits(integer=3, fraction=2)
	private Double threshold;
}

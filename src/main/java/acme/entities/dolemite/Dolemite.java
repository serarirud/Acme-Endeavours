package acme.entities.dolemite;

import java.util.Calendar;
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
public class Dolemite extends DomainEntity{

	protected static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(unique=true)
	@Pattern(regexp = "^\\w{6}:(0[1-9]|1[012])\\d{2}:([012]\\d|3[01])$", message = "Error con el patrón, debe ser: w{6}:mmyy:dd") //w{6}:mmyy:dd
	protected String keylem;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date deadline;
	
	@NotNull
	@Valid
	protected Money budget;
	
	@NotNull
	protected Boolean important;
	
	public static String getCodeRegExp(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		String year = String.valueOf(c.get(Calendar.YEAR));

		if (day.length() == 1)
			day = "0" + day;
		if (month.length() == 1)
			month = "0" + month;
		year = year.substring(2);

		return "^\\w{6}:" + month + year + ":" + day + "$";
	}
}

package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkPlanData implements Serializable {
	
	//Serialisation identifier
	
	protected static final long serialVersionUID = 1L;
	
	//Attributes
	
	private Integer total, finalizados, noFinalizados, publicados, noPublicados;
	
	private Double mediaPeriodo, desviacionPeriodo, mediaCarga, desviacionCarga, maximoCarga, minimoCarga;
	
	private String maximoPeriodo, minimoPeriodo;
}

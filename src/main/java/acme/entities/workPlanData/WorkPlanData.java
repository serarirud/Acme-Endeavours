package acme.entities.workPlanData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkPlanData {
	
	//Serialisation identifier
	
	protected static final long serialVersionUID = 1L;
	
	//Attributes
	
	private Integer total, finalizados, noFinalizados, publicados, noPublicados, maximoPeriodo, minimoPeriodo, maximoCarga, minimoCarga;
	
	private Double mediaPeriodo, desviacionPeriodo, mediaCarga, desviacionCarga;
	
}

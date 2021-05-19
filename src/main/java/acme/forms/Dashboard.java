package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	protected static final long	serialVersionUID	= 1L;
	
	Integer 					nPublicTask;
	Integer 					nPrivateTask;
	Integer 					nFinishedTask;
	Integer 					nNotFinishedTask;
	Double						averageTaskExecutionPeriods;
	Double						deviationTaskExecutionPeriods;
	Integer						minimumTaskExecutionPeriods;
	Integer						maximumTaskExecutionPeriods;
	
	Double 						averageTaskWorkloads;
	Double 						deviationTaskWorkloads;
	Double						minimumTaskWorkloads;
	Double						maximumTaskWorkloads;
	
	//---------------------------------
	
	Double						ratio1;
	Double						ratio2;
	Double 						averageSheetsEUR;
	Double 						averageSheetsUSD;
	Double 						deviationSheetsEUR;
	Double 						deviationSheetsUSD;
	
	//---------------------------------

}

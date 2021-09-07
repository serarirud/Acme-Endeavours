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
	
	Double						ratioImportantSheet;
	Double						ratioZeroBudgetSheet;
	Double						avarageEUR;
	Double						deviationEUR;
	Double						avarageUSD;
	Double						deviationUSD;
	Double						avarageGBP;
	Double						deviationGBP;

}

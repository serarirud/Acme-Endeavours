package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	protected static final long	serialVersionUID	= 1L;
	
	Integer 					nPublicDuties;
	Integer 					nPrivateDuties;
	Integer 					nFinishedDuties;
	Integer 					nNotFinishedDuties;
	Double						averageDutiesExecutionPeriods;
	Double						deviationDutiesExecutionPeriods;
	Integer						minimumDutiesExecutionPeriods;
	Integer						maximumDutiesExecutionPeriods;
	
	Double 						averageDutiesWorkloads;
	Double 						deviationDutiesWorkloads;
	Double						minimumDutiesWorkloads;
	Double						maximumDutiesWorkloads;
	
	Double						ratioImportantInfo;
	Double						ratioZeroBudgetInfo;
	Double						avarageEUR;
	Double						deviationEUR;
	Double						avarageUSD;
	Double						deviationUSD;
	Double						avarageGBP;
	Double						deviationGBP;

}

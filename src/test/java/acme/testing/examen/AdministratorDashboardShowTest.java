package acme.testing.examen;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class AdministratorDashboardShowTest extends AcmeEndeavoursTest {
	
	/*	Feature: un usuario administrador puede ver los datos de tasks y shouts
	 * 	Caso positivo.
	*/

	@ParameterizedTest
	@CsvFileSource(resources = "/examen/dashboard/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void showPositive(final String nPrivateTask, final String nPublicTask, final String nFinishedTask, final String nNotFinishedTask, 
		final String averageTaskExecutionPeriods, final String deviationTaskExecutionPeriods, 
		final String minimumTaskExecutionPeriods, final String maximumTaskExecutionPeriods, 
		final String averageTaskWorkloads, final String deviationTaskWorkloads, 
		final String minimumTaskWorkloads, final String maximumTaskWorkloads,
		//################################ CAMBIAR ################################
		final String ratioImportantSheet, final String ratioZeroBudgetSheet, 
		//#########################################################################
		final String avarageEUR, final String deviationEUR, 
		final String avarageUSD, final String deviationUSD, 
		final String avarageGBP, final String deviationGBP) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Dashboard");
		
		super.checkErrorsAcmeMessageExist(2,1,1,ratioImportantSheet.toString());
		super.checkErrorsAcmeMessageExist(2,1,2,ratioZeroBudgetSheet.toString());
		super.checkErrorsAcmeMessageExist(2,1,3,avarageEUR.toString() + " EUR");
		super.checkErrorsAcmeMessageExist(2,1,4,deviationEUR.toString() + " EUR");
		super.checkErrorsAcmeMessageExist(2,1,5,avarageUSD.toString() + " USD");
		super.checkErrorsAcmeMessageExist(2,1,6,deviationUSD.toString() + " USD");
		super.checkErrorsAcmeMessageExist(2,1,7,avarageGBP.toString() + " GBP");
		super.checkErrorsAcmeMessageExist(2,1,8,deviationGBP.toString() + " GBP");
		
		super.checkErrorsAcmeMessageExist(2,2,1,nPrivateTask.toString());
		super.checkErrorsAcmeMessageExist(2,2,2,nPublicTask.toString());
		super.checkErrorsAcmeMessageExist(2,2,3,nFinishedTask.toString());
		super.checkErrorsAcmeMessageExist(2,2,4,nNotFinishedTask.toString());
		super.checkErrorsAcmeMessageExist(2,3,1,averageTaskExecutionPeriods.toString() + " Days");
		super.checkErrorsAcmeMessageExist(2,3,2,deviationTaskExecutionPeriods.toString() + " Days");
		super.checkErrorsAcmeMessageExist(2,3,3,minimumTaskExecutionPeriods.toString() + " Days");
		super.checkErrorsAcmeMessageExist(2,3,4,maximumTaskExecutionPeriods.toString() + " Days");
		super.checkErrorsAcmeMessageExist(2,4,1,averageTaskWorkloads.toString() + " {Hours,Minutes}");
		super.checkErrorsAcmeMessageExist(2,4,2,deviationTaskWorkloads.toString() + " {Hours,Minutes}");
		super.checkErrorsAcmeMessageExist(2,4,3,minimumTaskWorkloads.toString() + " {Hours,Minutes}");
		super.checkErrorsAcmeMessageExist(2,4,4,maximumTaskWorkloads.toString() + " {Hours,Minutes}");

		super.signOut();
	}
}
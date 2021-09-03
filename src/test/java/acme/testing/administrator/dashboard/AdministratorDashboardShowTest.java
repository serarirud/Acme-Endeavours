package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardShowTest extends AcmePlannerTest {
	
	/*	Feature: un usuario administrador puede ver los datos de tasks y shouts
	 * 	Caso positivo.
	*/

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void showPositive(final String ratioShoutsFlaggedAsImportant, final String ratioShoutsWithBudgetZero, 
		final String averageShoutsGroupedByEUR, final String deviationShoutsGroupedByEUR, 
		final String averageShoutsGroupedByUSD, final String deviationShoutsGroupedByUSD, 
		final String averageShoutsGroupedByGBP, final String deviationShoutsGroupedByGBP, 
		final String nPrivateTask, final String nPublicTask, final String nFinishedTask, final String nNotFinishedTask, 
		final String averageTaskExecutionPeriods, final String deviationTaskExecutionPeriods, 
		final String minimumTaskExecutionPeriods, final String maximumTaskExecutionPeriods, 
		final String averageTaskWorkloads, final String deviationTaskWorkloads, 
		final String minimumTaskWorkloads, final String maximumTaskWorkloads) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Dashboard");
		
		
		super.checkErrorsAcmeMessageExist(2,1,1,ratioShoutsFlaggedAsImportant.toString() + " %");
		super.checkErrorsAcmeMessageExist(2,1,2,ratioShoutsWithBudgetZero.toString() + " %");
		super.checkErrorsAcmeMessageExist(2,1,3,averageShoutsGroupedByEUR.toString() + " EUR");
		super.checkErrorsAcmeMessageExist(2,1,4,deviationShoutsGroupedByEUR.toString() + " EUR");
		super.checkErrorsAcmeMessageExist(2,1,5,averageShoutsGroupedByUSD.toString() + " USD");
		super.checkErrorsAcmeMessageExist(2,1,6,deviationShoutsGroupedByUSD.toString() + " USD");
		super.checkErrorsAcmeMessageExist(2,1,7,averageShoutsGroupedByGBP.toString() + " GBP");
		super.checkErrorsAcmeMessageExist(2,1,8,deviationShoutsGroupedByGBP.toString() + " GBP");
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

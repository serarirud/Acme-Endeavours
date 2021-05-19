package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardShowTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void showPositive(final String nPrivateTask, final String nPublicTask, final String nFinishedTask, final String nNotFinishedTask, 
		final String averageTaskExecutionPeriods, final String deviationTaskExecutionPeriods, 
		final String minimumTaskExecutionPeriods, final String maximumTaskExecutionPeriods, 
		final String averageTaskWorkloads, final String deviationTaskWorkloads, 
		final String minimumTaskWorkloads, final String maximumTaskWorkloads) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Dashboard");
		
		super.checkErrorsAcmeMessageExist(2,1,1,nPrivateTask.toString());
		super.checkErrorsAcmeMessageExist(2,1,2,nPublicTask.toString());
		super.checkErrorsAcmeMessageExist(2,1,3,nFinishedTask.toString());
		super.checkErrorsAcmeMessageExist(2,1,4,nNotFinishedTask.toString());
		super.checkErrorsAcmeMessageExist(2,2,1,averageTaskExecutionPeriods.toString() + " Days");
		super.checkErrorsAcmeMessageExist(2,2,2,deviationTaskExecutionPeriods.toString() + " Days");
		super.checkErrorsAcmeMessageExist(2,2,3,minimumTaskExecutionPeriods.toString() + " Days");
		super.checkErrorsAcmeMessageExist(2,2,4,maximumTaskExecutionPeriods.toString() + " Days");
		super.checkErrorsAcmeMessageExist(2,3,1,averageTaskWorkloads.toString() + " {Hours,Minutes}");
		super.checkErrorsAcmeMessageExist(2,3,2,deviationTaskWorkloads.toString() + " {Hours,Minutes}");
		super.checkErrorsAcmeMessageExist(2,3,3,minimumTaskWorkloads.toString() + " {Hours,Minutes}");
		super.checkErrorsAcmeMessageExist(2,3,4,maximumTaskWorkloads.toString() + " {Hours,Minutes}");

		super.signOut();
	}
}

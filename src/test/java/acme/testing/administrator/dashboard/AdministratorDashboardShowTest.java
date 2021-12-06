package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class AdministratorDashboardShowTest extends AcmeEndeavoursTest {
	
	/*	Feature: un usuario administrador puede ver los datos de tasks y shouts
	 * 	Caso positivo.
	*/

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void showPositive(final String nPrivateDuties, final String nPublicDuties, final String nFinishedDuties, final String nNotFinishedDuties, 
		final String averageDutiesExecutionPeriods, final String deviationDutiesExecutionPeriods, 
		final String minimumDutiesExecutionPeriods, final String maximumDutiesExecutionPeriods, 
		final String averageDutiesWorkloads, final String deviationDutiesWorkloads, 
		final String minimumDutiesWorkloads, final String maximumDutiesWorkloads) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Dashboard");
		
		super.checkErrorsAcmeMessageExist(2,1,1,nPrivateDuties.toString());
		super.checkErrorsAcmeMessageExist(2,1,2,nPublicDuties.toString());
		super.checkErrorsAcmeMessageExist(2,1,3,nFinishedDuties.toString());
		super.checkErrorsAcmeMessageExist(2,1,4,nNotFinishedDuties.toString());
		super.checkErrorsAcmeMessageExist(2,2,1,averageDutiesExecutionPeriods.toString() + " Days");
		super.checkErrorsAcmeMessageExist(2,2,2,deviationDutiesExecutionPeriods.toString() + " Days");
		super.checkErrorsAcmeMessageExist(2,2,3,minimumDutiesExecutionPeriods.toString() + " Days");
		super.checkErrorsAcmeMessageExist(2,2,4,maximumDutiesExecutionPeriods.toString() + " Days");
		super.checkErrorsAcmeMessageExist(2,3,1,averageDutiesWorkloads.toString() + " {Hours,Minutes}");
		super.checkErrorsAcmeMessageExist(2,3,2,deviationDutiesWorkloads.toString() + " {Hours,Minutes}");
		super.checkErrorsAcmeMessageExist(2,3,3,minimumDutiesWorkloads.toString() + " {Hours,Minutes}");
		super.checkErrorsAcmeMessageExist(2,3,4,maximumDutiesWorkloads.toString() + " {Hours,Minutes}");

		super.signOut();
	}
}

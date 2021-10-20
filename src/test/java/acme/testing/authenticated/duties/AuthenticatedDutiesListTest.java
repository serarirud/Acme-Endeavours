package acme.testing.authenticated.duties;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class AuthenticatedDutiesListTest extends AcmeEndeavoursTest {
	
	
	/*	Feature: un usuario autenticado puede listar las tareas públicas finalizadas y ver detalles de estas
	*	Caso positivo
	*/
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/duties/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void listPositive(final int recordIndex, final String title, final String startExecutionPeriod, final String endExecutionPeriod, final String workload, final String description, final String link, final String isPublic) {		
		super.signIn("officer1", "officer1");
		
		super.clickOnMenu("Authenticated", "Public finished duties list");		
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startExecutionPeriod);
		super.checkColumnHasValue(recordIndex, 2, endExecutionPeriod);
		super.checkColumnHasValue(recordIndex, 3, workload);

		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startExecutionPeriod", startExecutionPeriod);		
		super.checkInputBoxHasValue("endExecutionPeriod", endExecutionPeriod);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("isPublic", isPublic);
		
		super.signOut();
	}
	
	

}



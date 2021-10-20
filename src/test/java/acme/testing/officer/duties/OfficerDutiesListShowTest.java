package acme.testing.officer.duties;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class OfficerDutiesListShowTest extends AcmePlannerTest{

	/*	Feature: un usuario manager puede listar sus propias tareas y ver detalles de estas
	*	Caso positivo
	*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duties/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void listAndShowPositive(final int recordIndex, final String title, final String startExecutionPeriod, final String endExecutionPeriod, final String workload, final String description, final String link, final String isPublic) {
		
		super.signIn("officer", "officer");
		
		super.clickOnMenu("Officer", "My duties list");
		
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
	
	/*	Feature: un usuario manager puede observar detalles de sus propias tareas.
	 * En el test anterior se comprueban los detalles en el caso positivo. Ahora se comprueba que un usuario anonimo no puede
	 * observar detalles de tareas de un manager.
	*	Caso negativo
	*/


	@Test
	@Order(20)	
	public void listNegative() {
				
		super.navigate("/officer/duties/list", "");
		super.checkErrorsExist();
	}
}

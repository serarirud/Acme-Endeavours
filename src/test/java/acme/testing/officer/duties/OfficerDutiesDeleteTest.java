package acme.testing.officer.duties;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class OfficerDutiesDeleteTest extends AcmeEndeavoursTest{

	/*
	 * Feature: un usuario manager puede borrar sus tareas
	 * Caso positivo
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duties/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void delete(final int recordIndex, final String title, final String startExecutionPeriod, final String endExecutionPeriod, final String workload, final String description, final String link, final String isPublic) {
		
		super.signIn("officer", "officer");
		
		super.clickOnMenu("Officer", "My duties list");
		
		super.clickOnListingRecord(0); //Para evitar conflictos de rango, siempre va a borrar la tarea 
									   //que tenga el recordIndex=0 pues cuando se borra una, las tareas
									   //se quedan con recordIndex-1
		
		super.clickOnSubmitButton("Delete");
		super.isSimplePath("/officer/duties/list");

		super.signOut();
		
	}
	
	
}

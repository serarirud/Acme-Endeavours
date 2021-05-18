package acme.testing.manager.tasks;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteTest extends AcmePlannerTest{

	/*
	 * Feature: un usuario manager puede borrar sus tareas
	 * Caso positivo
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void delete(final int recordIndex, final String title, final String startExecutionPeriod, final String endExecutionPeriod, final String workload, final String description, final String link, final String isPublic) {
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "Task list");
		
		super.clickOnListingRecord(0); //Para evitar conflictos de rango, siempre va a borrar la tarea 
									   //que tenga el recordIndex=0 pues cuando se borra una, las tareas
									   //se quedan con recordIndex-1
		
		super.clickOnSubmitButton("Delete");
		super.isSimplePath("/manager/task/list");

		super.signOut();
		
	}
	
	
}

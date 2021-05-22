package acme.testing.manager.tasks;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskListShowTest extends AcmePlannerTest{

	/*	Feature: un usuario manager puede listar sus propias tareas y ver detalles de estas
	*	Caso positivo
	*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void listAndShowPositive(final int recordIndex, final String title, final String startExecutionPeriod, final String endExecutionPeriod, final String workload, final String description, final String link, final String isPublic) {
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "My task list");
		
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
	 * En el test anterior se comprueban los detalles en el caso positivo. Ahora se comprueba que no se pueden
	 * observar detalles de tareas de otro manager. La tarea con id=21 no pertenece a manager1.
	*	Caso negativo
	*/


	@Test
	@Order(20)	
	public void listNegative() {
		
		super.signIn("manager1", "manager1");
				
		super.driver.get("http://localhost:8080/Acme-Planner/manager/task/show?id=21");
		super.checkErrorsExist();
		super.signOut();
	}
}

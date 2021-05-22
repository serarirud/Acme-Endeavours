package acme.testing.manager.tasks;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.helpers.StringHelper;
import acme.testing.AcmePlannerTest;

public class ManagerTaskUpdateTest extends AcmePlannerTest{

	/*	Feature: un usuario manager puede editar sus propias tareas
	*	Caso positivo
	*	
	*	Se actualizan todos los campos de todos las tareas de un manager.
	*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String title, final String startExecutionPeriod, final String endExecutionPeriod, final String workload, final String description, final String link, final String isPublic) {
		
		assert !StringHelper.isBlank(title);
		assert !StringHelper.isBlank(startExecutionPeriod);
		assert !StringHelper.isBlank(endExecutionPeriod);
		assert !StringHelper.isBlank(workload);
		assert !StringHelper.isBlank(description);
		assert !StringHelper.isBlank(isPublic);
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "My task list");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startExecutionPeriod", startExecutionPeriod);
		super.fillInputBoxIn("endExecutionPeriod", endExecutionPeriod);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		if(isPublic.equals("true")) {
			super.fillInputBoxIn("isPublic", "true");
		}else {
			super.fillInputBoxIn("isPublic", "false");
		}
		super.clickOnSubmitButton("Update");
		
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
	
	/*	Feature: un usuario manager puede actualizar sus tareas
	*	Caso negativo: se comprueba un error por cada tarea en el csv.
	*		Primera tarea: tarea sin título
	*		Segunda tarea: tarea sin startExecutionPeriod
	*		Tercera tarea: tarea sin endExecutionPeriod 
	*		Cuarta tarea: tarea sin workload
	*		Quinta tarea: startExecutionPeriod > endExecutionPeriod
	*		Sexta tarea: tarea sin descripcion
	*		Séptima tarea: demasiado workload para las fechas dadas a startExecutionPeriod y endExecutionPeriod
	*		Octava tarea: filtrado por spam
	*		Novena tarea: parte decimal workload >0.6
	*		Décima tarea: tarea con titulo más largo de la cuenta
	*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/updateNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updateNegative(final int recordIndex, final String title, final String startExecutionPeriod, final String endExecutionPeriod, final String workload, final String description, final String link, final String isPublic) {
		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "My task list");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startExecutionPeriod", startExecutionPeriod);
		super.fillInputBoxIn("endExecutionPeriod", endExecutionPeriod);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		if(isPublic.equals("true")) {
			super.fillInputBoxIn("isPublic", "true");
		}else {
			super.fillInputBoxIn("isPublic", "false");
		}
		
		super.clickOnSubmitButton("Update");
		
		super.checkSimplePath("/manager/task/update");
		
		super.signOut();
	}
}

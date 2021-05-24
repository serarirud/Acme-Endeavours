package acme.testing.manager.tasks;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.helpers.StringHelper;
import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTest extends AcmePlannerTest{

	/*	Feature: un usuario manager puede crear una nueva tarea
	*	Caso positivo
	*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void createPositive(final int recordIndex, final String title, final String startExecutionPeriod, final String endExecutionPeriod, final String workload, final String description, final String link, final String isPublic) {
	
		assert !StringHelper.isBlank(title);
		assert !StringHelper.isBlank(startExecutionPeriod);
		assert !StringHelper.isBlank(endExecutionPeriod);
		assert !StringHelper.isBlank(workload);
		assert !StringHelper.isBlank(description);
		assert !StringHelper.isBlank(isPublic);

		
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "Create a task");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startExecutionPeriod", startExecutionPeriod);
		super.fillInputBoxIn("endExecutionPeriod", endExecutionPeriod);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("isPublic", isPublic);

		super.clickOnSubmitButton("Create");
		
		super.checkSimplePath("/master/welcome");
		super.clickOnMenu("Manager", "My task list");
		
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
	
	
	/*	Feature: un usuario manager puede crear una nueva tarea
	*	Caso negativo: se comprueba un error por cada tarea en el csv.
	*		Primera tarea: startExecutionPeriod > endExecutionPeriod
	*		Segunda tarea: startExecutionPeriod < día actual
	*		Tercera tarea: parte decimal workload >0.6
	*		Cuarta tarea: demasiado workload para las fechas dadas a startExecutionPeriod y endExecutionPeriod
	*		Quinta tarea: filtrado por spam
	*		Sexta tarea: tarea sin título
	*		Séptima tarea: tarea sin startExecutionPeriod
	*		Octava tarea: tarea sin workload
	*		Novena tarea: tarea sin endExecutionPeriod
	*		Décima tarea: tarea sin descripcion
	*		Undécima tarea: tarea con workload negativo
	*		Duodécima tarea: tarea vacía
	*		Decimo tercera tarea: tarea con url inválida
	*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void createNegative(final int recordIndex, final String title, final String startExecutionPeriod, final String endExecutionPeriod, final String workload, final String description, final String link, final String isPublic) {
	
		super.signIn("manager", "manager");
		
		super.clickOnMenu("Manager", "Create a task");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startExecutionPeriod", startExecutionPeriod);
		super.fillInputBoxIn("endExecutionPeriod", endExecutionPeriod);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("isPublic", isPublic);

		super.clickOnSubmitButton("Create");
		
		super.checkSimplePath("/manager/task/create");
		super.checkErrorsExist();
		
		super.signOut();
		
	}
}

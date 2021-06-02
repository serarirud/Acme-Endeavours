package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerCreateTest extends AcmePlannerTest {
	
	
	/*	Feature: un usuario autenticado puede convertirse en mánager si no lo es
	 * 	Caso positivo.
	*/
	
	
	@Test
	@Order(20)	
	public void create() {		
		super.navigateHome();
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Account", "Become a manager");
		super.clickOnSubmitButton("Become a manager");
		 
		super.checkSimplePath("/master/welcome");
		super.clickOnMenu("Manager", "My task list");
		super.checkSimplePath("/manager/task/list");
		
	}
	
	/*	Feature: un usuario anónimo no puede convertirse en mánager
	 * 	Caso negativo.
	 * Se comprueba intentando entrar a la URL de creación de manager sin estar autenticado.
	*/
	
	@Test
	@Order(20)	
	public void createNegative() {	
		super.navigate("/authenticated/manager/create", "");
		super.checkErrorsExist();
		
	}
	
	

}

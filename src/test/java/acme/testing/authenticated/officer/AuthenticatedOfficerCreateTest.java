package acme.testing.authenticated.officer;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.AcmeEndeavoursTest;

public class AuthenticatedOfficerCreateTest extends AcmeEndeavoursTest {
	
	
	/*	Feature: un usuario autenticado puede convertirse en mánager si no lo es
	 * 	Caso positivo.
	*/
	
	
	@Test
	@Order(20)	
	public void create() {		
		super.navigateHome();
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Account", "Become an officer");
		super.clickOnSubmitButton("Become an officer");
		 
		super.checkSimplePath("/master/welcome");
		super.clickOnMenu("Officer", "My duties list");
		super.checkSimplePath("/officer/duties/list");
		
	}
	
	/*	Feature: un usuario anónimo no puede convertirse en mánager
	 * 	Caso negativo.
	 * Se comprueba intentando entrar a la URL de creación de manager sin estar autenticado.
	*/
	
	@Test
	@Order(20)	
	public void createNegative() {	
		super.navigate("/authenticated/officer/create", "");
		super.checkErrorsExist();
		
	}
	
	

}

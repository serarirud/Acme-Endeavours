package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AdministratorConfigurationShow2Test extends AcmePlannerTest {
	
	/*	Feature: un usuario administrador puede ver los parámetros de configuración de la
	 * 	aplicación (palabras spam, umbral de spam).
	 * 	Caso positivo.
	*/
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)	
	public void showPositive(final String spamWords, final String threshold) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Show configuration");		
				
		super.checkInputBoxHasValue("spamWords", spamWords);
		super.checkInputBoxHasValue("threshold", threshold);		
		
		super.signOut();
	}
	

}
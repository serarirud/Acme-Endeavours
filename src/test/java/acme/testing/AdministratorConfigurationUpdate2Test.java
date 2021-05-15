package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


public class AdministratorConfigurationUpdate2Test extends AcmePlannerTest {
	
	/*	Feature: un usuario administrador puede modificar los parámetros de configuración de la
	 * 	aplicación (palabras spam, umbral de spam).
	 * 	Caso positivo.
	*/
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)	
	public void updatePositive(final String spamWords, final String threshold) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Show configuration");		
				
		super.clickOnReturnButton("Edit");
		
		super.fillInputBoxIn("spamWords", spamWords);
		super.fillInputBoxIn("threshold", threshold);
		super.clickOnSubmitButton("Update");

		super.clickOnMenu("Administrator", "Show configuration");		

		super.checkInputBoxHasValue("spamWords", spamWords.toLowerCase());
		super.checkInputBoxHasValue("threshold", threshold);	
		
		super.signOut();
	}
	
	
	/*	Feature: un usuario administrador puede modificar los parámetros de configuración de la
	 * 	aplicación (palabras spam, umbral de spam).
	 * 	Caso negativo.
	*/
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)	
	public void updateNegative(final String spamWords, final String threshold) {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Show configuration");		
				
		super.clickOnReturnButton("Edit");
	
		super.fillInputBoxIn("spamWords", spamWords);
		super.fillInputBoxIn("threshold", threshold);
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist("spamWords");
		super.checkErrorsExist("threshold");

		super.signOut();
	}


}


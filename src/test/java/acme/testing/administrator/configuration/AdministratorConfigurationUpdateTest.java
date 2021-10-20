package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class AdministratorConfigurationUpdateTest extends AcmeEndeavoursTest {
	
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
	 * 		Primer update: spamWords es null y el threshold negativo.
	 * 	 	Segundo update: spamWords es null y el threshold positivo mayor que 100%.
	 * 		Tercer update: spamWords es null y el threshold tiene tres decimales.
	 * 		Cuarto update: spamWords es null y el threshold es null.
	 * 		Quinto update: spamWords es null y el threshold es un número negativo muy grande y con cuatro decimales.
	 * 		Sexto update: spamWords es null y el threshold es un positivo muy grande, superior a 100% y con cuatro decimales.
	 * 
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

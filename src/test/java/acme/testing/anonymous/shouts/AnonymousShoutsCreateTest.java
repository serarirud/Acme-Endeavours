package acme.testing.anonymous.shouts;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.helpers.StringHelper;
import acme.testing.AcmeEndeavoursTest;

public class AnonymousShoutsCreateTest extends AcmeEndeavoursTest {
	
	/*	Feature: un usuario anónimo puede crear gritos
	 * 	Caso positivo.
	*/
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void create(final int recordIndex, final String author, final String text, final String info) {		
		assert !StringHelper.isBlank(author);
		assert !StringHelper.isBlank(text);
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		 
		super.checkSimplePath("/master/welcome");
		
		super.clickOnMenu("Anonymous", "Shout list");	
				
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		
	}
	
	/*	Feature: un usuario anónimo puede crear gritos
	 * 	Caso negativo.
	 * 		Primer update: autor y texto vacío. Información mala URL.
	 * 		Segundo update: autor superando el máximo de caracteres, texto con umbral de spam superado y mala URL.
	 * 		Tercer update: autor y texto menor del mínimo de caracteres, y mala URL.
	 * 		Cuarto update: autor vacío, texto superando el máximo de caracteres y mala URL.
	 * 		Quinto update: autor vacío, texto con umbral de spam superado y mala URL.
	*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void createNegative(final String author, final String text, final String info) {
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("author");
		super.checkErrorsTextAreaExist("text");
		super.checkErrorsExist("info");
		
		super.checkSimplePath("/anonymous/shout/create");
		
	}
	
	

}

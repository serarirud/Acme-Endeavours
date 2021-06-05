package acme.testing.anonymous.shouts;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.helpers.StringHelper;
import acme.testing.AcmePlannerTest;

public class AnonymousShoutsCreateTest extends AcmePlannerTest {
	
	/*	Feature: un usuario anónimo puede crear gritos
	 * 	Caso positivo.
	*/
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void create(final int recordIndex, final String author, final String text, final String info,
		final String atr1, final String atr2, final String atr3, final String atr4) {		
		assert !StringHelper.isBlank(author);
		assert !StringHelper.isBlank(text);
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("sheet.atr1", atr1);
		super.fillInputBoxIn("sheet.atr2", atr2);
		super.fillInputBoxIn("sheet.atr3", atr3);
		super.fillInputBoxIn("sheet.atr4", atr4);
		
		super.clickOnSubmitButton("Shout!");
		 
		super.checkSimplePath("/master/welcome");
		
		super.clickOnMenu("Anonymous", "Shout list");	
				
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		super.checkColumnHasValue(recordIndex, 4, atr1);
		super.checkColumnHasValue(recordIndex, 5, atr2);
		super.checkColumnHasValue(recordIndex, 6, atr3);
		super.checkColumnHasValue(recordIndex, 7, atr4);
		
	}
	
	/*	Feature: un usuario anónimo puede crear gritos
	 * 	Caso negativo.
	 * 		Primer: autor y texto vacío. Información mala URL. Resto vacío
	 * 		Segundo: autor superando el máximo de caracteres, texto con umbral de spam superado y mala URL.
	 * 		Tercer: autor y texto menor del mínimo de caracteres, y mala URL.
	 * 		Cuarto: autor vacío, texto superando el máximo de caracteres y mala URL.
	 * 		Quinto: autor vacío, texto con umbral de spam superado y mala URL.
	 * 		Sexto: atr1, patrón incorrecto
	 * 		Séptimo: atr1, valor que ya existe
	 * 		Octavo: atr2, momento en pasado
	 * 		Noveno: atr3, dinero negativo
	 * 		Décimo: atr3, tipo de moneda no válida
	 * 
	*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void createNegative(final String author, final String text, final String info,
		final String atr1, final String atr2, final String atr3, final String atr4) {
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("sheet.atr1", atr1);
		super.fillInputBoxIn("sheet.atr2", atr2);
		super.fillInputBoxIn("sheet.atr3", atr3);
		super.fillInputBoxIn("sheet.atr4", atr4);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
		super.checkSimplePath("/anonymous/shout/create");
		
	}
	
	

}

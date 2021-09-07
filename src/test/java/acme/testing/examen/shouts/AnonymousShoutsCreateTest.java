package acme.testing.examen.shouts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	@CsvFileSource(resources = "/examen/shout/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void create(final int recordIndex, final String author, final String text, final String info
					, String pattern, String moment, final String money, final String important) {
		assert !StringHelper.isBlank(author);
		assert !StringHelper.isBlank(text);
		
		assert !StringHelper.isBlank(pattern);
		assert !StringHelper.isBlank(money);
		assert !StringHelper.isBlank(important);
		
		// Generando el patrón y el momento de Sheet a partir del dia de hoy
		final LocalDateTime today = LocalDateTime.now();
		pattern = pattern + today.format(DateTimeFormatter.ofPattern("/dd/MM/yy"));
		moment = today.plusDays(7L).plusMinutes(1L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("sheet.pattern", pattern);
		super.fillInputBoxIn("sheet.moment", moment);
		super.fillInputBoxIn("sheet.money", money);
		super.fillInputBoxIn("sheet.important", important);
		super.clickOnSubmitButton("Shout!");
		 
		super.checkSimplePath("/master/welcome");
		
		super.clickOnMenu("Anonymous", "Shout list");	
				
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		super.checkColumnHasValue(recordIndex, 4, pattern);
		super.checkColumnHasValue(recordIndex, 5, moment);
		super.checkColumnHasValue(recordIndex, 6, money);
		super.checkColumnHasValue(recordIndex, 7, important);
		
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
	@CsvFileSource(resources = "/examen/shout/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
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

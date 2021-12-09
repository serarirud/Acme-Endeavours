package acme.testing.examen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		//################################ CAMBIAR ################################
		final LocalDateTime today = LocalDateTime.now();
		pattern = today.format(DateTimeFormatter.ofPattern("yyMMdd")) + pattern;
		//#########################################################################
		moment = today.plusDays(7L).plusMinutes(1L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		//################################ CAMBIAR ################################
		super.fillInputBoxIn("shout.author", author);
		super.fillInputBoxIn("shout.text", text);
		super.fillInputBoxIn("shout.info", info);
		super.fillInputBoxIn("pattern", pattern);
		super.fillInputBoxIn("moment", moment);
		super.fillInputBoxIn("money", money);
		super.fillInputBoxIn("important", important);
		//#########################################################################
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
	*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/examen/shout/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void createNegative(final String author, final String text, final String info
		, final String pattern, final String moment, final String money) {
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		//################################ CAMBIAR ################################
		super.fillInputBoxIn("shout.author", author);
		super.fillInputBoxIn("shout.text", text);
		super.fillInputBoxIn("shout.info", info);
		super.fillInputBoxIn("pattern", pattern);
		super.fillInputBoxIn("moment", moment);
		super.fillInputBoxIn("money", money);
		super.clickOnSubmitButton("Shout!");
	
		super.checkErrorsExist("shout.author");
		super.checkErrorsTextAreaExist("shout.text");
		super.checkErrorsExist("shout.info");
		super.checkErrorsExist("pattern");
		super.checkErrorsExist("moment");
		super.checkErrorsExist("money");
		
		super.checkSimplePath("/anonymous/info/create");
		//#########################################################################
		
	}
	
	

}

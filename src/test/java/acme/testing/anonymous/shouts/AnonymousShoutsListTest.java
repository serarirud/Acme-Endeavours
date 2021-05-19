package acme.testing.anonymous.shouts;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutsListTest extends AcmePlannerTest {
	
	/*	Feature: un usuario anonimo puede ver los gritos de otros usuarios anonimos
	 * 	Caso positivo.
	*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void list(final int recordIndex, final String moment, final String author, final String text, final String info,
		final String atr1, final String atr2, final String atr3, final String atr4) {		
		
		super.clickOnMenu("Anonymous", "Shout list");		
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		super.checkColumnHasValue(recordIndex, 4, atr1);
		super.checkColumnHasValue(recordIndex, 5, atr2);
		super.checkColumnHasValue(recordIndex, 6, atr3);
		super.checkColumnHasValue(recordIndex, 7, atr4);

	}
	
	

}

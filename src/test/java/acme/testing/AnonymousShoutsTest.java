package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.helpers.StringHelper;

public class AnonymousShoutsTest extends AcmePlannerTest {
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void list(final int recordIndex, final String moment, final String author, final String text, final String info) {		
		
		super.clickOnMenu("Anonymous", "Shout list");		
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void create(final String author, final String text, final String info) {		
		assert !StringHelper.isBlank(author);
		assert !StringHelper.isBlank(text);
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		super.checkSimplePath("/master/welcome");
		
	}
	
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
		super.checkSimplePath("/anonymous/shout/create");
		
	}
	
	

}

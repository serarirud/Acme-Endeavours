package acme.testing.examen;

import java.time.LocalDate;
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
	public void create(final int recordIndex, final String author, final String text, final String info, 
						String label, String deadline, final String budget, final String important) {		
		
		assert !StringHelper.isBlank(author);
		assert !StringHelper.isBlank(text);
		assert !StringHelper.isBlank(label);
		assert StringHelper.isBlank(deadline);
		assert !StringHelper.isBlank(budget);
		
		label = LocalDate.now().format(DateTimeFormatter.ofPattern("yy-MMdd")) + label;
		deadline = LocalDateTime.now().plusDays(8).format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm"));
				
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("pustemi.label", label);
		super.fillInputBoxIn("pustemi.deadline", deadline);
		super.fillInputBoxIn("pustemi.budget", budget);
		super.fillInputBoxIn("pustemi.important", important);

		super.clickOnSubmitButton("Shout!");
		 
		super.checkSimplePath("/master/welcome");
		
		super.clickOnMenu("Anonymous", "Shout list");	
				
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		super.checkColumnHasValue(recordIndex, 4, label);
		super.checkColumnHasValue(recordIndex, 5, deadline);
		super.checkColumnHasValue(recordIndex, 6, budget);
		super.checkColumnHasValue(recordIndex, 7, important);

		
	}
	
	/*	Feature: un usuario anónimo puede crear gritos
	 * 	Caso negativo.
	 * 		Primer update: autor y texto vacío. Información mala URL. label, deadline y budget vacíos.
	 * 		Segundo update: autor superando el máximo de caracteres, texto con umbral de spam superado y mala URL. label incorrecto, 
	 * 		deadline vacío y budget con cantidad negativa.
	 * 		Tercer update: autor y texto menor del mínimo de caracteres, y mala URL. label vacío, deadline en el pasado y budget con moneda incorrecta.
	 * 		Cuarto update: autor vacío, texto superando el máximo de caracteres y mala URL. label con patrón correcto pero fecha en pasado, deadline inválido y
	 * 		budget vacío. 
	 * 		Quinto update: autor vacío, texto con umbral de spam superado y mala URL. label, deadline y budget vacíos.
	*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/examen/shout/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void createNegative(final String author, final String text, final String info, 
							final String label, final String deadline, final String budget, final String important) {
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("pustemi.label", label);
		super.fillInputBoxIn("pustemi.deadline", deadline);
		super.fillInputBoxIn("pustemi.budget", budget);
		super.fillInputBoxIn("pustemi.important", important);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("author");
		super.checkErrorsTextAreaExist("text");
		super.checkErrorsExist("info");
		super.checkErrorsExist("pustemi.label");
		super.checkErrorsExist("pustemi.deadline");
		super.checkErrorsExist("pustemi.budget");
		
		super.checkSimplePath("/anonymous/shout/create");
		
	}
	
	

}

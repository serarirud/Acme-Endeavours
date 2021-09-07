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
						String xxx1, String xxx2, final String xxx3, final String xxx4) {		
		
		assert !StringHelper.isBlank(author);
		assert !StringHelper.isBlank(text);
		assert !StringHelper.isBlank(xxx1);
		assert StringHelper.isBlank(xxx2);
		assert !StringHelper.isBlank(xxx3);

		xxx1 = xxx1 + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
		xxx2 = LocalDateTime.now().plusDays(8).format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm"));
				
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("xxx.xxx1", xxx1);
		super.fillInputBoxIn("xxx.xxx2", xxx2);
		super.fillInputBoxIn("xxx.xxx3", xxx3);
		super.fillInputBoxIn("xxx.xxx4", xxx4);

		super.clickOnSubmitButton("Shout!");
		 
		super.checkSimplePath("/master/welcome");
		
		super.clickOnMenu("Anonymous", "Shout list");	
				
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		super.checkColumnHasValue(recordIndex, 4, xxx1);
		super.checkColumnHasValue(recordIndex, 5, xxx2);
		super.checkColumnHasValue(recordIndex, 6, xxx3);
		super.checkColumnHasValue(recordIndex, 7, xxx4);

		
	}
	
	/*	Feature: un usuario anónimo puede crear gritos
	 * 	Caso negativo.
	 * 		Primer update: autor y texto vacío. Información mala URL. xxx1, xxx2 y xxx3 vacíos.
	 * 		Segundo update: autor superando el máximo de caracteres, texto con umbral de spam superado y mala URL. xxx1 incorrecto, 
	 * 		xxx2 vacío y xxx3 con cantidad negativa.
	 * 		Tercer update: autor y texto menor del mínimo de caracteres, y mala URL. xxx1 vacío, xxx2 en el pasado y xxx3 con moneda incorrecta.
	 * 		Cuarto update: autor vacío, texto superando el máximo de caracteres y mala URL. xxx1 con patrón correcto pero fecha en pasado, xxx2 inválido y
	 * 		xxx3 vacío. 
	 * 		Quinto update: autor vacío, texto con umbral de spam superado y mala URL. xxx1, xxx2 y xxx3 vacíos.
	*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/examen/shout/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void createNegative(final String author, final String text, final String info, 
							final String xxx1, final String xxx2, final String xxx3, final String xxx4) {
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("xxx.xxx1", xxx1);
		super.fillInputBoxIn("xxx.xxx2", xxx2);
		super.fillInputBoxIn("xxx.xxx3", xxx3);
		super.fillInputBoxIn("xxx.xxx4", xxx4);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("author");
		super.checkErrorsTextAreaExist("text");
		super.checkErrorsExist("info");
		super.checkErrorsExist("xxx.xxx1");
		super.checkErrorsExist("xxx.xxx2");
		super.checkErrorsExist("xxx.xxx3");
		
		super.checkSimplePath("/anonymous/shout/create");
		
	}
	
	

}

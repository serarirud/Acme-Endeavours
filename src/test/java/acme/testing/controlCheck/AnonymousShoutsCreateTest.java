package acme.testing.controlCheck;

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
	@Order(10)	
	public void create(final int recordIndex, final String author, final String text, final String info,
		final String atributo1, final String atributo2, final String atributo3, final String atributo4) {
		
		assert !StringHelper.isBlank(author);
		assert !StringHelper.isBlank(text);
		assert !StringHelper.isBlank(atributo3);
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("sheet.name", atributo1);
		super.fillInputBoxIn("sheet.deadline", atributo2);
		super.fillInputBoxIn("sheet.budget", atributo3);
		super.fillInputBoxIn("sheet.important", atributo4);
		super.clickOnSubmitButton("Shout!");
		 
		super.checkSimplePath("/master/welcome");
		
		super.clickOnMenu("Anonymous", "Shout list");	
				
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		super.checkColumnHasValue(recordIndex, 4, atributo1);
		super.checkColumnHasValue(recordIndex, 5, atributo2);
		super.checkColumnHasValue(recordIndex, 6, atributo3);
		super.checkColumnHasValue(recordIndex, 7, atributo4);
		
	}
	
	/*	Feature: un usuario anónimo puede crear gritos
	 * 	Caso negativo.
	 * 		Primer caso: Autor demasiado corto
	 * 		Segundo cas: Autor demasiado largo (26 letras)
	 * 		Tercer caso: Autor vacío
	 * 		Cuarto caso: Texto muy corto (4 caracteres)
	 * 		Quinto caso: Texto muy largo (101 caracteres)
	 * 		Sexto caso: Texto vacío
	 * 		Séptimo caso: Autor y texto vacío
	 * 		Octavo caso: Mala info (bad URL)
	 * 		Noveno caso: Mal día en ID
	 * 		Décimo caso: Mal mes en ID
	 * 		Undécimo caso: Mal año en ID
	 * 		Duodécimo caso: Mal patrón de letras/números
	 * 		Decimotercer caso: ID vacío
	 * 		Decimocuarto caso: Autor, texto e ID vacíos
	 * 		Decimoquinto caso: Deadline un día antes de la semana
	 * 		Decimosexto caso: Deadline en el pasado
	 * 		Decimoséptimo caso: Mal formato fecha
	 * 		Decimoctavo caso: Mal formato minutos
	 * 		Decimonoveno caso: Mal formato horas
	 * 		Vigésimo caso: Moneda no admitida
	 * 		Vigesimoprimer caso: No existe el entero
	 * 		Vigesimosegundo caso: Mal uso de los signos de puntuación
	 * 		Vigesimotercer caso: Sin deadline
	 * 		Vigesimocuarto caso: Sin cantidad monetaria
	 * 		Vigesimoquinto caso: Mala parte decimal en la cantidad monetaria
	 * 		Vigesimosexto caso: Supera el máximo permitido
	 * 		Vigesimoséptimp caso: Ni cantidad ni moneda
	 * 		Vigesimoctavo caso: Sin autor, texto, ID y deadline
	 * 		Vigesimonoveno caso: Letras en vez de cantidad monetaria
	 * 		Trigésimo caso: Sin autor, texto, ID, deadline y dinero
	*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void createNegative(final String author, final String text, final String info,
		final String atributo1, final String atributo2, final String atributo3, final String atributo4) {
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Create a shout");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("sheet.name", atributo1);
		super.fillInputBoxIn("sheet.deadline", atributo2);
		super.fillInputBoxIn("sheet.budget", atributo3);
		super.fillInputBoxIn("sheet.important", atributo4);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
		super.checkSimplePath("/anonymous/shout/create");
		
	}
	
	

}

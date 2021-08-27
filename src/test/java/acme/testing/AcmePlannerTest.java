package acme.testing;

import org.hibernate.internal.util.StringHelper;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class AcmePlannerTest extends AcmeTest {

	// Internal state ---------------------------------------------------------

	// Lifecycle management ---------------------------------------------------

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8080", "/Acme-Work-Plan", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(false);
		
		this.navigateHome();
		this.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Populate DB (samples)");
		super.checkAlertExists(true);		
		this.signOut();
	}

	// Business methods -------------------------------------------------------
	
	protected void signIn(final String username, final String password) {
		assert !StringHelper.isBlank(username);
		assert !StringHelper.isBlank(password);
		
		super.navigateHome();
		super.clickOnMenu("Sign in", null);
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("remember", "true");
		super.clickOnSubmitButton("Sign in");
		super.checkSimplePath("/master/welcome");
		super.checkLinkExists("Account");
	}

	protected void signOut() {
		super.navigateHome();
		super.clickOnMenu("Sign out", null);
		super.checkSimplePath("/master/welcome");
	}

	protected void signUp(final String username, final String password, final String name, final String surname, final String email) {
		assert !StringHelper.isBlank(username);
		assert !StringHelper.isBlank(password);
		assert !StringHelper.isBlank(name);
		assert !StringHelper.isBlank(surname);
		assert !StringHelper.isBlank(email);

		super.navigateHome();
		super.clickOnMenu("Sign up", null);	
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", password);
		super.fillInputBoxIn("identity.name", name);
		super.fillInputBoxIn("identity.surname", surname);
		super.fillInputBoxIn("identity.email", email);
		super.fillInputBoxIn("accept", "true");
		super.clickOnSubmitButton("Sign up");
		super.checkSimplePath("/master/welcome");
	}
	
	protected void checkErrorsTextAreaExist(final String name) {
		assert !StringHelper.isBlank(name);

		String xpath;
		By locator;

		xpath = String.format("//div[@class='form-group'][textarea[@id='%s'] and div[@class='text-danger']]", name);
		locator = By.xpath(xpath);
		assert super.exists(locator) : String.format("Errors found in input box '%s'", name);
	}

	protected void checkErrorsAcmeMessageExist(final int div, final int table, final int tr, final String name) {
		assert !StringHelper.isBlank(name);

		String xpath;
		By locator;

		xpath = String.format("/html/body/div[%d]/div/table[%d]/tbody/tr[%d]/td", div, table, tr);
		locator = By.xpath(xpath);
		assert super.exists(locator) : String.format("Errors found in div %d, table %d and tr %d", div, table, tr);
		final WebElement w = super.locateOne(locator);
		assert w.getText().equals(name) : String.format("Errors found. Expected value: '%s', real value: '%s'", name, w.getText());
	}
}


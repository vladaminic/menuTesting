package menuApp.tests.common;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

	protected WebDriver driver;
	protected String baseUrl;
	// protected ExtentTest test;

	public BasePage(WebDriver driver, String baseUrl) {
		this.driver = driver;
		this.baseUrl = baseUrl;
		// this.test = test;

	}

	public abstract BasePage show();

	public abstract boolean isDisplayed();
}

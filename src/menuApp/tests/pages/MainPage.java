package menuApp.tests.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import menuApp.tests.common.BasePage;
import menuApp.tests.utils.UIMap;

public class MainPage extends BasePage {

	DirectorySubPage directorySubPage;

	public MainPage(WebDriver driver, String url) {
		super(driver, url);
	}

	@Override
	public BasePage show() {
		this.driver.get(this.baseUrl);

		return this;
	}

	@Override
	public boolean isDisplayed() {
		try {
			new WebDriverWait(this.driver, 60)
					.until(ExpectedConditions.presenceOfElementLocated(UIMap.getLocator("mainPage.home")));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private WebElement getBtnDineInPickup() {
		try {
			return this.driver.findElement(UIMap.getLocator("mainPage.btnDineInPickup"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find button Dine-in Pickup", e);
		}
	}

	private WebElement getInputSelectLocation() {
		try {
			return this.driver.findElement(UIMap.getLocator("mainPage.inputSelectLocation"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find input select location", e);
		}
	}

	private WebElement getLocationDropdown() {
		try {
			return this.driver.findElement(UIMap.getLocator("mainPage.dropdownlist"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find dropdown list", e);
		}
	}

	private List<WebElement> getDropdownResults(WebElement weLocationDropdown) {

		try {
			return new WebDriverWait(this.driver, 10).until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(
					weLocationDropdown, UIMap.getLocator("mainPage.dropdownlist.results")));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find dropdown list results.", e);
		}
	}

	private void enterLocation(String location) {

		WebElement we = this.getInputSelectLocation();
		we.sendKeys(location);

	}

	private WebElement getLocation(String location) {

		this.enterLocation(location);

		List<WebElement> weList = this.getDropdownResults(this.getLocationDropdown());

		for (WebElement we : weList) {
			if (we.getText().equals(location)) {
				return we;
			}
		}
		return null;

	}

	public MainPage pressBtnDineInPickup() {

		this.getBtnDineInPickup().click();

		return this;
	}

	public DirectorySubPage selectLocation(String location) {

		this.getLocation(location).click();
		this.directorySubPage = new DirectorySubPage(driver, this.baseUrl);
		this.directorySubPage.isDisplayed();

		return this.directorySubPage;
	}

}

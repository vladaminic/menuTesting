package menuApp.tests.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import menuApp.tests.common.BasePage;
import menuApp.tests.utils.UIMap;

public class DirectorySubPage extends BasePage {

	private LocationSubPage locationSubPage;

	public DirectorySubPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl + "/directory");
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
					.until(ExpectedConditions.presenceOfElementLocated(UIMap.getLocator("directorySubPage.app")));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private WebElement getPnlLocations() {
		try {
			return new WebDriverWait(this.driver, 60).until(
					ExpectedConditions.presenceOfElementLocated(UIMap.getLocator("directorySubPage.pnlLocations")));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find locations panel", e);
		}
	}

	private List<WebElement> getLocationsList(WebElement wePnlLocations) {

		try {
			return wePnlLocations.findElements(UIMap.getLocator("directorySubPage.pnlLocations.locations"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find list of locations.", e);
		}
	}

	private WebElement getLocation(String location) {
		List<WebElement> weList = this.getLocationsList(this.getPnlLocations());

		for (WebElement we : weList) {
			if (we.getAttribute("alt").contains(location)) {
				return we;
			}
		}
		return null;
	}

	public List<String> getLocations() {

		List<WebElement> weList = this.getLocationsList(this.getPnlLocations());
		List<String> locations = new ArrayList<>();

		for (WebElement we : weList) {
			locations.add(we.getText());
		}
		return locations;

	}

	public LocationSubPage selectLocation(String location) {

		this.getLocation(location).click();
		this.locationSubPage = new LocationSubPage(driver, driver.getCurrentUrl());
		this.locationSubPage.isDisplayed();
		
		return this.locationSubPage;
	}

}

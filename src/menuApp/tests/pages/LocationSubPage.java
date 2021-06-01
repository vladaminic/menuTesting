package menuApp.tests.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import menuApp.tests.common.BasePage;
import menuApp.tests.utils.UIMap;

public class LocationSubPage extends BasePage {

	public LocationSubPage(WebDriver driver, String baseUrl) {
		super(driver, baseUrl);
	}

	@Override
	public BasePage show() {
		this.driver.get(this.baseUrl);

		return this;
	}

	@Override
	public boolean isDisplayed() {
		try {
			new WebDriverWait(this.driver, 60).until(
					ExpectedConditions.presenceOfElementLocated(UIMap.getLocator("locationSubPage.btnMoreInfo")));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void waitForPageToBeLoaded() {
		try {			
			WebElement weLoader = new WebDriverWait(this.driver, 10).until(ExpectedConditions
					.presenceOfElementLocated(UIMap.getLocator("locationSubPage.divLoaderOverlay")));
			new WebDriverWait(this.driver, 10).until(ExpectedConditions.stalenessOf(weLoader));
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find loader", e);
		}
	}
	
	private void waitForPanelToBeLoaded() {
		try {			
			WebElement weLoader = new WebDriverWait(this.driver, 10).until(ExpectedConditions
					.presenceOfElementLocated(UIMap.getLocator("locationSubPage.divLoaderOverlay")));
			new WebDriverWait(this.driver, 10).until(ExpectedConditions.not(ExpectedConditions.stalenessOf(weLoader)));
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find loader", e);
		}
	}
	
	private WebElement getBtnMoreInfo() {
		try {
			return this.driver.findElement(UIMap.getLocator("locationSubPage.btnMoreInfo"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find More info button", e);
		}
	}

	private WebElement getPnlMoreInfo() {
		try {
			return new WebDriverWait(this.driver, 60).until(
					ExpectedConditions.presenceOfElementLocated(UIMap.getLocator("locationSubPage.pnlMoreInfo")));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find More info panel", e);
		}
	}

	private List<WebElement> getOpeningHoursList(WebElement wePnlMoreInfo) {
		try {
			return wePnlMoreInfo.findElements(UIMap.getLocator("locationSubPage.pnlMoreInfo.listOpeningHours"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find Opening hours list", e);
		}
	}

	private WebElement getBtnClose(WebElement wePnlMoreInfo) {
		try {
			return wePnlMoreInfo.findElement(UIMap.getLocator("locationSubPage.pnlMoreInfo.btnClose"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find Close button", e);
		}
	}

	private WebElement getMenu() {
		try {
			return driver.findElement(UIMap.getLocator("locationSubPage.menu"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find menu", e);
		}
	}

	private List<WebElement> getMenuItems(WebElement weMenu) {
		try {
			return weMenu.findElements(UIMap.getLocator("locationSubPage.menu.items"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find menu items", e);
		}
	}

	private WebElement getPnlItem() {
		try {
			return new WebDriverWait(this.driver, 60)
					.until(ExpectedConditions.presenceOfElementLocated(UIMap.getLocator("locationSubPage.pnlItem")));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find item panel", e);
		}
	}

	private WebElement getBtnIncreaseQuantity(WebElement wePnlItem) {
		try {
			return wePnlItem.findElement(UIMap.getLocator("locationSubPage.pnlItem.btnIncreaseQuantity"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find Increase quantity button", e);
		}
	}

	private WebElement getBtnAdd(WebElement wePnlItem) {
		try {
			return wePnlItem.findElement(UIMap.getLocator("locationSubPage.pnlItem.btnAdd"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find Add button", e);
		}
	}
	
	private WebElement getinputSpecialInstructions(WebElement wePnlItem) {
		try {
			return wePnlItem.findElement(UIMap.getLocator("locationSubPage.pnlItem.inputSpecialInstructions"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find Special instructions input", e);
		}
	}
	
	private WebElement getPnlOrder() {
		try {
			return new WebDriverWait(this.driver, 60)
					.until(ExpectedConditions.presenceOfElementLocated(UIMap.getLocator("locationSubPage.pnlOrder")));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find Order panel", e);
		}
	}
	
	private WebElement getLblTotalPrice(WebElement weOrder) {
		try {
			return weOrder.findElement(UIMap.getLocator("locationSubPage.pnlOrder.lblTotalPrice"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Could not find total price label", e);
		}
	}

	private WebElement getOpeningHours(String day) {

		List<WebElement> weList = this.getOpeningHoursList(this.getPnlMoreInfo());

		for (WebElement we : weList) {
			if (we.getText().contains(day)) {
				return we;
			}
		}
		return null;
	}

	private WebElement getMenuItem(String item) {
		List<WebElement> weList = this.getMenuItems(this.getMenu());

		for (WebElement we : weList) {
			if (we.getText().contains(item)) {
				return we;
			}
		}
		return null;
	}

	private void increaseQuantity(int quantity) {
		WebElement we = this.getBtnIncreaseQuantity(this.getPnlItem());

		for (int i = 0; i < quantity-1; i++) {
			we.click();
		}

	}

	public LocationSubPage openMoreInfoModul() {
			
		this.getBtnMoreInfo().click();

		return this;
	}

	public String getWorkingHours(String day) { // Enum!!!!!!!!!!!

		String r = this.getOpeningHours(day).getText().substring(day.length() + 1);

		return r;
	}

	public LocationSubPage closeMoreInfoModul() {
		
		this.waitForPanelToBeLoaded();
		this.getBtnClose(this.getPnlMoreInfo()).click();

		return this;
	}

	public LocationSubPage selectItem(String item) {

		this.getMenuItem(item).click();

		return this;
	}

	public LocationSubPage addToCart(int quantity, String specialInstructions) {
		
		this.waitForPanelToBeLoaded();
		this.increaseQuantity(quantity);
		this.getinputSpecialInstructions(this.getPnlItem()).sendKeys(specialInstructions);
		this.getBtnAdd(this.getPnlItem()).click();

		return this;
	}
	
	public double getOrderTotalPrice() {
		
		this.waitForPageToBeLoaded();
		String price = this.getLblTotalPrice(this.getPnlOrder()).getText().substring(2);
				
		return Double.parseDouble(price);
	}
}

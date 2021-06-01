package menuApp.tests.suites;


import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import menuApp.tests.pages.DirectorySubPage;
import menuApp.tests.pages.LocationSubPage;
import menuApp.tests.pages.MainPage;

public class MenuSuite extends BaseSuite {

	@Parameters({ "baseUrl", "cityLocation", "locationNumber" })
	@Test
	public void verifyNumberOfLocations(String url, String cityLocation, int locationNumber, ITestContext context) {
		MainPage mp = new MainPage(driver, url);

		Assert.assertTrue(mp.show().isDisplayed(), "Main page is not fetched");

		DirectorySubPage dsp = mp.pressBtnDineInPickup().selectLocation(cityLocation);

		Assert.assertEquals(dsp.getLocations().size(), locationNumber, "The number of locations is not expected");

		context.setAttribute("page", dsp);

	}
	@Parameters({ "location", "day", "workingHours" })
	@Test(dependsOnMethods = "verifyNumberOfLocations")
	public void verifyWorkingHours(String location, String day, String workingHours, ITestContext context) {

		DirectorySubPage dsp = (DirectorySubPage)context.getAttribute("page");

		LocationSubPage lsp = dsp.selectLocation(location);

		String actual = lsp.openMoreInfoModul().getWorkingHours(day);
		
		lsp.closeMoreInfoModul();
		
		Assert.assertEquals(actual, workingHours, "Working hours are not expected");
		
		context.setAttribute("page", lsp);
	}
	
	@Parameters({ "item", "quantity", "specialInstructions", "price" })
	@Test(dependsOnMethods = "verifyWorkingHours")
	public void verifyCanItemsBeAddedToCart(String item, int quantity, String specialInstructions, double price, ITestContext context) {

		LocationSubPage lsp = (LocationSubPage)context.getAttribute("page");

		double actual = lsp.selectItem(item).addToCart(quantity, "").getOrderTotalPrice();
				
		Assert.assertEquals(actual, price, "The total price is not expected");
				
	}
	
	// Za cetvrti test mi je bila ideja da se samo parametri za ovaj test promene, jer su koraci identicni.
}

package menuApp.tests.suites;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseSuite {

	protected DesiredCapabilities cap;
	protected WebDriver driver;

	@Parameters({ "browser" })
	@BeforeTest
	public void beforeClass(String browser) {

		// String browser = "Chrome";
		if (browser.equalsIgnoreCase("Chrome")) {
			try {
				this.cap = DesiredCapabilities.chrome();
				// this.cap.setCapability("webdriver.chrome.driver",
				// "driver-lib\\chromedriver.exe");
				// this.cap.setCapability(ChromeOptions.CAPABILITY, new
				// ChromeOptions().addArguments("--lang=en"));
				System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
				this.driver = new ChromeDriver();
				this.driver.manage().window().maximize();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		/*
		 * 
		 * else if (browser.equalsIgnoreCase("Firefox")) { try { nodeURL =
		 * "http://10.1.2.167:4545/wd/hub"; this.cap = DesiredCapabilities.firefox();
		 * FirefoxOptions fOptions = new FirefoxOptions();
		 * this.cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS,
		 * fOptions.addArguments("--disable-notifications")); this.driver = new
		 * RemoteWebDriver(new URL(nodeURL), cap); this.wait = new WebDriverWait(driver,
		 * 20); this.driver.manage().window().maximize();
		 * 
		 * } catch (Exception e) { System.out.println(e); } } else if
		 * (browser.equalsIgnoreCase("IE")) { try { nodeURL =
		 * "http://10.1.2.167:4547/wd/hub"; System.out.println("IE"); cap =
		 * DesiredCapabilities.internetExplorer(); //
		 * cap.setCapability(InternetExplorerDriver.
		 * INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, // true); driver = new
		 * RemoteWebDriver(new URL(nodeURL), cap);
		 * 
		 * } catch (Exception e) { System.out.println(e); }
		 * 
		 * } else if (browser.equalsIgnoreCase("Android")) { try { nodeURL =
		 * "http://localhost:4723/wd/hub"; System.out.println("Android"); cap =
		 * DesiredCapabilities.android(); cap.setCapability("platformName", "android");
		 * cap.setCapability("platformVersion", "7.1.1");
		 * cap.setCapability("deviceName", "E1MGAP1832405250");
		 * cap.setCapability("browserName", "chrome");
		 * cap.setCapability(ChromeOptions.CAPABILITY, new
		 * ChromeOptions().addArguments("--disable-notifications")); driver = new
		 * RemoteWebDriver(new URL(nodeURL), cap);
		 * 
		 * } catch (Exception e) { System.out.println(e); } }
		 */

	}

	@AfterTest
	public void afterClass() {
		driver.close();
	}

}

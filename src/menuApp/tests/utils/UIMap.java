package menuApp.tests.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;

public class UIMap {

	private static Properties properties;

	public static Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
			try {
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				InputStream input = classLoader.getResourceAsStream("NameMapping.properties");
				properties.load(input);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return properties;
	}

	public static By getLocator(String elementName) throws Exception {

		String locator = getProperties().getProperty(elementName);
		String locatorType = locator.substring(0, locator.indexOf("="));
		String locatorValue = locator.substring(locator.indexOf("=") + 1, locator.length());

		switch (locatorType.toLowerCase()) {

		case "id":
			return By.id(locatorValue);
		case "name":
			return By.name(locatorValue);
		case "classname":
			return By.className(locatorValue);
		case "tagname":
			return By.tagName(locatorValue);
		case "linktext":
			return By.linkText(locatorValue);
		case "partiallinktext":
			return By.partialLinkText(locatorValue);
		case "cssselector":
			return By.cssSelector(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		default:
			throw new Exception("Locator type '" + locatorType + "' not defined!!");
		}

	}
}

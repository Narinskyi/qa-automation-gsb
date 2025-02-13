package zm.co.gsb.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zm.co.gsb.config.ConfigManager;
import zm.co.gsb.driver.DriverFactory;

public class UITests {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
    }

    @Test
    public void testHomePageTitle() {
        driver.get(ConfigManager.getProperty("baseUrl"));
        String title = driver.getTitle();
        Assert.assertNotNull(title, "Page title should not be null");
        System.out.println("Page title is: " + title);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}


package zm.co.gsb.tests.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import zm.co.gsb.config.ConfigManager;
import zm.co.gsb.driver.DriverFactory;

public class AbstractUiTest {

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
    }

    protected WebDriver driver;

    @Step("Navigate to home page")
    protected void openHomePage() {
        driver.get(ConfigManager.getProperty("baseUrl"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}

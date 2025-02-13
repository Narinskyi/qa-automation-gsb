package zm.co.gsb.tests.ui.sportsbook;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zm.co.gsb.config.ConfigManager;
import zm.co.gsb.data.components.Header;
import zm.co.gsb.data.components.LoginPage;
import zm.co.gsb.driver.DriverFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@Epic("Login")
@Feature("Form")
public class LoginTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
    }

    @Test
    @Description("Verify default country code selection")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("LOGIN_TC_01")
    public void testDefaultCountryCode() {
        driver.get(ConfigManager.getProperty("baseUrl"));

        driver.findElement(Header.Button.LOGIN).click();

        assertThat(
                driver.findElement(LoginPage.Dropdown.COUNTRY_CODE).getText(),
                containsString("+260"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
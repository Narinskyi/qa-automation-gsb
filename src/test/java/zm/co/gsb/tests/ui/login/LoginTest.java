package zm.co.gsb.tests.ui.login;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import zm.co.gsb.data.components.Header;
import zm.co.gsb.data.components.LoginPage;
import zm.co.gsb.tests.ui.AbstractUiTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@Epic("Login")
@Feature("Form")
public class LoginTest extends AbstractUiTest {

    @Test
    @Description("Verify default country code selection")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("LOGIN_TC_01")
    public void testDefaultCountryCode() {
        openHomePage();

        driver.findElement(Header.Button.LOGIN).click();

        assertThat(
                driver.findElement(LoginPage.Dropdown.COUNTRY_CODE).getText(),
                containsString("+260"));
    }
}
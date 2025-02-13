package zm.co.gsb.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zm.co.gsb.config.ConfigManager;
import zm.co.gsb.data.components.SportsBookMenu;
import zm.co.gsb.data.components.SportsTable;
import zm.co.gsb.driver.DriverFactory;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EventsTests {
    private static final Logger logger = LogManager.getLogger(EventsTests.class);

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
    }

    @Test
    public void testUpcomingEventsOdds() {
        driver.get(ConfigManager.getProperty("baseUrl"));

        assertThat(driver.findElement(SportsBookMenu.Button.UPCOMING).getAttribute("class"),
                equalTo("au-s-s"));

        List<Double> odds = driver.findElements(SportsTable.Upcoming.ODDS_CONTAINER)
                .stream()
                .map(WebElement::getText)
                .map(Double::parseDouble)
                .filter(value -> value >= 1.5 && value <= 3.34)
                .toList();

        assertThat(odds, not(empty()));

        logger.info(odds);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}


package zm.co.gsb.tests.ui.sportsbook;

import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import zm.co.gsb.data.components.SportsBookMenu;
import zm.co.gsb.data.components.SportsTable;
import zm.co.gsb.tests.ui.AbstractUiTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Sportsbook")
@Feature("Events")
public class EventsTests extends AbstractUiTest {
    private static final Logger logger = LogManager.getLogger(EventsTests.class);

    @Test
    @Description("Upcoming events odds are displayed within the acceptable range (1.5 to 3.34), 'Upcoming' button is active")
    @Severity(SeverityLevel.NORMAL)
    @TmsLinks({
            @TmsLink("EVENTS_TC_01"),
            @TmsLink("EVENTS_TC_02")
    })
    public void testUpcomingEventsOdds() {
        openHomePage();

        assertThat("Upcoming button should be active",
                driver.findElement(SportsBookMenu.Button.UPCOMING).getAttribute("class"),
                equalTo("au-s-s"));

        List<Double> odds = driver.findElements(SportsTable.Upcoming.ODDS_CONTAINER)
                .stream()
                .map(WebElement::getText)
                .map(Double::parseDouble)
                .filter(value -> value >= 1.5 && value <= 3.34)
                .toList();

        assertThat("Odds list should not be empty", odds, not(empty()));

        logger.info("Odds: {}", odds);
    }
}
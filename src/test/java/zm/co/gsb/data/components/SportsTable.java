package zm.co.gsb.data.components;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

public class SportsTable {
    public static class Upcoming {
        public static final By ODDS_CONTAINER = new ByChained(
                By.cssSelector("table.upcoming"),
                By.cssSelector("span.odds.none"));
    }
}

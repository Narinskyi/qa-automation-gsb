package zm.co.gsb.driver;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class WebDriverDecorator implements WebDriver {

    private static final Logger logger = LogManager.getLogger(WebDriverDecorator.class);

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Decorates an existing WebDriver with explicit waits.
     *
     * @param driver  the original WebDriver instance
     * @param timeout the maximum time to wait for conditions
     */
    public WebDriverDecorator(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
        logger.info("Initialized WebDriverDecorator with timeout: {} seconds", timeout.getSeconds());
    }

    @Step("Find element using locator: {0}")
    @Override
    public WebElement findElement(By by) {
        logger.info("Waiting for visibility of element: {}", by);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Step("Find elements using locator: {0}")
    @Override
    public List<WebElement> findElements(By by) {
        logger.info("Waiting for presence of at least one element: {}", by);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElements(by);
    }

    @Step("Click on element located by: {0}")
    public void click(By by) {
        logger.info("Clicking on element: {}", by);
        findElement(by).click();
    }

    @Step("Send keys '{1}' to element located by: {0}")
    public void sendKeys(By by, CharSequence... keysToSend) {
        logger.info("Sending keys {} to element: {}", keysToSend, by);
        findElement(by).sendKeys(keysToSend);
    }

    @Step("Get text from element located by: {0}")
    public String getText(By by) {
        logger.info("Getting text from element: {}", by);
        return findElement(by).getText();
    }

    // Delegate methods from WebDriver to the wrapped instance

    @Override
    @Step("Navigate to URL: {0}")
    public void get(String url) {
        logger.info("Navigating to URL: {}", url);
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    @Step("Close the current window")
    public void close() {
        logger.info("Closing the current window");
        driver.close();
    }

    @Override
    @Step("Quit the WebDriver")
    public void quit() {
        logger.info("Quitting the WebDriver");
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }
}

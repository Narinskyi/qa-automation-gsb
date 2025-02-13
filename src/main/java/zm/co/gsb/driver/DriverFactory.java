package zm.co.gsb.driver;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import zm.co.gsb.config.ConfigManager;

import java.time.Duration;

public class DriverFactory {
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Step("Initializing WebDriver for browser: {0}")
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = ConfigManager.getProperty("browser");
            logger.info("Initializing WebDriver for browser: {}", browser);
            WebDriver webDriver;
            switch (browser.toLowerCase()) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                    webDriver = new ChromeDriver();
                    logger.info("ChromeDriver initiated successfully");
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
                    webDriver = new FirefoxDriver();
                    logger.info("FirefoxDriver initiated successfully");
                    break;
                default:
                    logger.error("Unsupported browser: {}", browser);
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            long timeout = Long.parseLong(ConfigManager.getProperty("timeout"));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
            logger.info("Implicit wait set to {} seconds", timeout);
            driver.set(webDriver);
        }
        return driver.get();
    }

    @Step("Quitting the WebDriver")
    public static void quitDriver() {
        if (driver.get() != null) {
            logger.info("Quitting the WebDriver");
            driver.get().quit();
            driver.remove();
        } else {
            logger.warn("No WebDriver instance found to quit");
        }
    }
}



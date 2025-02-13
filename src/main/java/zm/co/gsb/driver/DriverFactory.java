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
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = ConfigManager.getProperty("browser");
            logger.info("Initializing WebDriver for: {}", browser);

            long timeout = Long.parseLong(ConfigManager.getProperty("timeout"));

            WebDriver webDriver;
            switch (browser.toLowerCase()) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                    webDriver = new WebDriverDecorator(new ChromeDriver(), Duration.ofSeconds(timeout));
                    logger.info("ChromeDriver initialized successfully");
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
                    webDriver = new WebDriverDecorator(new FirefoxDriver(), Duration.ofSeconds(timeout));
                    logger.info("FirefoxDriver initialized successfully");
                    break;
                default:
                    logger.error("Unsupported browser: {}", browser);
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.set(webDriver);
        }
        return driver.get();
    }

    @Step("Quit the WebDriver")
    public static void quitDriver() {
        if (driver.get() != null) {
            logger.info("Quitting the WebDriver");
            driver.get().close();
            driver.remove();
        } else {
            logger.warn("No WebDriver found to quit.");
        }
    }
}



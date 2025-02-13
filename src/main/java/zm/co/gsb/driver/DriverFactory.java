package zm.co.gsb.driver;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
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
                case "edge":
                    System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/msedgedriver.exe");
                    webDriver = new WebDriverDecorator(new EdgeDriver(), Duration.ofSeconds(timeout));
                    logger.info("EdgeDriver initialized successfully");
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



package zm.co.gsb.ui.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import zm.co.gsb.constants.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public abstract class Test {

    // Use ThreadLocal to manage WebDriver per thread (parallel execution)
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    @BeforeClass
    public void setup() {
        // Load config properties
        Properties properties = new Properties();
        try {
            FileInputStream configFile = new FileInputStream("src/test/resources/config.properties");
            properties.load(configFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file", e);
        }

        // Read properties
        String browser = System.getProperty("browser", properties.getProperty("browser", "chrome")).toLowerCase();
        boolean isLocal = Boolean.parseBoolean(System.getProperty("local", properties.getProperty("local", "true")));
        String gridUrl = properties.getProperty("grid.url", "http://localhost:4444/wd/hub");

        // Select WebDriver
        if (isLocal) {
            // Run locally
            switch (browser) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                    DRIVER.set(new ChromeDriver());
                    break;
                case "edge":
                    System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
                    DRIVER.set(new EdgeDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        } else {
            // Run on Selenium Grid
            try {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(browser);
                DRIVER.set(new RemoteWebDriver(new URL(gridUrl), capabilities));
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid Selenium Grid URL", e);
            }
        }
    }

    @AfterClass(alwaysRun = true)
    public void quit() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();
        }
    }

    public void open(String url) {
        DRIVER.get().get(url);
    }

    private WebElement find_visible(By locator) {
        return new WebDriverWait(DRIVER.get(), Duration.ofSeconds(Constants.WEB_DRIVER_WAIT_IN_SEC))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickOn(By locator) {
        find_visible(locator).click();
    }

    public String getTextOf(By locator) {
        return DRIVER.get().findElement(locator).getText();
    }

    public String getAttributeOf(By locator, String attributeName) {
        return DRIVER.get().findElement(locator).getAttribute(attributeName);
    }
}


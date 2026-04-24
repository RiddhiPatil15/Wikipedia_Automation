package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URL;

public class BaseTest {

    protected static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
    
        driver = new ChromeDriver(options);
    }
     public static void quitDriver() {
            if (driver != null) {
                driver.quit();
            }
        }
    }
    

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

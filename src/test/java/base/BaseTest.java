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
        try {
            ChromeOptions options = new ChromeOptions();

            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444"),
                    options
            );

            driver.manage().window().maximize();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
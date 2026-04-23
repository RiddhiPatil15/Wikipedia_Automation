package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.*;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;

    By searchBox = By.name("search");
    By searchButton = By.xpath("//button[@type='submit']");
    By paragraphs = By.xpath("//div[@id='mw-content-text']//p");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openPage() {
        driver.get("https://www.wikipedia.org/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
    }

    public void search(String keyword) {

        WebElement box = wait.until(
                ExpectedConditions.elementToBeClickable(searchBox)
        );

        box.clear();
        box.sendKeys(keyword);

        driver.findElement(searchButton).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(paragraphs));
    }

    public boolean isResultDisplayed() {

        return driver.getTitle().contains("Wikipedia");
    }

    public String getParagraphOrError() {

        List<WebElement> paraList = driver.findElements(paragraphs);

        String paragraphText = "";

        for (WebElement para : paraList) {
            if (!para.getText().trim().isEmpty()) {
                paragraphText = para.getText();
                break;
            }
        }

        if (paragraphText.toLowerCase().contains("does not exist")) {
            return "No Wikipedia article found.\n";
        }

        if (paragraphText.isEmpty()) {
            return "No content available.\n";
        }

        String[] sentences = paragraphText.split("\\.");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 3 && i < sentences.length; i++) {
            result.append(sentences[i].trim()).append(".\n");
        }

        return result.toString();
    }
}
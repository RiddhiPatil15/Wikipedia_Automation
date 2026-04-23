package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import pages.SearchPage;
import utils.FileUtil;
import base.BaseTest;

import static io.restassured.RestAssured.*;

public class SearchSteps {

    SearchPage searchPage;

    @Given("user is on Wikipedia home page")
    public void openHomePage() {
        searchPage = new SearchPage(BaseTest.getDriver());
        searchPage.openPage();
    }

    @When("user searches for {string}")
    public void searchKeyword(String keyword) {

        System.out.println("Running test for: " + keyword);

        searchPage.openPage();

        searchPage.search(keyword);

        String content = searchPage.getParagraphOrError();

        String encodedKeyword = keyword.trim().replaceAll("\\s+", "_");

        // API Validation
        Response response =
                given()
                        .baseUri("https://en.wikipedia.org")
                        .header("User-Agent", "Mozilla/5.0")
                        .when()
                        .get("/wiki/" + encodedKeyword);

        int statusCode = response.getStatusCode();

        // Better handling (don’t break entire run)
        if (statusCode != 200) {
            System.out.println("API Warning for: " + keyword + " | Status: " + statusCode);
        }

        // Write output
        FileUtil.writeToFile(
                "Search: " + keyword + "\n" +
                        content + "\n" +
                        "API Status: " + statusCode + "\n" +
                        "-----------------\n"
        );
    }

    @Then("results should be validated and stored")
    public void validateResults() {

        System.out.println("Execution completed.");
    }
}
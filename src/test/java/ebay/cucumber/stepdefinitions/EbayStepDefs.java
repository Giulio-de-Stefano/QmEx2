package ebay.cucumber.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ebay.ChromeDriverBuilder;
import ebay.results.AuctionSearchResult;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static ebay.EbayElements.*;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.assertTrue;

/**
 * These steps can be organised in separate classes as they grow in number/complexity
 * (e.g. BrowserActionStepDefs, VerificationStepDefs etc...)
 */
public class EbayStepDefs {
    private WebDriver webDriver = null;

    @Given("^browser is opened$")
    public void browser_is_opened() {
        webDriver = ChromeDriverBuilder.buildInstance();
    }

    @When("^I navigate to url (.*)$")
    public void I_navigate_to_url(String url) {
        webDriver.get(url);
    }

    @And("^I query for item (.*)$")
    public void I_query_for_item(String item) {
        webDriver.findElement(SEARCH_INPUT).sendKeys(item);
        webDriver.findElement(SEARCH_BTN).click();
    }

    @And("^I filter by (.*)$")
    public void I_filter_by(String filter) throws OperationNotSupportedException {
        switch (filter.toLowerCase()) {
            case "auction":
                webDriver.findElement(AUCTION_FILTER_BTN).click();
                break;
            case "lowest price":
                Actions actions = new Actions(webDriver);
                actions.moveToElement(webDriver.findElement(FILTER_MENU)).perform();
                WebDriverWait wait = new WebDriverWait(webDriver, 5);
                wait.until(ExpectedConditions.elementToBeClickable(LOWEST_PRICE_INCL_PP_LI)).click();
                break;
            default:
                throw new OperationNotSupportedException("Filter not supported");
        }
    }

    @Then("^each result displays the number of bids$")
    public void each_result_displays_the_number_of_bids() {
        List<WebElement> searchResults = webDriver.findElement(RESULT_LIST).findElements(RESULT_ITEM);
        Set<AuctionSearchResult> searchResultSet = new LinkedHashSet<>();

        for (WebElement webElement : searchResults)
            searchResultSet.add(new AuctionSearchResult(webElement));

        for (AuctionSearchResult res : searchResultSet) {
            String bids = res.getBids();
            System.out.println("Checking auction: [" + res.getTitle() + "]");

            assertTrue("Bids not null", bids != null);
            assertTrue("Bids not empty", isNotEmpty(bids));
            assertTrue("Bids is not negative", parseInt(bids) >= 0);
        }
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

}


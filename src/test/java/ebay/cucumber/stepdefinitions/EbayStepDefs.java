package ebay.cucumber.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ebay.helper.BrowserFactory;
import ebay.pages.HomePage;
import ebay.pages.SearchResultsPage;
import ebay.results.AuctionSearchResult;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static ebay.EbayElements.RESULT_ITEM;
import static ebay.EbayElements.RESULT_LIST;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.assertTrue;

/**
 * These steps can be organised in separate classes as they grow in number/complexity
 * (e.g. BrowserActionStepDefs, VerificationStepDefs etc...)
 */
public class EbayStepDefs {
    private WebDriver webDriver = null;

    @When("^I navigate to url (.*)$")
    public void I_navigate_to_url(String url) {
        if (webDriver == null)
            webDriver = BrowserFactory.startBrowser("chrome", url);
        else
            webDriver.get(url);
    }

    @And("^I query for item (.*)$")
    public void I_query_for_item(String item) {
        HomePage homePage = PageFactory.initElements(webDriver, HomePage.class);
        homePage.search(item);
    }

    @And("^I filter by auction$")
    public void I_filter_by_auction() {
        SearchResultsPage searchResultsPage = PageFactory.initElements(webDriver, SearchResultsPage.class);
        searchResultsPage.filterByAuction();
    }

    @And("^I filter by lowest price$")
    public void I_filter_by_lowest_price() {
        SearchResultsPage searchResultsPage = PageFactory.initElements(webDriver, SearchResultsPage.class);
        searchResultsPage.filterByLowestPrice();
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


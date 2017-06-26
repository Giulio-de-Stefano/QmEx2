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

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

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
        HomePage homePage = initElements(webDriver, HomePage.class);
        homePage.search(item);
    }

    @And("^I filter by auction$")
    public void I_filter_by_auction() {
        SearchResultsPage searchResultsPage = initElements(webDriver, SearchResultsPage.class);
        searchResultsPage.filterByAuction();
    }

    @And("^I filter by lowest price$")
    public void I_filter_by_lowest_price() {
        SearchResultsPage searchResultsPage = initElements(webDriver, SearchResultsPage.class);
        searchResultsPage.filterByLowestPrice();
    }

    @Then("^each result displays the number of bids$")
    public void each_result_displays_the_number_of_bids() {
        SearchResultsPage searchResultsPage = initElements(webDriver, SearchResultsPage.class);

        for (AuctionSearchResult res : searchResultsPage.getAuctionSearchResults()) {
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


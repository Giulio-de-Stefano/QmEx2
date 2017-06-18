package ebay;

import ebay.results.AuctionSearchResult;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static ebay.EbayElements.*;
import static java.lang.System.getenv;
import static java.lang.System.setProperty;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.testng.Assert.assertTrue;

public class TestEbay {

    private static WebDriver webDriver;

    @BeforeSuite
    public static void beforeSuite() {
        final String CHROMEDRIVER_PATH = getenv("USERPROFILE") + "\\Desktop\\exercise\\chromedriver.exe";
        setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
    }

    @BeforeMethod
    public static void beforeMethod() {
        final String TARGET_URL = "https://www.ebay.co.uk/";
        final String SEARCH_TERM = "iPhone";

        final String UBLOCK_ORIGIN_PATH = getenv("USERPROFILE") + "\\Desktop\\exercise\\ublock_origin_1_12_4.crx";
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File(UBLOCK_ORIGIN_PATH));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        webDriver = new ChromeDriver(capabilities); // start with a fresh session for each test
        webDriver.manage().timeouts().implicitlyWait(5, SECONDS);
        webDriver.get(TARGET_URL);
        webDriver.findElement(SEARCH_INPUT).sendKeys(SEARCH_TERM);
        webDriver.findElement(SEARCH_BTN).click();
    }

    @Test
    public static void bidsDisplayed() {
        webDriver.findElement(AUCTION_FILTER_BTN).click();
        List<WebElement> searchResults = webDriver.findElement(RESULT_LIST).findElements(RESULT_ITEM);
        Set<AuctionSearchResult> searchResultSet = new LinkedHashSet<>();

        for (WebElement webElement : searchResults)
            searchResultSet.add(new AuctionSearchResult(webElement));

        for (AuctionSearchResult res : searchResultSet) {
            String bids = res.getBids();
            Reporter.log("Checking auction: [" + res.getTitle() + "]");
            assertTrue(bids != null, "Bids not null");
            assertTrue(isNotEmpty(bids), "Bids not empty");
            assertTrue(isNumeric(bids), "Bids is numeric");
            assertTrue(Integer.parseInt(bids) >= 0, "Bids is not negative");
        }
    }
}

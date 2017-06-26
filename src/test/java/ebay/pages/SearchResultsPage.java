package ebay.pages;

import ebay.results.AuctionSearchResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SearchResultsPage extends PageAB {

    @FindBy(id = "DashSortByContainer")
    WebElement filterMenu;
    @FindBy(css = ".tgl_button.center_b")
    WebElement auctionFilterBtn;
    @FindBy(xpath = "//*[text()='Lowest price + P&P']")
    WebElement lowestPriceIncludingPPListElement;
    @FindBy(className = "sresult")
    List<WebElement> searchResults;
    @FindBy(className = "lvprice")

    private By.ByClassName byTitle = new By.ByClassName("vip");
    private By.ByClassName byPrice = new By.ByClassName("lvprice");
    private By.ByClassName byBids = new By.ByClassName("lvformat");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void filterByAuction() {
        auctionFilterBtn.click();
    }

    public void filterByLowestPrice() {
        Actions actions = new Actions(driver);
        actions.moveToElement(filterMenu).perform();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(lowestPriceIncludingPPListElement)).click();
    }

    public Set<AuctionSearchResult> getAuctionSearchResults() {
        Set<AuctionSearchResult> auctionSearchResults = new LinkedHashSet<>();

        for (WebElement result : searchResults) {
            String title = result.findElement(byTitle).getText();
            String price = result.findElement(byPrice).getText();
            String bids = result.findElement(byBids).getText();
            auctionSearchResults.add(new AuctionSearchResult(title, price, bids));
        }

        return auctionSearchResults;
    }
}

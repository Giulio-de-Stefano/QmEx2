package ebay.results;

import org.openqa.selenium.WebElement;

import static ebay.EbayElements.RESULT_ITEM_BIDS;

public class AuctionSearchResult extends SearchResult {
    private final String bids;

    public AuctionSearchResult(WebElement webElement) {
        super(webElement);
        this.bids = webElement.findElement(RESULT_ITEM_BIDS).getText();
    }

    public String getBids() {
        return bids;
    }

    public String toString() {
        return super.toString() + " - " + this.getBids();
    }
}

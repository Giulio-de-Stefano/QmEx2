package ebay.results;

import org.openqa.selenium.WebElement;

import static ebay.EbayElements.RESULT_ITEM_PRICE;
import static ebay.EbayElements.RESULT_ITEM_TITLE;

public class SearchResult {
    private final String title;
    private final String price;

    public SearchResult(WebElement webElement) {
        this.title = webElement.findElement(RESULT_ITEM_TITLE).getText();
        this.price = webElement.findElement(RESULT_ITEM_PRICE).getText();
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String toString() {
        return this.getTitle() + " - " + this.getPrice();
    }
}

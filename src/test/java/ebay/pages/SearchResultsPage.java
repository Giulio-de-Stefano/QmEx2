package ebay.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage {

    @FindBy(id = "ListViewInner")
    WebElement resultList;

    @FindBy(id = "DashSortByContainer")
    WebElement filterMenu;

    @FindBy(css = ".tgl_button.center_b")
    WebElement auctionFilterBtn;

    @FindBy(xpath = "//*[text()='Lowest price + P&P']")
    WebElement lowestPriceIncludingPPListElement;

    @FindBy(className = "sresult")
    WebElement resultItems;

    @FindBy(className = "lvprice")
    WebElement resultItemPrice;

    @FindBy(className = "vip")
    WebElement resultItemTitle;

    @FindBy(className = "lvformat")
    WebElement resultItemBids;

}

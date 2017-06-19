package ebay;

import org.openqa.selenium.By;

public class EbayElements {
    public static final By.ByClassName SEARCH_INPUT = new By.ByClassName("ui-autocomplete-input");
    public static final By.ById SEARCH_BTN = new By.ById("gh-btn");
    public static final By.ById RESULT_LIST = new By.ById("ListViewInner");

    // filters
    public static final By.ById FILTER_MENU = new By.ById("DashSortByContainer");
    public static final By.ByCssSelector AUCTION_FILTER_BTN = new By.ByCssSelector(".tgl_button.center_b");
    public static final By.ByXPath LOWEST_PRICE_INCL_PP_LI = new By.ByXPath("//*[text()='Lowest price + P&P']");

    // results properties
    public static final By.ByClassName RESULT_ITEM = new By.ByClassName("sresult");
    public static final By.ByClassName RESULT_ITEM_PRICE = new By.ByClassName("lvprice");
    public static final By.ByClassName RESULT_ITEM_TITLE = new By.ByClassName("vip");
    public static final By.ByClassName RESULT_ITEM_BIDS = new By.ByClassName("lvformat");
}

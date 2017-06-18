package ebay;

import org.openqa.selenium.By;

public class EbayElements {
    public static final By.ByClassName SEARCH_INPUT = new By.ByClassName("ui-autocomplete-input");
    public static final By.ById SEARCH_BTN = new By.ById("gh-btn");
    public static final By.ById RESULT_LIST = new By.ById("ListViewInner");

    public static final By.ByClassName RESULT_ITEM = new By.ByClassName("sresult");
    public static final By.ByClassName RESULT_ITEM_PRICE = new By.ByClassName("lvprice");
    public static final By.ByClassName RESULT_ITEM_TITLE = new By.ByClassName("vip");
}

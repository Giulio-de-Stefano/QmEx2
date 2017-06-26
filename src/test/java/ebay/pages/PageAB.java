package ebay.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageAB {
    WebDriver driver;

    public PageAB(WebDriver driver) {
        this.driver = driver;
    }

    public PageAB init() {
        return PageFactory.initElements(driver, this.getClass());
    }
}

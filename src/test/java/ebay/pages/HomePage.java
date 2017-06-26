package ebay.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(className = "ui-autocomplete-input")
    WebElement searchInput;

    @FindBy(id = "gh-btn")
    WebElement searchButton;

    public void search(String query) {
        searchInput.sendKeys(query);
        searchButton.click();
    }


}

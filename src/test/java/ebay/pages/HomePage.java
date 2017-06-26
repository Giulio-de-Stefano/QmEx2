package ebay.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    @FindBy(className = "ui-autocomplete-input")
    WebElement searchInput;

    @FindBy(id = "gh-btn")
    WebElement searchButton;
}

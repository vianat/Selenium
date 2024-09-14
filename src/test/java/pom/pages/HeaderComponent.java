package pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pom.base.BasePage;

public class HeaderComponent extends BasePage {
    @FindBy(how = How.CSS, using = "#menu-item-1227 a") private WebElement storeMenuLink;


    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public StorePage clickStoreMenuLink() throws InterruptedException {
        Thread.sleep(500);
        WebElement e = waitForElementToBeClickable(waitForElementToBeVisible(storeMenuLink));
        e.click();
        return new StorePage(driver);
    }
}

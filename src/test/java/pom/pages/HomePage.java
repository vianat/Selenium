package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pom.base.BasePage;

public class HomePage extends BasePage {

    @FindBy(how = How.CSS, using = "#menu-item-1227 a") private WebElement storeMenuLink;
    @FindBy(how = How.CSS, using = "a[title='View cart']") private WebElement viewCart;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public StorePage clickStoreMenuLink() throws InterruptedException {
        Thread.sleep(500);
        WebElement e = waitForElementToBeClickable(waitForElementToBeVisible(storeMenuLink));
        e.click();
        return new StorePage(driver);
    }
    public HomePage load(){
        load("/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }
    public HomePage clickAddToCartBtn(String product){
        WebElement e = waitForElementToBeClickable(getAddToCartElement(product));
        e.click();
        return this;
    }
    private By getAddToCartElement(String productName){
        return By.cssSelector("a[aria-label='Add “"+ productName +"” to your cart']");
    }
    public CartPage clickViewCart(){
        WebElement e = waitForElementToBeClickable(viewCart);
        e.click();
        return new CartPage(driver);
    }
}
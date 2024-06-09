package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import pom.base.BasePage;

public class StorePage extends BasePage {

    private final By searchField = By.id("woocommerce-product-search-field-0");
    private final By searchButton = By.cssSelector("button[value='Search']");
    private final By title = By.xpath("//h1[contains(text(),'Search results: “blue”')]");
    private final By viewCart = By.cssSelector("a[title='View cart']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.urlContains("/store"));
    }
    public void enterTextInSearchField(String text){
        WebElement e = waitForElementToBeVisible(searchField);
        e.sendKeys(text);
    }
    public void clickSearchBtn(){
        WebElement e = waitForElementToBeClickable(searchButton);
        e.click();
    }
    public CartPage clickViewCart(){
        WebElement e = waitForElementToBeClickable(viewCart);
        e.click();
        return new CartPage(driver);
    }
    public String getTitle(){
        WebElement e = waitForElementToBeVisible(title);
        return e.getText();
    }
    public void clickAddToCartBtn(String text){
        WebElement e = waitForElementToBeClickable(getAddToCartElement(text));
        e.click();
    }
    private By getAddToCartElement(String productName){
        return By.cssSelector("a[aria-label='Add “"+ productName +"” to your cart']");
    }

    public StorePage search(String text){
        enterTextInSearchField(text);
        clickSearchBtn();
        return this;
    }
}
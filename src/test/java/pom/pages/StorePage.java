package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pom.base.BasePage;

public class StorePage extends BasePage {

    private final By searchField = By.id("woocommerce-product-search-field-0");
    private final By searchButton = By.cssSelector("button[value='Search']");
    private final By title = By.xpath("//h1[contains(text(),'Search results: “blue”')]");
    private final By addToCartBtn = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']");

    public StorePage(WebDriver driver) {
        super(driver);
    }
    public void enterTextInSearchField(String text){
        driver.findElement(searchField).sendKeys(text);
    }
    public void clickSearchBtn(){
        driver.findElement(searchButton).click();
    }
    public String getTitle(){
        return driver.findElement(title).getText();
    }
    public void clickAddToCartBtn(String text){
        driver.findElement(getAddToCartElement(text)).click();
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

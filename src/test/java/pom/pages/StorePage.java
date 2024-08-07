package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pom.base.BasePage;

public class StorePage extends BasePage {

//    private final By searchField = By.id("woocommerce-product-search-field-0");
    @FindBy(how = How.ID, using = "woocommerce-product-search-field-0") private WebElement searchField;
//    private final By searchButton = By.cssSelector("button[value='Search']");
    @FindBy(how = How.CSS, using = "button[value='Search']") private WebElement searchButton;
//    private final By title = By.xpath("//h1[contains(text(),'Search results: “blue”')]");
    @FindBy(how = How.XPATH, using = "//h1[contains(text(),'Search results: “blue”')]") private WebElement title;
//    private final By viewCart = By.cssSelector("a[title='View cart']");
    @FindBy(how = How.CSS, using = "a[title='View cart']") private WebElement viewCart;

    public StorePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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
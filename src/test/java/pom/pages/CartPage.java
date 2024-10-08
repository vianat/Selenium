package pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pom.base.BasePage;

public class CartPage extends BasePage {

//    private final By productName = By.xpath("//a[normalize-space()='Blue Shoes']");
//    private final By checkoutBtn = By.xpath("//a[normalize-space()='Proceed to checkout']");
//    private final By pageHeader = By.cssSelector(".has-text-align-center");

    @FindBy(css = "td[class='product-name'] a") private WebElement productName;
    @FindBy(xpath = "//a[normalize-space()='Proceed to checkout']") private WebElement checkoutBtn;
    @FindBy(css = ".has-text-align-center") private WebElement pageHeader;

    private ProductComponent productComponent;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        productComponent = new ProductComponent(driver);
    }
    public CartPage load(){
        load("/cart");
        return this;
    }
    public boolean isLoaded() {
//        u can set the slowest element on the page and wait it. that means page fully loaded
        return wait.until(ExpectedConditions.textToBePresentInElement(pageHeader, "Cart"));
    }

    public String getProductName(){
        WebElement e = waitForElementToBeVisible(productName);
        return e.getText();
    }

    public CheckoutPage clickCheckoutBtn(){
        WebElement e = waitForElementToBeClickable(checkoutBtn);
        e.click();
        return new CheckoutPage(driver);
    }
}
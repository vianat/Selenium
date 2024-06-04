package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pom.base.BasePage;

public class CartPage extends BasePage {

    private final By productName = By.xpath("//a[normalize-space()='Blue Shoes']");
    private final By checkoutBtn = By.xpath("//a[normalize-space()='Proceed to checkout']");

    public String getProductName(){
        return driver.findElement(productName).getText();
    }
    public CheckoutPage clickCheckoutBtn(){
        driver.findElement(checkoutBtn).click();
        return new CheckoutPage(driver);
    }
    public CartPage(WebDriver driver) {
        super(driver);
    }

}

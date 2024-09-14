package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pom.base.BasePage;

public class ProductComponent extends BasePage {

    @FindBy(how = How.CSS, using = "a[title='View cart']") private WebElement viewCart;

    public ProductComponent(WebDriver driver) {
        super(driver);
    }

    public ProductComponent clickAddToCartBtn(String product){
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

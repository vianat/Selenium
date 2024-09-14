package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Parameters;
import pom.base.BasePage;

public class StorePage extends BasePage {

//    private final By searchField = By.id("woocommerce-product-search-field-0");
    @FindBy(how = How.ID, using = "woocommerce-product-search-field-0") private WebElement searchField;
//    private final By searchButton = By.cssSelector("button[value='Search']");
    @FindBy(how = How.CSS, using = "button[value='Search']") private WebElement searchButton;
//    private final By title = By.xpath("//h1[contains(text(),'Search results: “blue”')]");
    @FindBy(how = How.XPATH, using = "//h1") private WebElement title;
//    private final By viewCart = By.cssSelector("a[title='View cart']");

    private HeaderComponent headerComponent;
    private ProductComponent productComponent;

    public StorePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        headerComponent = new HeaderComponent(driver);
        productComponent = new ProductComponent(driver);
    }
    public StorePage load(){
        load("/store");
        return this;
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

    public String getTitle(){
        WebElement e = waitForElementToBeVisible(title);
        return e.getText();
    }

    public StorePage search(String text){
        enterTextInSearchField(text);
        clickSearchBtn();
        return this;
    }

    public ProductComponent getProductComponent() {
        return productComponent;
    }

    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }
}
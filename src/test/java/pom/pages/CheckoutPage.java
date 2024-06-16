package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pom.base.BasePage;
import pom.objects.BillingAddress;
import pom.objects.User;

public class CheckoutPage extends BasePage {

    private final By firstNameField = By.id("billing_first_name");
    private final By lastNameField = By.id("billing_last_name");
    private final By cityField = By.id("billing_city");
    private final By countryDropDown = By.id("billing_country");
    private final By stateDropDown = By.id("billing_state");
    private final By zipField = By.id("billing_postcode");
    private final By emailField = By.id("billing_email");
    private final By notice = By.tagName("h1");
    private final By placeOrderBtn = By.id("place_order");
    public  final By showLogin = By.cssSelector(".showlogin");
    private final By userNameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginBtn = By.cssSelector("button[value='Login']");
    private final By overlay = By.cssSelector(".blockUI .blockOverlay");
    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");



    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private CheckoutPage enterUserName(String name) {
        WebElement e = waitForElementToBeVisible(userNameField);
        e.clear();
        e.sendKeys(name);
        return this;
    }
    private CheckoutPage enterPassword(String pass) {
        WebElement e = waitForElementToBeVisible(passwordField);
        e.clear();
        e.sendKeys(pass);
        return this;
    }
    private CheckoutPage clickLoginBtn() {
        WebElement e = waitForElementToBeVisible(loginBtn);
        e.click();
        return this;
    }

    private CheckoutPage enterFirstname(String name) {
        WebElement e = waitForElementToBeVisible(firstNameField);
        e.clear();
        e.sendKeys(name);
        return this;
    }
    private CheckoutPage enterLastname(String lName) {
        WebElement e = waitForElementToBeVisible(lastNameField);
        e.clear();
        e.sendKeys(lName);
        return this;
    }
    private CheckoutPage enterCity(String city) {
        WebElement e = waitForElementToBeVisible(cityField);
        e.clear();
        e.sendKeys(city);
        return this;
    }
    private CheckoutPage selectCountry(String countryName) {
        WebElement e = waitForElementToBeVisible(countryDropDown);
        Select select = new Select(driver.findElement(countryDropDown));
        select.selectByVisibleText(countryName);
        return this;
    }
    private CheckoutPage selectState(String stateName) {
        WebElement e = waitForElementToBeVisible(stateDropDown);
        Select select = new Select(driver.findElement(stateDropDown));
        select.selectByVisibleText(stateName);
        return this;
    }
    private CheckoutPage enterZIP(String zip) {
        WebElement e = waitForElementToBeVisible(zipField);
        e.clear();
        e.sendKeys(zip);
        return this;
    }
    private CheckoutPage enterEmail(String email) {
        WebElement e = waitForElementToBeVisible(emailField);
        e.clear();
        e.sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        return enterFirstname(billingAddress.getFirstName())
                .enterLastname(billingAddress.getLastName())
                .selectCountry(billingAddress.getCountry())
                .selectState(billingAddress.getState())
                .enterCity(billingAddress.getCity())
                .enterZIP(billingAddress.getZip())
                .enterEmail(billingAddress.getEmail());
    }
    public CheckoutPage placeOrder() throws InterruptedException {
        Thread.sleep(200);
        waitForOverlaysToDisappear(overlay);
        waitForElementToBeClickable(placeOrderBtn).click();
        return this;
    }

    public CheckoutPage login(User user) {
        enterUserName(user.getUserName());
        enterPassword(user.getPassword());
        clickLoginBtn();
        return this;
    }
    public String getNoticeText() {
        WebElement e = waitForElementToBeVisible(notice);
        return e.getText();
    }

    public CheckoutPage selectDirectBankTransfer(){
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if(!el.isSelected()){
            el.click();
        }
        return  this;
    }
}
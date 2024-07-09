package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pom.base.BasePage;
import pom.objects.BillingAddress;
import pom.objects.User;

public class CheckoutPage extends BasePage {

//    private final By firstNameField = By.id("billing_first_name");
    @FindBy(how = How.ID, using = "billing_first_name") private WebElement firstNameField;
//    private final By lastNameField = By.id("billing_last_name");
    @FindBy(how = How.ID, using = "billing_last_name") private WebElement lastNameField;
//    private final By cityField = By.id("billing_city");
    @FindBy(how = How.ID, using = "billing_city") private WebElement cityField;
//    private final By countryDropDown = By.id("billing_country");
    @FindBy(how = How.ID, using = "billing_country") private WebElement countryDropDown;
//    private final By stateDropDown = By.id("billing_state");
    @FindBy(how = How.ID, using = "billing_state") private WebElement stateDropDown;
//    private final By zipField = By.id("billing_postcode");
    @FindBy(how = How.ID, using = "billing_postcode") private WebElement zipField;
//    private final By emailField = By.id("billing_email");
    @FindBy(how = How.ID, using = "billing_email") private WebElement emailField;
//    private final By notice = By.tagName("h1");
    @FindBy(how = How.TAG_NAME, using = "h1") private WebElement notice;
//    private final By placeOrderBtn = By.id("place_order");
    @FindBy(how = How.ID, using = "place_order") private WebElement placeOrderBtn;
//    public  final By showLogin = By.cssSelector(".showlogin");
    @FindBy(how = How.CSS, using = ".showlogin") public WebElement showLogin;
//    private final By userNameField = By.id("username");
    @FindBy(how = How.ID, using = "username") private WebElement userNameField;
//    private final By passwordField = By.id("password");
    @FindBy(how = How.ID, using = "password") private WebElement passwordField;
//    private final By loginBtn = By.cssSelector("button[value='Login']");
    @FindBy(how = How.CSS, using = "button[value='Login']") private WebElement loginBtn;
    private final By overlay = By.cssSelector(".blockUI .blockOverlay");
//    @FindBy(how = How.CSS, using = ".blockUI .blockOverlay") private WebElement overlay;
//    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");
    @FindBy(how = How.ID, using = "payment_method_bacs")@CacheLookup
    private WebElement directBankTransferRadioBtn;
    private final By alternativeDropDown = By.id("select2-billing_country-container");
    private final By alternativeStateDropDown = By.id("select2-billing_state-container");



    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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
//        through Select, chrome -> ok
//        WebElement e = waitForElementToBeVisible(countryDropDown);
//        Select select = new Select(e);
//        select.selectByVisibleText(countryName);

//        Firefox hide dropdown list use this ->
         wait.until(ExpectedConditions.elementToBeClickable(alternativeDropDown)).click();
         WebElement e = waitForElementToBeClickable(By.xpath("//li[text()='" + countryName + "']"));
         ((JavascriptExecutor) driver). executeScript("arguments[0].scrollIntoView(true);", e);
         e.click();

        return this;
    }
    private CheckoutPage selectState(String stateName) {
//        for chrome -> use Select, ff -> x
//        WebElement e = waitForElementToBeVisible(stateDropDown);
//        Select select = new Select(e);
//        select.selectByVisibleText(stateName);

//        for Firefox (hidden dropdown list) use scroll ->
        wait.until(ExpectedConditions.elementToBeClickable(alternativeStateDropDown)).click();
        WebElement e = waitForElementToBeClickable(By.xpath("//li[text()='" + stateName + "']"));
        ((JavascriptExecutor) driver). executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();

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
        Thread.sleep(700);
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
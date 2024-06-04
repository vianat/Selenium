package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pom.base.BasePage;
import pom.objects.BillingAddress;
import pom.objects.User;

public class CheckoutPage extends BasePage {

    private final By firstNameField = By.id("billing_first_name");
    private final By lastNameField = By.id("billing_last_name");
    private final By cityField = By.id("billing_city");
    private final By zipField = By.id("billing_postcode");
    private final By emailField = By.id("billing_email");
    private final By notice = By.tagName("h1");
    private final By placeOrderBtn = By.xpath("//button[@id='place_order']");
    public  final By showLogin = By.cssSelector(".showlogin");
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginBtn = By.cssSelector("button[value='Login']");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private CheckoutPage enterUserName(String name) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(name);
        return this;
    }
    private CheckoutPage enterPassword(String pass) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(pass);
        return this;
    }
    private CheckoutPage clickLoginBtn() {
        driver.findElement(loginBtn).click();
        return this;
    }

    private CheckoutPage enterFirstname(String name) {
        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(name);
        return this;
    }
    private CheckoutPage enterLastname(String lName) {
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lName);
        return this;
    }
    private CheckoutPage enterCity(String city) {
        driver.findElement(cityField).clear();
        driver.findElement(cityField).sendKeys(city);
        return this;
    }
    private CheckoutPage enterZIP(String zip) {
        driver.findElement(zipField).clear();
        driver.findElement(zipField).sendKeys(zip);
        return this;
    }
    private CheckoutPage enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        return enterFirstname(billingAddress.getFirstName())
                .enterLastname(billingAddress.getLastName())
                .enterCity(billingAddress.getCity())
                .enterZIP(billingAddress.getZip())
                .enterEmail(billingAddress.getEmail());
    }
    public CheckoutPage placeOrder() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public CheckoutPage login(User user) {
        enterUserName(user.getUserName());
        enterPassword(user.getPassword());
        clickLoginBtn();
        return this;
    }
    public String getNoticeText() {
        return driver.findElement(notice).getText();
    }
}

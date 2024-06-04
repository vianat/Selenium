import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.base.BaseTest;
import pom.objects.BillingAddress;
import pom.pages.CartPage;
import pom.pages.CheckoutPage;
import pom.pages.HomePage;
import pom.pages.StorePage;

public class MainTest extends BaseTest {



    @Test
    public  void e2e() throws InterruptedException {

        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setFirstName("myName");
        billingAddress.setLastName("myLastName");
        billingAddress.setCity("myCity");
        billingAddress.setZip("47373");
        billingAddress.setEmail("thisisme@gmail.com");

        StorePage storePage = new HomePage(driver)
                .load()
                .clickStoreMenuLink()
                .search("blue");

        Assert.assertEquals(storePage.getTitle(),"Search results: “blue”");

        storePage.clickAddToCartBtn("Blue Shoes");

        Thread.sleep(1500);

        CartPage cartPage = storePage.clickViewCart();

        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

        CheckoutPage checkoutPage = cartPage.
                clickCheckoutBtn().
                setBillingAddress(billingAddress).
                placeOrder();

        Thread.sleep(1500);

        Assert.assertEquals(checkoutPage.getNoticeText(), "Checkout");

    }

    @Test
    public  void e2eWithLogin() throws InterruptedException {

        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setFirstName("myName");
        billingAddress.setLastName("myLastName");
        billingAddress.setCity("myCity");
        billingAddress.setZip("47373");
        billingAddress.setEmail("thisisme@gmail.com");

        HomePage hp = new HomePage(driver);

        StorePage sp = hp.clickStoreMenuLink();

        sp.enterTextInSearchField("blue");
        sp.clickSearchBtn();

        Assert.assertEquals(sp.getTitle(),"Search results: “blue”");

        sp.clickAddToCartBtn("Blue Shoes");

        Thread.sleep(1500);

        CartPage cartPage = sp.clickViewCart();

        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

        CheckoutPage checkoutPage = cartPage.clickCheckoutBtn();

        driver.findElement(checkoutPage.showLogin).click();
        Thread.sleep(1000);

        checkoutPage.login("Fufel","TsqvCmNjwGde2tm");

        checkoutPage.setBillingAddress(billingAddress).placeOrder();

        Assert.assertEquals(checkoutPage.getNoticeText(), "Checkout");

    }
}
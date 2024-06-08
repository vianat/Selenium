import org.testng.Assert;
import org.testng.annotations.Test;
import pom.base.BaseTest;
import pom.objects.BillingAddress;
import pom.objects.Product;
import pom.objects.User;
import pom.pages.CartPage;
import pom.pages.CheckoutPage;
import pom.pages.HomePage;
import pom.pages.StorePage;
import utils.JacksonUtils;

import java.io.IOException;

public class MainTest extends BaseTest {



    @Test
    public  void e2e() throws InterruptedException, IOException {
        String searchingFor = "blue";
        Product product = new Product(1215);
//v1
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAdress.json", BillingAddress.class);

//v2    BillingAddress billingAddress = new BillingAddress("myName","myLastName","myCity","47373","thisisme@gmail.com");


        StorePage storePage = new HomePage(driver)
                .load()
                .clickStoreMenuLink()
                .search(searchingFor);

        Assert.assertEquals(storePage.getTitle(),"Search results: “"+searchingFor+"”");

        storePage.clickAddToCartBtn(product.getName());

        CartPage cartPage = storePage.clickViewCart();

        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.
                clickCheckoutBtn().
                setBillingAddress(billingAddress).
                placeOrder();

        Assert.assertEquals(checkoutPage.getNoticeText(), "Checkout");

    }

    @Test
    public  void e2eWithLogin() throws InterruptedException, IOException {

        String searchingFor = "blue";
        Product product = new Product(1215);
        User user = new User("Fufel", "TsqvCmNjwGde2tm");
//v1
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAdress.json", BillingAddress.class);

//v2    BillingAddress billingAddress = new BillingAddress("myName","myLastName","myCity","47373","thisisme@gmail.com");

        HomePage hp = new HomePage(driver);

        StorePage sp = hp.clickStoreMenuLink();

        sp.enterTextInSearchField(searchingFor);
        sp.clickSearchBtn();

        Assert.assertEquals(sp.getTitle(),"Search results: “"+searchingFor+"”");

        sp.clickAddToCartBtn(product.getName());

        Thread.sleep(1500);

        CartPage cartPage = sp.clickViewCart();

        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.clickCheckoutBtn();

        driver.findElement(checkoutPage.showLogin).click();
        Thread.sleep(1000);

        checkoutPage.login(user);

        checkoutPage.setBillingAddress(billingAddress).placeOrder();

        Assert.assertEquals(checkoutPage.getNoticeText(), "Checkout");

    }
}
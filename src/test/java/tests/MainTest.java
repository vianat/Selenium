package tests;

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
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
//v2
//      BillingAddress billingAddress = new BillingAddress("myName","myLastName","myCity","47373","thisisme@gmail.com", "India", "Maharashtra");


        StorePage storePage = new HomePage(getDriver())
                .load()
                .clickStoreMenuLink()
                .search(searchingFor);

        Assert.assertEquals(storePage.getTitle(),"Search results: “" + searchingFor + "”");

        storePage.clickAddToCartBtn(product.getName());

        CartPage cartPage = storePage.clickViewCart();

        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage
                .clickCheckoutBtn()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();

        Assert.assertEquals(checkoutPage.getNoticeText(), "Checkout");

    }

    @Test
    public  void e2eWithLogin() throws InterruptedException, IOException {

        String searchingFor = "blue";
        Product product = new Product(1215);
        User user = new User("Fufel", "TsqvCmNjwGde2tm");
//v1
//        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
//v2
        BillingAddress billingAddress = new BillingAddress("myName","myLastName","myCity","47373","thisisme@gmail.com", "India", "Maharashtra");

        HomePage hp = new HomePage(getDriver());

        StorePage sp = hp.clickStoreMenuLink();

        sp.enterTextInSearchField(searchingFor);
        sp.clickSearchBtn();

        Assert.assertEquals(sp.getTitle(),"Search results: “"+searchingFor+"”");

        sp.clickAddToCartBtn(product.getName());

        Thread.sleep(1500);

        CartPage cartPage = sp.clickViewCart();

        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.clickCheckoutBtn();

        checkoutPage.showLogin.click();
        Thread.sleep(1000);

        checkoutPage.login(user);

        checkoutPage
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();

        Assert.assertEquals(checkoutPage.getNoticeText(), "Checkout");

    }



    @Test
    public  void e2e2() throws InterruptedException, IOException {
        e2e();
    }

//    @Test
//    public  void e2eWithLogin2() throws InterruptedException, IOException {
//        e2eWithLogin();
//    }
//
//    @Test
//    public  void e2e3() throws InterruptedException, IOException {
//        e2e();
//    }
//
//    @Test
//    public  void e2eWithLogin3() throws InterruptedException, IOException {
//        e2eWithLogin();
//    }
//
//    @Test
//    public  void e2e4() throws InterruptedException, IOException {
//        e2e();
//    }
//
//    @Test
//    public  void e2eWithLogin4() throws InterruptedException, IOException {
//        e2eWithLogin();
//    }
//
//    @Test
//    public  void e2e5() throws InterruptedException, IOException {
//        e2e();
//    }
//
//    @Test
//    public  void e2eWithLogin5() throws InterruptedException, IOException {
//        e2eWithLogin();
//    }

}

// 1 - 5.22
// 2 - 2.53
// 3 - 2.20
// 4 - 2.36
// 5 - 1.58
// 6 - 1.58
// 7 - 2.05
// 10 - 2.05
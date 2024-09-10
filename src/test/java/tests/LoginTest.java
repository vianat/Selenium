package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pom.api.actions.CartApi;
import pom.api.actions.SignUpApi;
import pom.base.BaseTest;
import pom.objects.Product;
import pom.objects.User;
import pom.pages.CheckoutPage;
import utils.FakerUtils;

import java.io.IOException;

public class LoginTest extends BaseTest {
    @Test
    public void loginDuringCheckout() throws IOException, InterruptedException {
        String userName = "bratok" + new FakerUtils().generateRandomNumbers();
        User user = new User()
                .setUserName(userName)
                .setPassword("12345")
                .setEmail(userName + "@gmail.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        CartApi cartApi = new CartApi();
        Product product = new Product(1215);

        cartApi.addToCart(product.getId(), 1);

        injectCookiesToBrowser(cartApi.getCookies());

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(3000);
        checkoutPage.login(user);

        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
    }
}
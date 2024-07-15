package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pom.base.BaseTest;
import pom.objects.Product;
import pom.pages.CartPage;
import pom.pages.StorePage;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);

        CartPage cp = new StorePage(getDriver())
                .load()
                .clickAddToCartBtn(product.getName())
                .clickViewCart();

        Assert.assertEquals(cp.getProductName(), product.getName());
    }
}

package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pom.base.BaseTest;
import pom.dataProviders.MyDataProvider;
import pom.objects.Product;
import pom.pages.CartPage;
import pom.pages.HomePage;
import pom.pages.StorePage;
import utils.JacksonUtils;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);

        CartPage cp = new StorePage(getDriver())
                .load()
                .getProductComponent()
                .clickAddToCartBtn(product.getName())
                .clickViewCart();

        Assert.assertEquals(cp.getProductName(), product.getName());
    }

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class) // use dataProvider = "getFeaturedProducts" for this test
    public void addToCartFeaturedProducts(Product product){

        CartPage cp = new HomePage(getDriver())
                .load()
                .getProductComponent()
                .clickAddToCartBtn(product.getName())
                .clickViewCart();

        Assert.assertEquals(cp.getProductName(), product.getName());
    }

}
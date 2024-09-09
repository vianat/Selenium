package pom.dataProviders;

import org.testng.annotations.DataProvider;
import pom.objects.Product;
import utils.JacksonUtils;

import java.io.IOException;

public class MyDataProvider {
    @DataProvider(name = "getFeaturedProducts", parallel = true)
    public Product[] getFeaturedProducts() throws IOException {
        return JacksonUtils.deserializeJson("products.json", Product[].class);
    }
}
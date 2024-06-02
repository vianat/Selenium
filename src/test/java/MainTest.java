import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.base.BaseTest;
import pom.pages.HomePage;
import pom.pages.StorePage;

public class MainTest extends BaseTest {

    @Test
    public  void e2e() throws InterruptedException {

        HomePage hp = new HomePage(driver);

        StorePage sp = hp.clickStoreMenuLink();

        sp.enterTextInSearchField("blue");
        sp.clickSearchBtn();

        Assert.assertEquals(sp.getTitle(),"Search results: “blue”");

        sp.clickAddToCartBtn("Blue Shoes");

        Thread.sleep(1500);
        driver.findElement(By.cssSelector("a[title='View cart']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//a[normalize-space()='Blue Shoes']")).getText(),
                "Blue Shoes");

        driver.findElement(By.xpath("//a[normalize-space()='Proceed to checkout']")).click();

        driver.findElement(By.xpath("//input[@id='billing_first_name']")).sendKeys("Fufel");
        driver.findElement(By.xpath("//input[@id='billing_last_name']")).sendKeys("Fufelov");
        driver.findElement(By.xpath("//input[@id='billing_city']")).sendKeys("Fufelburg");
        driver.findElement(By.xpath("//input[@id='billing_postcode']")).sendKeys("47373");
        driver.findElement(By.xpath("//input[@id='billing_email']")).sendKeys("Fufel@gmail.com");

        Thread.sleep(1500);

        driver.findElement(By.id("place_order")).click();

        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(),
                "Checkout");

    }

    @Test
    public  void e2eWithLogin() throws InterruptedException {

        driver.findElement(By.cssSelector("#menu-item-1227 a")).click();

        driver.findElement(By.cssSelector("#woocommerce-product-search-field-0")).sendKeys("blue");

        driver.findElement(By.cssSelector("button[value='Search']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(text(),'Search results: “blue”')]")).getText(),
                "Search results: “blue”");

        driver.findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();

        Thread.sleep(1000);
        driver.findElement(By.cssSelector("a[title='View cart']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//a[normalize-space()='Blue Shoes']")).getText(),
                "Blue Shoes");

        driver.findElement(By.xpath("//a[normalize-space()='Proceed to checkout']")).click();

        driver.findElement(By.cssSelector(".showlogin")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("username")).sendKeys("fufel");
        driver.findElement(By.id("password")).sendKeys("TsqvCmNjwGde2tm");
        driver.findElement(By.cssSelector("button[value='Login']")).click();

        driver.findElement(By.xpath("//input[@id='billing_first_name']")).sendKeys("Fufel");
        driver.findElement(By.xpath("//input[@id='billing_last_name']")).sendKeys("Fufelov");
        driver.findElement(By.xpath("//input[@id='billing_city']")).sendKeys("Fufelburg");
        driver.findElement(By.xpath("//input[@id='billing_postcode']")).sendKeys("47373");
        driver.findElement(By.xpath("//input[@id='billing_email']")).sendKeys("Fufel@gmail.com");

        Thread.sleep(1000);

        driver.findElement(By.id("place_order")).click();
        Thread.sleep(1000);

        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(),
                "Checkout");

    }
}
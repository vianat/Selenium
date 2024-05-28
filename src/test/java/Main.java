import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Main {

    @Test
    public  void e2e() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://askomdch.com");
        driver.manage().window().maximize();

        driver.findElement(By.cssSelector("#menu-item-1227 a")).click();

        driver.findElement(By.cssSelector("#woocommerce-product-search-field-0")).sendKeys("blue");

        driver.findElement(By.cssSelector("button[value='Search']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(text(),'Search results: “blue”')]")).getText(),
                "Search results: “blue”");

        driver.findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();

        Thread.sleep(1500);
        driver.findElement(By.cssSelector("a[title='View cart']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//a[normalize-space()='Blue Shoes']")).getText(),
                "Blue Shoes");

        driver.findElement(By.xpath("//a[normalize-space()='Proceed to checkout']")).click();

        driver.findElement(By.xpath("//input[@id='billing_first_name']")).sendKeys("Fufel");
        driver.findElement(By.xpath("//input[@id='billing_last_name']")).sendKeys("Fufelov");
        driver.findElement(By.xpath("//input[@id='billing_city']")).sendKeys("Fufel town");
        driver.findElement(By.xpath("//input[@id='billing_postcode']")).sendKeys("47373");
        driver.findElement(By.xpath("//input[@id='billing_email']")).sendKeys("47373@gmail.com");

        Thread.sleep(1500);

        driver.findElement(By.id("place_order")).click();

        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(),
                "Checkout");

        driver.close();
    }
}
package pom.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pom.factory.DriverManager;
import utils.ConfigLoader;

public class BaseTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver){
        this.driver.set(driver);
    }
    protected WebDriver getDriver(){
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(String browser){
        if(browser == null) browser = "CHROME";

        setDriver(new DriverManager().initializeDriver(browser));
        getDriver().get(ConfigLoader.getInstance().getBaseUrl());
//        System.out.println("Current thread id -> " + Thread.currentThread().getId() + " and Driver -> " + getDriver());
    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
//        Thread.sleep(100);
//        System.out.println("Close thread id -> " + Thread.currentThread().getId() + " " + " and Driver -> " + getDriver());
        getDriver().quit();
    }
}
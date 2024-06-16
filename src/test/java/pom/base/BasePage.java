package pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    public void load(String endpoint){
        driver.get("https://askomdch.com" + endpoint);
    }

    public void waitForOverlaysToDisappear(By overlay){

        List<WebElement> overlays = driver.findElements(overlay);
//        System.out.println("overlay size " + overlays.size());

        if (overlays.size() > 0){
            wait.until(ExpectedConditions.visibilityOfAllElements(overlays));
//            System.out.println("no overlay");
        } else {
//            System.out.println("overlay not found");
        }
    }

    public WebElement waitForElementToBeVisible(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public WebElement waitForElementToBeClickable(By element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
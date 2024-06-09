package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pom.base.BasePage;

public class HomePage extends BasePage {

    private final By storeMenuLink = By.cssSelector("#menu-item-1227 a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public StorePage clickStoreMenuLink(){
        WebElement e = waitForElementToBeClickable(storeMenuLink);
        e.click();
        return new StorePage(driver);
    }
    public HomePage load(){
        load("/");
        wait.until(ExpectedConditions.titleContains("AskODch"));
        return this;
    }
}
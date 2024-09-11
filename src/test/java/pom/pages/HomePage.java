package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pom.base.BasePage;

public class HomePage extends BasePage {

    private HeaderComponent headerComponent;
    private ProductComponent productComponent;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        headerComponent = new HeaderComponent(driver);
        productComponent = new ProductComponent(driver);
    }

    public ProductComponent getProductComponent() {
        return productComponent;
    }

    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }

    public HomePage load(){
        load("/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }

}
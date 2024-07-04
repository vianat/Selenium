package pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pom.constants.DriverType;

import java.time.Duration;

public class DriverManager {

    public WebDriver initializeDriver(String browser) {


// for mvn
        browser = System.getProperty("browser", browser);     // use mvn [-Dbrowser] first , and xml by default

// for xml
//        browserName = browser;                             // for xml using, get browser name from testng xml

        WebDriver driver = switch (DriverType.valueOf(browser.toUpperCase())) {
            case CHROME -> {
                WebDriverManager.chromedriver().cachePath("Drivers").setup();
                yield new ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
                yield new FirefoxDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().cachePath("Drivers").setup();
                yield new EdgeDriver();
            }
            default -> throw new IllegalStateException("************************ INVALID BROWSER NAME ************************ => " + browser);
        };

        driver.manage().window().maximize();

//        hard fix all waits
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }
}
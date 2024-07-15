package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pom.base.BaseTest;
import pom.pages.HomePage;
import pom.pages.StorePage;

public class NavigationTest extends BaseTest {

    @Test
    public void navigateFromHomeToStoreUsingMainMenu() throws InterruptedException {
        StorePage storePage = new HomePage(getDriver())
                .load()
                .clickStoreMenuLink();

        Assert.assertEquals(storePage.getTitle(),"Store");
    }
}

package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pom.base.BaseTest;
import pom.pages.StorePage;

public class SearchTest extends BaseTest {

    @Test
    public void searchWithPartialMatch() {

        String search = "blue";

        StorePage sp = new StorePage(getDriver())
                .load()
                .search(search);

        Assert.assertEquals(sp.getTitle(),"Search results: “" + search + "”");
    }
}
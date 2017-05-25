package com.example;

import com.example.pages.HomePage;
import com.example.pages.ItemSearchFilter;
import com.example.pages.ItemSearchPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class checks correct work of items filtration by a brand.
 * Snippet should contain a brand word.
 */
public class BrandFilterTest extends TestNgTestBase {
    private static final String SEARCH_INPUT = "jazz bass";
    private static final String BRAND_WORD = "fender";
    private HomePage homepage;
    private ItemSearchFilter itemSearchFilter;

    /**
     * For some strange reason implicitly wait does not work
     * on my retro laptop. That`s why I used Thread.sleep().
     * @throws InterruptedException
     */
    @BeforeMethod
    public void setUp() throws  InterruptedException {
        homepage = PageFactory.initElements(driver, HomePage.class);
        itemSearchFilter = PageFactory.initElements(driver, ItemSearchFilter.class);
        homepage.openPage(baseUrl);
        Thread.sleep(10000);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('gh-eb-Geo-a-en').click();");
        Thread.sleep(10000);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testSnippetContainsBrandWord() {
        ItemSearchPage itemSearchPage = homepage.search(SEARCH_INPUT);
        itemSearchFilter.fenderBrandCheckboxClick();
        List<String> titleList = itemSearchPage.getTitleList();
        for(String title: titleList) {
            Assert.assertTrue(title.toLowerCase().contains(BRAND_WORD));
        }
    }
}

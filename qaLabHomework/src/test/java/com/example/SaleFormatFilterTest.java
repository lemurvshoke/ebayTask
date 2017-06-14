package com.example;

import com.example.pages.HomePage;
import com.example.pages.ItemSearchFilter;
import com.example.pages.ItemSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This is a check for item sale format.
 * If snippet contains bid field  - sale format is an auction.
 */
public class SaleFormatFilterTest extends TestNgTestBase {
    private static final String SEARCH_INPUT = "jazz bass";
    private static final String KEY_WORD = "bid";
    private HomePage homepage;
    private ItemSearchFilter itemSearchFilter;

    @BeforeMethod
    public void setUp() throws  InterruptedException {
        homepage = PageFactory.initElements(driver, HomePage.class);
        itemSearchFilter = PageFactory.initElements(driver, ItemSearchFilter.class);
        homepage.openPage(baseUrl);
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(30000);
    }

    @Test
    public void testSnippetContainsAuction() {
        ItemSearchPage itemSearchPage = homepage.search(SEARCH_INPUT);
        itemSearchFilter.auctionRadiobuttonClick();
        List<String> saleFormatList = itemSearchPage.getSaleFormatList();
        for(String format: saleFormatList) {
            Assert.assertTrue(format.toLowerCase().contains(KEY_WORD));
        }
    }
}


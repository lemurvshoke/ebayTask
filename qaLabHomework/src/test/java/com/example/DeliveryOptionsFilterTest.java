package com.example;

import com.example.pages.HomePage;
import com.example.pages.ItemSearchFilter;
import com.example.pages.ItemSearchPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * This is a check for delivery options filtration.
 * There are should be 50 snippets per page
 * with free international shipping available.
 */
public class DeliveryOptionsFilterTest extends TestNgTestBase {
    private static final String SEARCH_INPUT = "jazz bass";
    private static final String EXPECTED_SHIPPING_TYPE = "free international shipping";
    private static final int EXPECTED_QUANTITY = 50;
    private HomePage homepage;
    private ItemSearchFilter itemSearchFilter;

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
    public void testSnippetContainsFreeInternationalShipping() throws InterruptedException {
        ItemSearchPage itemSearchPage = homepage.search(SEARCH_INPUT);
        itemSearchFilter.freeInternationalShippingCheckboxClick();
        Thread.sleep(10000);
        int FreeInternationalShippingItemsQuantity = itemSearchPage.getDeliveryOptionsCount(EXPECTED_SHIPPING_TYPE);
        Assert.assertEquals(FreeInternationalShippingItemsQuantity, EXPECTED_QUANTITY);
    }
}
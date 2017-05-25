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
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This is a check for price filtration.
 * Item price should be >= 45,000 RUB.
 */
public class PriceFilterTest extends TestNgTestBase {
    private static final String SEARCH_INPUT = "jazz bass";
    private static final String LOWER_PRICE_INPUT = "50000";
    private static final double EXPECTED_LOWER_PRICE = 50000;
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
    public void itemCardPageContainsExpectedPrice() throws InterruptedException {
        ItemSearchPage itemSearchPage = homepage.search(SEARCH_INPUT);
        Thread.sleep(5000);
        itemSearchFilter.submitLowerPrice(LOWER_PRICE_INPUT);
        Thread.sleep(10000);
        List<String> priceList = itemSearchPage.getPriceList();
        for (String price: priceList) {
            String priceRub = price.replaceAll( "[a-z,]", "");
            double priceDigit = Double.parseDouble(priceRub);
            Assert.assertTrue(priceDigit >= EXPECTED_LOWER_PRICE*0.9);
        }
    }
}

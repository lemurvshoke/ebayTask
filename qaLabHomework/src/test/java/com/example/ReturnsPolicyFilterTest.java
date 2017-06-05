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
 * This is a check for return policy option.
 */
public class ReturnsPolicyFilterTest extends TestNgTestBase {
    private static final String SEARCH_INPUT = "jazz bass";
    private static final String UNDESIRABLE_RETURN_POLICY_OPTION = "seller does not offer returns.";
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
    public void itemCardPageContainsPositiveReturnPolicy() {
        ItemSearchPage itemSearchPage = homepage.search(SEARCH_INPUT);
        itemSearchFilter.returnsAcceptedCheckboxClick();
        List<String> linkList = itemSearchPage.getLinkList();
        for (String link : linkList) {
            driver.get(link);
            WebElement itemPrice = driver.findElement(By.xpath(".//*[@id='vi-ret-accrd-txt']"));
            String returnPolicy = itemPrice.getText().toLowerCase();
            Assert.assertTrue(!returnPolicy.equals(UNDESIRABLE_RETURN_POLICY_OPTION));
        }
    }
}
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class checks item condition. For example, New.
 */
public class ConditionFilterTest extends TestNgTestBase {
    private static final String SEARCH_INPUT = "jazz bass";
    private static final String EXPECTED_CONDITION = "new";
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
    public void itemCardPageContainsNewCondition() {
        ItemSearchPage itemSearchPage = homepage.search(SEARCH_INPUT);
        itemSearchFilter.newConditionCheckboxClick();
        List<String> linkList = itemSearchPage.getLinkList();
        for (String link: linkList) {
            driver.get(link);
            WebElement itemCondition = driver.findElement(By.xpath(".//*[@id='vi-itm-cond']"));
            String condition = itemCondition.getText().toLowerCase();
            Assert.assertEquals(condition, EXPECTED_CONDITION);
        }
    }
}

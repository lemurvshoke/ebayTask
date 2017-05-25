package com.example.pages;

import com.gargoylesoftware.htmlunit.html.DomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

/**
 * Implementation of item search page(SERP).
 */
public class ItemSearchPage extends Page {
    private static final String ITEM_TITLE_TEXT_XPATH = ".//*[@id='ListViewInner']/li/h3/a/text()";
    private static final String ITEM_TITLE_XPATH = ".//*[@id=\'ListViewInner\']/li/h3/a";
    private static final String ITEM_PRICE_RUB_XPATH = "//div[@id=\"ResultSetItems\"]//span[@class=\"bold\"]";
    private static final String SALE_FORMAT_XPATH = "*//li[@class='lvformat']/span";
    private static final String DELIVERY_OPTIONS_XPATH = "//li[@class=\"lvshipping\"]//span[@class=\"bfsp\"]";
    private static final String ITEM_LOCATION_XPATH = "*//li[contains(text(), \"From United States\")]";
    String shippingType;
    String itemLocationType;

    public ItemSearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * @return list which contains item snippet titles
     */
        public List<String> getTitleList(){
            List<WebElement> itemSearchPageResult = driver.findElements(By.xpath(ITEM_TITLE_TEXT_XPATH));
            List<String> titleList = new ArrayList<String>();
            for (WebElement item: itemSearchPageResult) {
                String results = item.getText().toLowerCase();
                titleList.add(results);
            }
            return titleList;
        }

    /**
     * @return list which contains item snippet prices.
     */
    public List<String> getPriceList(){
        List<WebElement> itemSearchPageResult = driver.findElements(By.xpath(ITEM_PRICE_RUB_XPATH));
        List<String> priceList = new ArrayList<String>();
        for (WebElement price: itemSearchPageResult) {
            String results = price.getText().toLowerCase();
            priceList.add(results);
        }
        return priceList;
    }

    /**
     * @return list which contains links leading to item card page
     */
     public List<String> getLinkList(){
        List<WebElement> itemSearchPageResult = driver.findElements(By.xpath(ITEM_TITLE_XPATH));
        List<String> linkList = new ArrayList<String>();
        for (WebElement link: itemSearchPageResult) {
            String results = link.getAttribute("href");
            linkList.add(results);
        }
        return linkList;
    }


    /**
     * @return list which contains sale format: All Listings|Auction|Buy It Now
     */
    public List<String> getSaleFormatList(){
        List<WebElement> itemSearchPageResult = driver.findElements(By.xpath(SALE_FORMAT_XPATH));
        List<String> saleFormatList = new ArrayList<String>();
        for (WebElement format: itemSearchPageResult) {
            String results = format.getText().toLowerCase();
            saleFormatList.add(results);
        }
        return saleFormatList;
    }

    /**
     * @param shippingType contains delivery option name: Free International shipping
     * @return int counter for delivery option
     */
    public int getDeliveryOptionsCount(String shippingType){
        List<WebElement> itemSearchPageResult = driver.findElements(By.xpath(DELIVERY_OPTIONS_XPATH));
        List<String> deliveryOptionsList = new ArrayList<String>();
        int deliveryOptionsCounter = 0;
        for (WebElement option: itemSearchPageResult) {
            String results = option.getText().toLowerCase();
            if (results.equals(shippingType)) {
                deliveryOptionsList.add(results);
            }
            deliveryOptionsCounter++;
        }
        return deliveryOptionsCounter;
    }

    /**
     * @param itemLocationType contains location name: From Russia, From china, etc
     * @return int counter for item location
     */
    public int getItemLocationCount(String itemLocationType) {
        List<WebElement> itemSearchPageResult = driver.findElements(By.xpath(ITEM_LOCATION_XPATH));
        List<String> itemLocationList = new ArrayList<String>();
        int itemLocationCounter = 0;
        for (WebElement location: itemSearchPageResult) {
            String results = location.getText().toLowerCase();
            if (results.equals(itemLocationType)) {
                itemLocationList.add(results);
            }
            itemLocationCounter++;
        }
        return itemLocationCounter;
    }
}

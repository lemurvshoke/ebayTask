package com.example.pages;

import com.gargoylesoftware.htmlunit.html.DomElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Implementation of side bar filtration.
 */
public class ItemSearchFilter extends Page {
    private static final String FENDER_BRAND_CHECKBOX_XPATH = ".//*[@id='e1-19']";
    private static final String NEW_CONDITION_XPATH = ".//*[@id='e1-52']";
    private static final String LOWER_PRICE_FIELD_XPATH = ".//*[@id='e1-7']";
    private static final String SUBMIT_PRICE_BUTTON_XPATH = ".//*[@id='e1-65']";
    private static final String AUCTION_RADIOBUTTON_XPATH = ".//*[@id='e1-69']";
    private static final String US_ONLY_RADIOBUTTON_XPATH = ".//*[@id='e1-77']";
    private static final String FREE_INTERNATIONAL_SHIPPING_CHECKBOX_XPATH = ".//*[@id='e1-86']";
    private static final String RETURNS_ACCEPTED_CHECKBOX_XPATH = ".//*[@id='e1-89']";
    String price;


    public ItemSearchFilter(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = FENDER_BRAND_CHECKBOX_XPATH)
    WebElement fenderBrandCheckbox;

    @FindBy(xpath = NEW_CONDITION_XPATH)
    WebElement newConditionCheckbox;

    @FindBy(xpath = LOWER_PRICE_FIELD_XPATH)
    WebElement lowerPriceField;

    @FindBy(xpath = SUBMIT_PRICE_BUTTON_XPATH)
    WebElement submitPriceButton;

    @FindBy(xpath = AUCTION_RADIOBUTTON_XPATH)
    WebElement auctionRadiobutton;

    @FindBy(xpath = US_ONLY_RADIOBUTTON_XPATH)
    WebElement usOnlyRadiobutton;

    @FindBy(xpath = FREE_INTERNATIONAL_SHIPPING_CHECKBOX_XPATH)
    WebElement freeInternationalShippingCheckbox;

    @FindBy(xpath = RETURNS_ACCEPTED_CHECKBOX_XPATH)
    WebElement returnsAcceptedCheckbox;

    /**
     * Set of click-methods to operate with private variables.
     */
    public void fenderBrandCheckboxClick() {
        fenderBrandCheckbox.click();
    }

    public void freeInternationalShippingCheckboxClick() {
        freeInternationalShippingCheckbox.click();
    }

    public void auctionRadiobuttonClick() {
        auctionRadiobutton.click();
    }

    public void usOnlyRadiobuttonClick() {
        usOnlyRadiobutton.click();
    }

    public void newConditionCheckboxClick() { newConditionCheckbox.click(); }

    /**
     * Submit lower price range method.
     * @param price
     */
    public void submitLowerPrice(String price) {
        lowerPriceField.sendKeys(price);
        submitPriceButton.click();
    }

    public void returnsAcceptedCheckboxClick() {
        returnsAcceptedCheckbox.click();
    }
}

package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;

/**
 * Implementation of home page.
 */
public class HomePage extends Page {
  private static final String XPATH_SEARCH_LINE = "//*[@id=\"gh-ac\"]";
  private static final String XPATH_SEARCH_BUTTON = "//*[@id=\"gh-btn\"]";
  private HomePage homepage;

  @FindBy(xpath = XPATH_SEARCH_LINE)
  WebElement searchLine;

  @FindBy(xpath = XPATH_SEARCH_BUTTON)
  WebElement searchButton;

  public HomePage(WebDriver webDriver) {
    super(webDriver);
  }

  public void openPage(String URL) {

    this.driver.get(URL);
  }

  /**
   *
   * @param searchInput
   * @return ItemSearchPage
   */
  public ItemSearchPage search(String searchInput) {
    searchLine.sendKeys(searchInput);
    searchButton.click();

    return new ItemSearchPage(driver);
  }
}



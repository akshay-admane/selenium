package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchResults= By.cssSelector("div#content div.product-layout");

	public SearchPage(WebDriver driver) {
		this.driver= driver;
		eleUtil= new ElementUtil(driver);
	}
	
	public int getSearchPrductCount() {
		int searchProductCount= eleUtil.waitForElementsVisible(searchResults, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Search product count is ::: "+searchProductCount);
		return searchProductCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productLocator= By.linkText(productName);
		eleUtil.waitForElementPresence(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}

}

package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	
	private WebDriver driver;
	ElementUtil eleUtil;
	
	
	//Locators
	private By logout= By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']"); 
	private By accHeaders= By.cssSelector("div #content h2");
	private By listOptions= By.xpath("//a[@class='list-group-item']");
	private By searchBox= By.xpath("//input[@placeholder='Search']");
	private By searchBtn= By.xpath("//i[@class='fa fa-search']");
	
	

	//Create page constant
	public AccountsPage(WebDriver driver) {
		this.driver= driver;
		eleUtil= new ElementUtil(driver);
	}
	
	//page methods
	
	@Step(".....getting Accounts page title.....")
	public String getAccpageTitle() {
		String accTitle=eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		return accTitle;
	}
	
	@Step(".....getting accounts page url.....")
	public String getAccPageURL() {
		String accPageURL= eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
		return accPageURL;
	}
	
	@Step(".....check if logout link exist.....")
	public boolean isLogoutExist() {
		return eleUtil.waitForElementPresence(logout, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	@Step(".....check searchbar exist.....")
	public boolean isSearchBoxExist() {
		return eleUtil.waitForElementPresence(searchBox, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	@Step(".....get account page header list.....")
	public List <String> getaccPageHeadersList() {
		List <WebElement> accPageHeadersList= eleUtil.waitForElementsPresence(accHeaders, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List <String> accHeadersValueList= new ArrayList<String>();
		for(WebElement e:accPageHeadersList ) {
			String value= e.getText();
			accHeadersValueList.add(value);
		}
		return accHeadersValueList;
	}
	
	@Step(".....get account page options.....")
	public List<String> getaccPageOptionsList() {
		List <WebElement> accPageOptionsList=eleUtil.waitForElementsPresence(listOptions, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List <String> accPageOptionsValueList= new ArrayList<String>();
		for(WebElement e: accPageOptionsList ) {
			String value= e.getText();
			accPageOptionsValueList.add(value);
		}
		return accPageOptionsValueList;
		}
	
	@Step(".....perform search.....")
	public SearchPage performSearch(String searchKey) {
		if(isSearchBoxExist()) {
			eleUtil.doSendKeys(searchBox, searchKey);
			eleUtil.doClick(searchBtn);
			return new SearchPage(driver);
		}else {
			System.out.println("Search field is not present on page");
			return null;
		}
	}

}

package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup() {
		accountsPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		String accPageTitle=accountsPage.getAccpageTitle();
		Assert.assertEquals(accPageTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accountsPage.getAccPageURL().contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test
	public void isLogoutExist() {
		Assert.assertTrue(accountsPage.isLogoutExist());
	}
	
	@Test
	public void accountsPageHeaderCountTest() {
		List<String> actualHeaders= accountsPage.getaccPageHeadersList();
		System.out.println(actualHeaders);
		Assert.assertEquals(actualHeaders.size(), AppConstants.ACCOUNTS_PAGE_HEADER_COUNT);
	}
	
	@Test
	public void accountsPageHeaderValueTest() {
		List<String> actualHeaders= accountsPage.getaccPageHeadersList();
		System.out.println("Exoected Headers are "+AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADER_LIST);
		System.out.println("Actual Headers are "+actualHeaders);
		Assert.assertEquals(actualHeaders, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADER_LIST);
	}
	
	@Test
	public void accountsPageOptionsCountTest() {
		List<String> actualOptions= accountsPage.getaccPageOptionsList();
		System.out.println(actualOptions);
		Assert.assertEquals(actualOptions.size(), AppConstants.ACCOUNTS_PAGE_OPTIONS_COUNT);
	}
	
	@Test
	public void accountsPageOptionsValueTest() {
		List<String> actualHeaders= accountsPage.getaccPageHeadersList();
		System.out.println("Expected Headers are "+AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADER_LIST);
		System.out.println("Actual Headers are "+actualHeaders);
		Assert.assertEquals(actualHeaders, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADER_LIST);
	}
	
	@DataProvider
	public Object [] [] getProductData(){
		return new Object [] [] {
			{"macbook"}, {"imac"}, {"samsung"}, {"apple"}
		};
	}
	
	@Test(dataProvider= "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage= accountsPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchPrductCount()>0);
	}
	
	@DataProvider
	public Object [] [] getProductTestData(){
		return new Object [] [] {
			{"Macbook", "MacBook Air"}, {"Macbook", "MacBook Pro"},{"Macbook", "MacBook"},{"imac", "iMac"}, {"Samsung", "Samsung Galaxy Tab 10.1"},
			{"Samsung", "Samsung SyncMaster 941BW"}
		};
	}
	
	@Test(dataProvider= "getProductTestData")
	public void searchProductTest(String searchKey, String productName) {
		searchPage= accountsPage.performSearch(searchKey);
		if ((searchPage.getSearchPrductCount()>0)) {
			productInfoPage=searchPage.selectProduct(productName);
			String actualproductHeaderValue= productInfoPage.getHeaderValue();
			productInfoPage.getProductDetails();
			Assert.assertEquals(actualproductHeaderValue, productName);
		}
	}
	
	
	
	
	

}

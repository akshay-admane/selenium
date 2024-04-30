package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		accountsPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@DataProvider
	public Object [] [] getProductTestData(){
		return new Object [] [] {
			{"Macbook", "MacBook Air", 4}, 
			{"Macbook", "MacBook Pro", 4},
			{"Macbook", "MacBook", 5},
			{"imac", "iMac", 3}, 
			{"Samsung", "Samsung Galaxy Tab 10.1", 7},
			{"Samsung", "Samsung SyncMaster 941BW", 1}
		};
	}
	@Test(dataProvider= "getProductTestData")
	public void verifyProductImageCount(String searchKey, String productName, int imageCount) {
		searchPage=accountsPage.performSearch(searchKey);
		productInfoPage=searchPage.selectProduct(productName);
		int actualImageCount= productInfoPage.getProductImageCount();
		Assert.assertEquals(actualImageCount, imageCount);
	}
	
	@Test
	public void productInfoTest() {
		searchPage=accountsPage.performSearch("macbook");
		productInfoPage=searchPage.selectProduct("MacBook Pro");
		Map<String, String>actualProductInfoMap= productInfoPage.getProductDetails();
		softAssert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductInfoMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actualProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductInfoMap.get("productName"), "MacBook Pro");
		softAssert.assertAll();
	}
	
	
	@DataProvider
	public Object [] [] addToCartData(){
		return new Object [] [] {
			{"Macbook", "MacBook Air"}, 
			{"Macbook", "MacBook Pro"},
			{"Macbook", "MacBook"},
			{"imac", "iMac"}, 
			{"Samsung", "Samsung Galaxy Tab 10.1"},
			{"Samsung", "Samsung SyncMaster 941BW"}
		};
	}	
	@Test(dataProvider= "addToCartData")
	public void addToCartTest(String searchKey, String productName) {
		searchPage=accountsPage.performSearch(searchKey);
		productInfoPage=searchPage.selectProduct(productName);
		productInfoPage.enterQuantity(2);
		String actualMessage= productInfoPage.addTocart();
		softAssert.assertTrue(actualMessage.contains(productName));
		softAssert.assertTrue(actualMessage.contains("Success"));
		softAssert.assertEquals(actualMessage, "Success: You have added "+productName+" to your shopping cart!");
		softAssert.assertAll();
	}

}

















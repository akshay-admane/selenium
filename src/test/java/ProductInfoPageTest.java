

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	
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

}

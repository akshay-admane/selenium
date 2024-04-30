package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterationTest extends BaseTest {
	
//	@BeforeClass
//	public void regPageSetUp() {
//		registerPage= loginPage.naivateToRegisterationPage();
//	}
	
	public String getRandomEmail() {
		//Random ran= new Random();
		String ranEmail= "automation@"+System.currentTimeMillis()+"gmail.com";
		System.out.println(ranEmail);
		return ranEmail;
		
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		 Object regData[][]=ExcelUtil.getTestData(AppConstants.USER_REG_SHEET_NAME); 
		 return regData;
	}

	@Test(dataProvider= "getRegisterData")
	public void registerUser(String firstname, String lastname, String telephone, String password, String subscribe )  {
		registerPage= loginPage.naivateToRegisterationPage();
		Assert.assertTrue(registerPage.registerAccount(firstname, lastname, getRandomEmail(),telephone,  password, subscribe));
	}
	
}

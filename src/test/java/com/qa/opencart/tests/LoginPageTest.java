package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic: 100- Design Login for OpenCart")
@Story("US- Login 101: Design Login Page Features for Login Page")
public class LoginPageTest extends BaseTest {
	
	@Severity(SeverityLevel.TRIVIAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actualURL=loginPage.getLoginPageUrl();
		Assert.assertTrue(actualURL.contains
				(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkExist() {
		Assert.assertTrue(loginPage.isForgotPasswordExist());
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void isLogoDisplayed() {
		Assert.assertTrue(loginPage.isLogoDisplayed());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 6)
	public void loginTest() {
		accountsPage= loginPage.doLogin
				(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accountsPage.isLogoutExist());
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5)
	public void invalidCredsLoginTest() {
		loginPage.doLogin
		(prop.getProperty("invalidUsername"), prop.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.isErrorMessageDisplayed());
		
	}
	
	@Severity(SeverityLevel.TRIVIAL)
	@Test
	public void verifyRightColumn() {
		List<String>rightColumnOptions=loginPage.verifyRightColumn();
		System.out.println(rightColumnOptions);
		softAssert.assertEquals(rightColumnOptions.size(), AppConstants.LOGIN_PAGE_RIGHT_COLUMN_OPTIONS_SIZE);
		softAssert.assertEquals(rightColumnOptions, AppConstants.EXPECTED_LOGIN_PAGE_RIGHT_COLUMN_OPTIONS_LIST);
	}

	

}

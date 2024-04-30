package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//Locators
	private By emailID= By.id("input-email");
	private By password= By.name("password");
	private By loginBtn= By.xpath("//input[@value='Login']");
	private By forgotPasswordLink= By.xpath("//div[@class='form-group']//a[normalize-space()='Forgotten Password']");
	private By logo= By.xpath("//a[normalize-space()='Qafox.com']");
	private By rightColumn= By.xpath("//a[@class='list-group-item']");
	private By errorMessage= By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	private By registerLink= By.xpath("//a[@class='list-group-item'][normalize-space()='Register']");
	
	//Create page const
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		eleUtil= new ElementUtil(driver);
	}
	
	
	//Page actions
	
	@Step(".....getting login page title.....")
	public String getLoginPageTitle() {
		String Title=eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE );
		return Title;
	}
	
	@Step(".....getting login page url....")
	public String getLoginPageUrl() {
		String actualURL= eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		return actualURL;
	}
	
	@Step(".....checking forgot password link....")
	public boolean isForgotPasswordExist() {
		return eleUtil.waitForElementPresence(forgotPasswordLink, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
		
	}
	
	@Step("....logging in using email= {0} and password= {1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("App credentials are "+username+ ":"+pwd);
		eleUtil.waitForElementPresence(emailID, AppConstants.DEFAULT_SHORT_TIME_OUT).clear();
		eleUtil.waitForElementPresence(emailID, AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step("....checking if logo is displayed")
	public boolean isLogoDisplayed() {
		return eleUtil.waitForElementPresence(logo, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	@Step("....logging in with invalid credentials as email= {0} and password= {1}")
	public boolean loginInvalidCredentials(String username, String pwd) {
		System.out.println("App credentials are "+username+ ":"+pwd);
		eleUtil.waitForElementPresence(emailID, AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return eleUtil.waitForElementPresence(errorMessage, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	@Step("....checking if error message is displayed upon invalid login attempt....")
	public boolean isErrorMessageDisplayed() {
		return eleUtil.waitForElementPresence(errorMessage, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	@Step("....navigating to the register page....") 
	public RegisterPage naivateToRegisterationPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	@Step("....verifying right column options.....")
	public List<String> verifyRightColumn() {
		List <WebElement> rightColumnOptions= eleUtil.waitForElementsPresence(rightColumn, AppConstants.DEFAULT_SHORT_TIME_OUT);
		List <String> rightColumnValues= new ArrayList<String>();
		for(WebElement e:rightColumnOptions ) {
			String value= e.getText();
			rightColumnValues.add(value);
		}
		return rightColumnValues;
		
	}
	
	
	
	
}

package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");

	private By registerSuccessMesg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	
	
	public RegisterPage(WebDriver driver) {
		this.driver= driver;
		eleUtil= new ElementUtil(driver);
	}
	
	public boolean registerAccount(String fn, String ln, String em, String telephoneNo, String pwd, String subscribe)   {
		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, firstName).sendKeys(fn);
		eleUtil.doSendKeys(lastName, ln);
		eleUtil.doSendKeys(email, em);
		eleUtil.doSendKeys(telephone, telephoneNo);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doSendKeys(confirmpassword, pwd);
		   if(subscribe.equalsIgnoreCase("yes")) {
			   eleUtil.doClick(subscribeYes);
		   }else {
			   eleUtil.doClick(subscribeNo);
		   }
		eleUtil.doActionsCick(agreeCheckBox);   
		eleUtil.doClick(continueButton);
		String successMeaage=eleUtil.waitForElementPresence(registerSuccessMesg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		System.out.println("User registeration success message is "+successMeaage);
		if( successMeaage.contains(AppConstants.USER_REG_SUCCESS_MESSAGE)){
			eleUtil.doClick(logoutLink);
			return true;
		}else {
			
			return false;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}

package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private Map<String, String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver= driver;
		eleUtil= new ElementUtil(driver);
	}
	
	private By productHeader= By.cssSelector("div[id='content'] h1");
	private By productImages= By.cssSelector("ul.thumbnails img");
	private By productMetaData= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPricing= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity= By.xpath("//input[@id='input-quantity']");
	private By addToCart= By.xpath("//button[@id='button-cart']");
	private By addToCartsuccessMsg= By.cssSelector(".alert.alert-success.alert-dismissible");
	
	public String getHeaderValue() {
		String productHeaderValue= eleUtil.doElementGetText(productHeader);
		System.out.println("Product header is :::"+productHeaderValue);
		return productHeaderValue;
	}
	
	public int getProductImageCount() {
		int actualProductImagesCount= eleUtil.waitForElementsPresence(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("actualProductImagesCount is :::"+actualProductImagesCount);
		return actualProductImagesCount;
	}
	
	public void enterQuantity(int i) {
		eleUtil.doSendKeys(quantity, String.valueOf(i));
	}
	
	public String addTocart() {
		eleUtil.waitForElementPresence(addToCart, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		String CartsuccessMsg= eleUtil.waitForElementPresence(addToCartsuccessMsg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		StringBuilder sb= new StringBuilder(CartsuccessMsg);
		String msg= sb.substring(0, CartsuccessMsg.length()-1 ).replace("\n", "").toString();
		System.out.println("Add to cart success message is :::"+msg);
		return msg;
		
	}
	
	public Map<String, String> getProductDetails() {
		productInfoMap= new TreeMap<String, String>();
		productInfoMap.put("productName", getHeaderValue());
		getProductMataData();
		getProductPrice();
		System.out.println(productInfoMap);
		return productInfoMap;
	
	}
	
	private void getProductMataData() {
		List <WebElement> metaList= eleUtil.getElements(productMetaData);
		for (WebElement e: metaList) {
			String meta=e.getText();
			String metaInfo[]= meta.split(":");
			String key= metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
		}
	}
	
	private void getProductPrice() {
			List<WebElement>pricingList= eleUtil.getElements(productPricing);
			String price= pricingList.get(0).getText();
			String exTax= pricingList.get(1).getText();
			productInfoMap.put("productprice", price);
			productInfoMap.put("exTax", exTax);
		}
	
	
	
	
	
	
	
}

package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.qa.opencart.exception.FrameworkException;


public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to return the driver
	 * 
	 * @param browserName
	 * @return
	 */

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println("browser name is : " + browserName);

		highlight = prop.getProperty("highlight").trim();
		optionsManager = new OptionsManager(prop);

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C://Users//akshay.ad//Downloads//chromedriver-win32 (1)//chromedriver-win32//chromedriver.exe"); // Replace with your actual path
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		} else if (browserName.equals("firefox")) {
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}

		else if (browserName.equals("edge")) {
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		}

		else {
			System.out.println("Please pass the right browser: " + browserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	/**
	 * @return This method will return a thread local copy of the WebDriver
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize the properties
	 * 
	 * @return this will return properties prop reference
	 */

	public Properties initProp() {
		
		//mvn clean install -Denv="qa"
		prop= new Properties();
		FileInputStream ip=null;
		String envName= System.getProperty("env");
		System.out.println("=======Running test cases on env "+envName+ "=========");
		try {
		   if(envName==null) {
			   System.out.println("........no env is passed........runnning tests on qa env........");
			    ip= new FileInputStream("./src/test/resources/config/qa.config.properties");
		   }else {
			   switch(envName.toLowerCase().trim()) {
			   case "qa":
				    ip= new FileInputStream("./src/test/resources/config/qa.config.properties");
				   break;
			   case "dev":
				    ip= new FileInputStream("./src/test/resources/config/dev.config.properties");
				   break;
			   case "stage":
				    ip= new FileInputStream("./src/test/resources/config/stage.config.properties");
				   break;
			   case "prod":
				    ip= new FileInputStream("./src/test/resources/config/config.properties");
				   break;
				   
				   default:
					   System.out.println(".....Incorrect environment is paased....not running the tests");
					   throw new FrameworkException(".....incorrect environment is passed....");
				   //break;
			   }
		   }
		}catch(FileNotFoundException e) {
			
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return prop;
	}

	/**
	 * This method is used to take screenshot for the project which has no space in
	 * between
	 * 
	 * @return
	 */

	public static String getScreenshotinbase64() {
		File srcFile= ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
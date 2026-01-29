package com.qa.OpencartProject.Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.OpencartProject.DriverFactory.DriverFactory;
import com.qa.OpencartProject.Utils.AppConstants;
import com.qa.OpencartProject.Utils.ElementUtils;


public class LoginPage {
	
	
	//private By locators
	//these private locator marked as final =>beacause we dont want to change the value of locators
	
	private final By email=By.id("input-email");  
	private final By pass=By.id("input-password");  
	private final By Login=By.xpath("//input[@type='submit']");
	private final By forgottenpassword=By.xpath("(//a[text()='Forgotten Password'])[1]"); 
	private final By header=By.tagName("h2");  
	
	
	//register link locator
	private final By registerLink=By.xpath("(//a[text()='Register'])[2]");
	
	//LOGIN PAGE -Warning error message
	private final By warningErrorMessage=By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	
	
	//public page_class constructor
	//page_class :LoginPage
	//creating private webdriver driver to fetch current driver 
	
	private WebDriver driver;    
	private ElementUtils eleUtils;
	
	private static final Logger log=LogManager.getLogger(LoginPage.class);
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtils=new ElementUtils(driver);
	}
	
	
	//public methods
	
	public String loginpagetitle()
	{
		String title=eleUtils.waitforexactTitle(AppConstants.loginPage_Title,AppConstants.DEFAULT_SHORT_WAIT);
		
		//System.out.println("Login page title is : "+title);
		log.info("Login page title is : "+title);
		return title;
		
	}
	
	public String loginpageurl()
	{
		String url=eleUtils.waitforUrlContains(AppConstants.LoginPage_containsURL,AppConstants.DEFAULT_SHORT_WAIT);
		//System.out.println("Login page URL  is : "+url);
		log.info("Login page URL  is : "+url);
		return url;
		
	}
	
	public boolean isForgotPasswordLinkExist()
	{
		boolean flag=eleUtils.isElementDisplayed(forgottenpassword);
		return flag;
	}
	
	public boolean isHeaderExist() 
	{
		boolean flag=eleUtils.isElementDisplayed(header);
		return flag;
		
	}
	
	public AccountsPage doLogin(String username ,String password) 
	{
		
		
		eleUtils.waitforElementsVisibility(email, AppConstants.DEFAULT_SHORT_WAIT);
		eleUtils.doSendKeys(email, username);
		eleUtils.doSendKeys(pass, password);
		eleUtils.doClick(Login);
		
				
		eleUtils.waitforexactTitle("My Account", AppConstants.DEFAULT_SHORT_WAIT);
		
		return new AccountsPage(driver); 
		
		
		
	}
	
	public RegisterPage NavigateToregisterlink()
	{
		eleUtils.waitForElementPresence(registerLink, AppConstants.DEFAULT_SHORT_WAIT).click();
		
		// after clicking register page open. So register page driver need to call
		return new RegisterPage(driver);
	}
	
	
	public boolean doLoginwithInvalidCreds(String inUname, String inPass)
	{
		
		log.info("Invalid App Credentials are : "+inUname + " :" +inPass);
		
		WebElement el=eleUtils.waitForElementPresence(email, AppConstants.DEFAULT_SHORT_WAIT);
		el.clear();
		el.sendKeys(inUname);
		WebElement el2=eleUtils.waitForElementVisibility(pass, AppConstants.DEFAULT_SHORT_WAIT);
		el2.clear();
		eleUtils.doSendKeys(pass, inPass);
		eleUtils.waitForElementClickable(Login, AppConstants.DEFAULT_SHORT_WAIT);
		
	
		String error=eleUtils.waitForElementPresence(warningErrorMessage, AppConstants.DEFAULT_SHORT_WAIT).getText();
		
		log.error("Login Error Message is : "+error);
		
		if(error.contains(AppConstants.LOGIN_MAX_ATTEMPT_MESSG))
		{
			return true;
		}
		else if(error.contains(AppConstants.LOGIN_INVALID_ERROR_MESSG))
		{
			return true;
		}
		return false;
	}
	
}

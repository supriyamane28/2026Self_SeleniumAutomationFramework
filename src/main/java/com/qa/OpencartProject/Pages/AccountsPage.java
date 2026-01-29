package com.qa.OpencartProject.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.OpencartProject.Utils.AppConstants;
import com.qa.OpencartProject.Utils.ElementUtils;

public class AccountsPage 
{
	
	
	//private By locators
		//these private locator marked as final =>beacause we dont want to change the value of locators
		
	private final By header=By.tagName("h2");    //multiple header
	private final By logoutLink=By.linkText("Logout");  //logout link
	private final By searchicon=By.xpath("//div[@id='search']//button");
	private final By searchbar=By.name("search");
	
	
	
	
	//public page_class constructor
		//page_class :Accountpage
		//creating private webdriver driver to fetch current driver 
		
		private WebDriver driver;  
		private ElementUtils eleUtils;
		public AccountsPage(WebDriver driver)
		{
			this.driver=driver;
			eleUtils=new ElementUtils(driver);
		}
		
		
		//public methods
		
		public List<String> getAccountHeaders()
		{
			return eleUtils.getElementsTextList(header);
			 
		}
		
		

		public boolean logoutlink()
		{
			boolean flag=eleUtils.isElementDisplayed(logoutLink);
			return flag;
		}
		
		public SearchResultPage doSearch(String searchvalue)
		{
			
			
			WebElement ele=eleUtils.waitForElementPresence(searchbar, AppConstants.DEFAULT_SHORT_WAIT);
			ele.clear();
			ele.sendKeys(searchvalue);
			eleUtils.doActionsSendKeys(searchbar, searchvalue);
			eleUtils.doClick(searchicon);
			
			return new SearchResultPage(driver);
			
		}
		
		
}

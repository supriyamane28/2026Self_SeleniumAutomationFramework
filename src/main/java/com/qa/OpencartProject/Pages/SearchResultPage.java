package com.qa.OpencartProject.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.OpencartProject.Utils.AppConstants;
import com.qa.OpencartProject.Utils.ElementUtils;

public class SearchResultPage 
{

	//maintaining by loactors
	
	private final By searchresult=By.xpath("//div[@class='product-thumb']/parent::div");
	private final By resultsHeader=By.tagName("h1");
	
	
	

	private WebDriver driver;  
	private ElementUtils eleUtils;
	
	//public page_class constructor
	public SearchResultPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtils=new ElementUtils(driver);
	}
	
	
	//public methods
	
	public int getSearchResultCounts()
	{
		int count=eleUtils.waitforElementsPresence(searchresult, AppConstants.DEFAULT_SHORT_WAIT).size();
		System.out.println("Total Search results :"+count);
		return count;
	}
	
	public String getResultHeader()
	{
		String text=eleUtils.doElementGetText(resultsHeader);
		System.out.println("Search result page Header is : "+text);
		return text;
	}
	
	public ProductInfoPage selectproduct(String productname)
	{
		System.out.println("Product to be sleected :"+productname);
		eleUtils.doClick(By.linkText(productname));
		
		return new ProductInfoPage(driver);
	}
	
	
	
	
	
}

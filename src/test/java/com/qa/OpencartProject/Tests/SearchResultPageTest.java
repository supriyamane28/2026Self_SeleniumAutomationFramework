package com.qa.OpencartProject.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.OpencartProject.Base.BaseTest;

public class SearchResultPageTest extends BaseTest
{

	@BeforeClass
	public void searchpageSetup()
	{
		accPage=lpage.doLogin(proobj.getProperty("username"),proobj.getProperty("password"));
		searchResPage=accPage.doSearch("macbook");
				
	}
	
	@Test(priority=1)
	public void searchResultCounttest()
	{
		
		int count=searchResPage.getSearchResultCounts();
		System.out.println("total search result count :"+count);
		Assert.assertTrue(count > 0, "Search result count should be greater than 0");
		
	}
	
	@Test(priority=2)
	public void getResultHeadertest()
	{
		
		String text=searchResPage.getResultHeader();
		//Assert.assertEquals(text, "Search - macbook");
		Assert.assertTrue(text.contains("Search"), "Header is not correct");
	}
	
	@Test(priority=3)
	public void searchproductest()
	{
		// searchResPage=accPage.doSearch("macbook");   this code already written in searchproductSetup() 
		productInfoPage=searchResPage.selectproduct("MacBook Pro");
		String text=productInfoPage.getHeaderProductInforPage();
		Assert.assertEquals(text, "MacBook Pro");
		
	}
	
	
}

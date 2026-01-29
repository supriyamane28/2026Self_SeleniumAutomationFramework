package com.qa.OpencartProject.Tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.OpencartProject.Base.BaseTest;
import com.qa.OpencartProject.Pages.AccountsPage;


public class AccountsPageTest extends BaseTest
{
	

	@BeforeClass
	public void accpageSetup()
	{
		accPage=lpage.doLogin(proobj.getProperty("username"),proobj.getProperty("password"));
		
	}
	
	@Test(priority=1)
	public void getaccHeaders()
	{
		List<String> text=accPage.getAccountHeaders();
		List<String> listtext=new ArrayList<String>();
		listtext.addAll(text);
		
	}
	
	@Test(priority=2)
	public void islogoutlinkexist()
	{
		boolean flag=accPage.logoutlink();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void accPageHeaderTest()
	{
		List<String> accpageheadertext=accPage.getAccountHeaders();
		Assert.assertEquals(accpageheadertext.size(), 4);
	}
	
	@Test(priority=4)
	public void dosearchproduct()
	{
		accPage.doSearch("macbook");
	}
	
}

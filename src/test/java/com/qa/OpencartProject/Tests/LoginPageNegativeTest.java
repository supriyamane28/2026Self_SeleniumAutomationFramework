package com.qa.OpencartProject.Tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.OpencartProject.Base.BaseTest;

public class LoginPageNegativeTest extends BaseTest
{

	@DataProvider
	public Object[][] getNegativeLoginData()
	{
		return new Object[][]
				{
			    {"","test"},
			    {"test@gmail.com",""},
			    {"test@gmail.com","test123"}     
			
				};
		
		
	}
	
	
	@Test(dataProvider = "getNegativeLoginData")
	public void negativeLoginTest(String uname, String pass) {
		
		boolean flag=lpage.doLoginwithInvalidCreds(uname,pass);
		
		Assert.assertTrue(flag);
		
	}
	
}

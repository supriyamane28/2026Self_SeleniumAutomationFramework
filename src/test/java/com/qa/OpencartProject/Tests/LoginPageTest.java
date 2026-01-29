package com.qa.OpencartProject.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.OpencartProject.Base.BaseTest;
import com.qa.OpencartProject.Utils.AppConstants;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("EP100: Design the Open cart Login App Page")
@Feature("F101: Design open cart login page")
@Story("S01: develop login core features")
public class LoginPageTest extends BaseTest {

	// In baseTest class >> creating object of LoginPageTest class 
	// object is "lpage"
	
	@Test(priority=1)
	public void loginpageTitle()
	{
		String titletext=lpage.loginpagetitle();
		ChainTestListener.log("Login page title is : "+titletext);
		Assert.assertEquals(titletext,AppConstants.loginPage_Title);
	}
	
	@Test(priority=2)
	public void loginpageurl()
	{
		String urltext=lpage.loginpageurl();
		Assert.assertEquals(urltext,AppConstants.LoginPage_exactURL);
	}

	@Test(priority=3)
	public void isforgotpassLinkpresent()
	{
		boolean flag=lpage.isForgotPasswordLinkExist();
		Assert.assertTrue(flag);
		
	}
	@Test(priority=4)
	public void isheaderpresent()
	{
		boolean flag=lpage.isHeaderExist();
		Assert.assertTrue(flag);
		
	}
	@Test(priority=5)
	public void loginbuttonclick() throws InterruptedException
	{
		Thread.sleep(2000);
		accPage=lpage.doLogin(proobj.getProperty("username"),proobj.getProperty("password"));
		boolean flag=accPage.logoutlink();
		Assert.assertTrue(flag);
		
	}
	
	
}

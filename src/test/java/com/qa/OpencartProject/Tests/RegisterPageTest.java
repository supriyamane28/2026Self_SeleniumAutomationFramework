package com.qa.OpencartProject.Tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.OpencartProject.Base.BaseTest;
import com.qa.OpencartProject.Utils.CsvUtils;
import com.qa.OpencartProject.Utils.ExcelUtils;
import com.qa.OpencartProject.Utils.StringUtils;

public class RegisterPageTest extends BaseTest
{
	@BeforeClass
	public void gotoRegisterPage()
	{
		regpage=lpage.NavigateToregisterlink();
	}

	
	//using excel
	@DataProvider
	public Object[][] getuserDataFromSheet()
	{
		Object obj [][]=ExcelUtils.getTestData("Registration");
		return obj;
	}
	
	@Test(dataProvider ="getuserDataFromSheet")
	public void doregistertest(String firstname,String lastname,String telephone,String password ,String subscribe)
	{
		
		boolean flag=regpage.userRegister(firstname, lastname, StringUtils.genearteEmailId(), telephone,password,subscribe);
		Assert.assertTrue(flag);
		
	}
	
	
	//using .csv file 
	@DataProvider
	public Object[][] getuserDataFromCSVfile()
	{
		Object obj [][]=CsvUtils.csvData("registration");
		return obj;
	}
	
	@Test(dataProvider ="getuserDataFromCSVfile")
	public void doregistertestfromCSVfile(String firstname,String lastname,String telephone,String password ,String subscribe)
	{
		
		boolean flag=regpage.userRegister(firstname, lastname, StringUtils.genearteEmailId(), telephone,password,subscribe);
		Assert.assertTrue(flag);
		
	}
	
}

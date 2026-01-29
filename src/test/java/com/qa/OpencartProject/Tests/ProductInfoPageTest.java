package com.qa.OpencartProject.Tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.OpencartProject.Base.BaseTest;
import com.qa.OpencartProject.Utils.ExcelUtils;
import com.qa.OpencartProject.Utils.StringUtils;

public class ProductInfoPageTest extends BaseTest
{

	@BeforeClass
	public void productinfopageSetup()
	{
		accPage=lpage.doLogin(proobj.getProperty("username"),proobj.getProperty("password"));
	}
	
	//data with the help of array
	@DataProvider
	public Object[][] getDataToSearchProduct()
	{
		Object obj[][]=new Object[3][2];      //creating object class array[row][col]
		
		// for 1st row data
		obj[0][0]="macbook";
		obj[0][1]="MacBook Air";
		
		// for 2nd row data
		obj[1][0]="samsung";
		obj[1][1]="Samsung SyncMaster 941BW";
		
		// for 3rd row data
		obj[2][0]="canon";
		obj[2][1]="Canon EOS 5D";
		
		return obj;
		
	}
	
	@Test(dataProvider = "getDataToSearchProduct")
	public void productHeaderTest(String searchvalue,String productname)
	{
		searchResPage=accPage.doSearch(searchvalue);
		productInfoPage=searchResPage.selectproduct(productname);

		String text=productInfoPage.getHeaderProductInforPage();
		Assert.assertEquals(text, productname);
	}
	
	
	//data with the help of object array
	@DataProvider
	public Object[][] getDatacountofImagesofProduct()
	{
		//Object obj[][]= {{},{},{},{}};   //4 test data passing . 
		//Each data (search text, selected/clicked product,total count of images)
		
		Object obj[][]= {{"macbook","MacBook Air",4}
						,{"samsung","Samsung SyncMaster 941BW",1}
						,{"canon","Canon EOS 5D",3}
						,{"imac","iMac",3}}; 
		
		return obj;
		
	}
	
	
	
	@Test(dataProvider = "getDatacountofImagesofProduct")
	public void countallimageofProduct(String searchvalue,String productname,int imagecount)
	{
		searchResPage=accPage.doSearch(searchvalue);
		productInfoPage=searchResPage.selectproduct(productname);
		
	int count=productInfoPage.getProductImagescount();
	Assert.assertEquals(count, imagecount);
	}
	
	
	//data with the help of excel 
	@DataProvider
	public Object[][] getDataToSearchProductfromsheet()
	{
		Object obj [][]=ExcelUtils.getTestData("productinfo");
		return obj;
	}
	@Test(dataProvider ="getDataToSearchProductfromsheet")
	public void productHeaderTestsheet(String searchvalue,String productname)
	{
		searchResPage=accPage.doSearch(searchvalue);
		productInfoPage=searchResPage.selectproduct(productname);

		String text=productInfoPage.getHeaderProductInforPage();
		Assert.assertEquals(text, productname);
	}
	
	
	
	
	@Test
	public void productInfoTest()
	{
		SoftAssert so=new SoftAssert();   //using soft assertion..in that we can run all assertion and report all
		
		searchResPage=accPage.doSearch("macbook");
		productInfoPage=searchResPage.selectproduct("MacBook Pro");
		
		Map<String, String> allProductInfo = productInfoPage.allProductInfo();
		
		
		so.assertEquals(allProductInfo.get("Brand"),"Apple");
		so.assertEquals(allProductInfo.get("Availability"),"Out Of Stock");
		so.assertEquals(allProductInfo.get("Product Code"),"Product 18");
		so.assertEquals(allProductInfo.get("Reward Points"),"800");
		
		so.assertEquals(allProductInfo.get("Ex Tax"),"$2,000.00");
		
		so.assertAll();  //this method is mandatory to call in soft assertion
		
	}
	
	
}
	


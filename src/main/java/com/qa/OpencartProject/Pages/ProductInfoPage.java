package com.qa.OpencartProject.Pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.OpencartProject.Utils.AppConstants;
import com.qa.OpencartProject.Utils.ElementUtils;

public class ProductInfoPage 
{

	private final By headerProductInfoPage=By.tagName("h1");
	private final By imagescount=By.xpath("//a[@class='thumbnail']");
	
	private final By productmedatdata=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By productprice=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	
	public Map<String,String> productInfo;
	
	private WebDriver driver;  
	private ElementUtils eleUtils;
	public ProductInfoPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtils=new ElementUtils(driver);
	}
	
	
	public String getHeaderProductInforPage()
	{
		String text=eleUtils.waitForElementVisibility(headerProductInfoPage, AppConstants.DEFAULT_SHORT_WAIT).getText();
		System.out.println("Product info page header value : "+text);
		return text;		
	}
	
	
	public int getProductImagescount()
	{
		int imagecount=eleUtils.waitforElementsVisibility(imagescount, AppConstants.DEFAULT_SHORT_WAIT).size();
		System.out.println("all images count on selected product :" +imagecount);
		return imagecount;
	}
	
	
	
	private void getProductMetaData()
	{
		List <WebElement> metalist=eleUtils.waitforElementsVisibility(productmedatdata, AppConstants.DEFAULT_SHORT_WAIT);
		
		System.out.println("Product metadata size :" +metalist.size());
		
		for(WebElement e:metalist)
		{
			//we need to display data same in hash key value pair like as application
			
			String text=e.getText();
			String meta[]=text.split(":");
			
			String key=meta[0];
			String value=meta[1].trim();
			
			productInfo.put(key, value);
			
		}
	
	}

	
	private void getProductpriceData()
	{
		List <WebElement> pricelist=eleUtils.waitforElementsVisibility(productprice, AppConstants.DEFAULT_SHORT_WAIT);
		
		System.out.println("Product metadata size :" +pricelist.size());
		
		String productPrice=pricelist.get(0).getText();    // it gives $1,202.00 format output
		String ExcludingTextPrice=pricelist.get(1).getText().split(":")[1].trim();  //it print only :$1,000.00 .....from this text=> Ex Tax: $1,000.00
		
		
//		productInfo.put(productPrice,"");   // $1,202.00 ...printed .beacause here no key is given
		System.out.println(productPrice);
		productInfo.put("Ex Tax", ExcludingTextPrice);   // Ex Tax :$1,000.00
		
	
	}
	
	
	
	//creating object of HashMap in below method - in same class  ref variable is declared
	public Map<String, String> allProductInfo()
	{
		productInfo=new HashMap<String, String>();
		
		//calling both methods related productInfor
		
		getProductMetaData();
		getProductpriceData();
		
		
		System.out.println("product info :\n"+productInfo);
		
		return productInfo;
		
	}
	
}

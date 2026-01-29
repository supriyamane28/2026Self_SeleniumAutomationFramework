package com.qa.OpencartProject.Base;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.OpencartProject.DriverFactory.DriverFactory;
import com.qa.OpencartProject.Pages.AccountsPage;
import com.qa.OpencartProject.Pages.LoginPage;
import com.qa.OpencartProject.Pages.ProductInfoPage;
import com.qa.OpencartProject.Pages.RegisterPage;
import com.qa.OpencartProject.Pages.SearchResultPage;
import com.qa.OpencartProject.Utils.AppConstants;
import com.qa.OpencartProject.listeners.TestAllureListener;


//@Listeners({ChainTestListener.class,TestAllureListener.class})
public class BaseTest {

			
		public DriverFactory df ;
		public WebDriver driver;
		public LoginPage lpage; 
		public AccountsPage accPage;
		public SearchResultPage searchResPage;
		public ProductInfoPage productInfoPage;
		
		public Properties proobj;   //creating ref variable to call the method and data from config.peroperties file
		
		public RegisterPage regpage;
		
		
		@BeforeSuite
		public void beforesuitecleanreports()
		{
			cleanOldchaintestReports();
			cleanAllureResults();
			
		}
		
		//this code is for clean chaintest report of old
		public void cleanOldchaintestReports() {
		    File dir = new File(System.getProperty("user.dir") + "/target/chaintest");
		    if (dir.exists()) {
		        deleteDirectory(dir);
		    }
		}

		private void deleteDirectory(File file) {
		    for (File subFile : file.listFiles()) {
		        if (subFile.isDirectory()) {
		            deleteDirectory(subFile);
		        } else {
		            subFile.delete();
		        }
		    }
		    file.delete();
		}
		
	
		//this code is for clean allure report of old
		public void cleanAllureResults() {
		    File allureDir = new File(System.getProperty("user.dir") + "/allure-results");
		    if (allureDir.exists()) {
		        deleteFolder(allureDir);
		    }
		}

		private void deleteFolder(File file) {
		    for (File sub : file.listFiles()) {
		        if (sub.isDirectory()) {
		            deleteFolder(sub);
		        } else {
		            sub.delete();
		        }
		    }
		    file.delete();
		}

		
		
		//in xml file we passed below parameter like <parameter name="browser" value="chrome"/>
		@Parameters({"browser"})
		@BeforeTest
		public void setup(@Optional("chrome") String browsernamevalue)
		{
			 df =new DriverFactory();
			 proobj= df.initProperty();
			 
			 if(browsernamevalue!=null)
			 {
				 proobj.setProperty("browser", browsernamevalue);
			 }
			 
			WebDriver driver=df.initDriver(proobj);  //here passing object of config.properties file class
			 lpage=new LoginPage(driver);
		}
		
		
		// if some test cases are failed then after method below code works
		//AfterMethod run every @Test test cases.if something fails then check if condition mentioned below
		@AfterMethod
		public void attachScreenshot(ITestResult result)
		{
			if(!result.isSuccess())
			{
				ChainTestListener.embed(DriverFactory.getScreenshotAsFile(), "image/png");
			}
			
		}
		
						
		@AfterTest
		public void teardown()
		{
			df.quitbrowser();
			
		}

	

}

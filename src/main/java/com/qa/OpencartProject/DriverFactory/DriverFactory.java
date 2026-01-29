package com.qa.OpencartProject.DriverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.qa.OpencartProject.Exceptions.FrameworkException;
import com.qa.OpencartProject.Utils.AppError;

public class DriverFactory
{

	public WebDriver driver;
	public Properties proobj;
	
	public static ThreadLocal<WebDriver> tlocalDriver=new ThreadLocal<WebDriver>();
	
	private static final Logger log=LogManager.getLogger(DriverFactory.class);
	
	public OptionsManager optionmanagerobj;    // here created object ref varibale for optionmanger class ...need to call headless and incognito mode method
	
	public WebDriver initDriver(Properties proobj)  //pass properties object here
	{
		
		optionmanagerobj=new OptionsManager(proobj);
		String browsername=proobj.getProperty("browser");
		
		
		// System.out.println("You Selected Browser name is : "+browsername);
		log.info("You Selected Browser name is : "+browsername);
		
		
		switch (browsername.trim().toLowerCase()) 
		{
		case "chrome": 
			
			
			/*
			ChromeOptions cp=new ChromeOptions();
			cp.addArguments("--incognito");
			//cp.addArguments(proobj.getProperty("--incognito"));
			
			//driver=new ChromeDriver(cp); instaead of this line ..we are using localthreadDriver object
			//beacause of driver=new ChromeDriver(cp). so we pass statement instead of driver
			tlocalDriver.set(new ChromeDriver(cp));  //set and create the local driver object of particular browser
			
			*/
			
			tlocalDriver.set(new ChromeDriver(optionmanagerobj.getchromoption()));
			
			break;
		
		case "edge": 
			
			/*
			 EdgeOptions eo=new EdgeOptions();
			 
			eo.addArguments("-inprivate");
			//driver=new EdgeDriver(eo);
			tlocalDriver.set(new EdgeDriver(eo)); 
			*/
			tlocalDriver.set(new EdgeDriver(optionmanagerobj.getedgeoption())); 
			break;
		
		case "firefox": 
			/*
			FirefoxOptions fo=new FirefoxOptions();
			fo.addArguments("-private");
		//	driver=new FirefoxDriver(fo);
			tlocalDriver.set(new FirefoxDriver(fo));
			*/
			
			tlocalDriver.set(new FirefoxDriver(optionmanagerobj.getfirefoxoption()));
			break;
		
			
		case "safari": 
	
			//driver=new SafariDriver();
			tlocalDriver.set(new SafariDriver()); 
			break;

		 default:
			 
			 //System.out.println(AppError.invalid_browsername);
			 log.warn(AppError.invalid_browsername);
	            throw new FrameworkException("browser is not supported");
	            
	            
	    }
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(proobj.getProperty("url"));   //read url from config.property file using object proobj
		
		return getDriver();
	}

	
	
//creating method for config.property file.here we given path of the file
	public Properties initProperty()
	{
		//mvn clean install -Denv="qa";
				proobj=new Properties();
				FileInputStream fp=null;
				
				String envName=System.getProperty("env");
				
				try {
					
					if(envName==null)
					{
					log.info("No Env is given hence running the test case on default env : "+ envName);	
					fp= new FileInputStream("src/test/resources/config/config.properties");
					}
					
					else
					{
						
					switch (envName.toLowerCase().trim()) 
					{
					case "dev":
						log.info("Env is given hence running the test case on : "+ envName +" env");	
						fp= new FileInputStream("src/test/resources/config/config_dev.properties");	
						
						break;
						
					case "uat":
						log.info("Env is given hence running the test case on : "+ envName +" env");	
						fp= new FileInputStream("src/test/resources/config/config_uat.properties");	
						
						break;	
						
					case "prod":
						log.info("Env is given hence running the test case on : "+ envName +" env");	
						fp= new FileInputStream("src/test/resources/config/config_prod.properties");	
						
						break;	

					default:
						
					log.error("Wrong Env Name is Passed : "+ envName);
					
					throw new FrameworkException("===INVALID ENV PASSED===");
					
					}	
						
				}
			
					proobj.load(fp);
			
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
				
				return proobj;
	}
	
	
	
	
	
	
	 
	public void quitbrowser()
	{
		getDriver().quit();
	}
	
	
	public void closebrowser()
	{
		getDriver().close();
	}
	
	
	public static WebDriver getDriver()
	{
		return tlocalDriver.get();
	}
	
	public static File getScreenshotAsFile()
	{
		File file=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
	       return file;

	}
	
	
	
}
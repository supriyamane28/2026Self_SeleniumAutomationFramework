package com.qa.OpencartProject.DriverFactory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager
{

	private Properties prop;
	
	private ChromeOptions co;
	
	private FirefoxOptions fo;
	
	private EdgeOptions eo;
	
	
	public OptionsManager(Properties prop)
	{
		this.prop=prop;
	}
	
	
	ChromeOptions getchromoption()
	{
		co= new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			co.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote")))
		{
			co.setCapability("browsername", "chrome");
		}
		
		return co;
	}
	
	
	FirefoxOptions getfirefoxoption()
	{
		fo= new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("private")))
		{
			fo.addArguments("-private");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote")))
		{
			fo.setCapability("browsername", "firefox");
		}
		return fo;
	}
	
	
	EdgeOptions getedgeoption()
	{
		eo= new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("inprivate")))
		{
			eo.addArguments("-inprivate");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote")))
		{
			eo.setCapability("browsername", "edge");
		}
		return eo;
	}
	
	
	
	
}

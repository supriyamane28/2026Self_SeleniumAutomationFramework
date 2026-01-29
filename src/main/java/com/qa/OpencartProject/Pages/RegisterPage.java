package com.qa.OpencartProject.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.OpencartProject.Utils.AppConstants;
import com.qa.OpencartProject.Utils.ElementUtils;

public class RegisterPage
{
	//private By locators
	private final By firstname = By.id("input-firstname");
	private final By lastname = By.id("input-lastname");
	private final By email = By.id("input-email");
	private final By telephone = By.id("input-telephone");
	
	private final By password=By.id("input-password");
	private final By confirmpassword=By.id("input-confirm");
	
	private final By subscribeNo=By.xpath("(//label[@class='radio-inline'])[2]/input[@value=0]");
	private final By subscribeYes=By.xpath("(//label[@class='radio-inline'])[1]/input[@value=1]");
	private final By checkbox=By.xpath("//input[@type=\"checkbox\"]");
	
	private final By continueButton=By.xpath("//input[@type='submit' and @value='Continue']");
	
	private final By sucessmessage=By.tagName("h1");
	private final By logoutLink=By.linkText("Logout");
	private final By registerLink=By.linkText("Register");
	
	private WebDriver driver;    
	private ElementUtils eleUtils;
	
	
	//public page_class constructor
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtils=new ElementUtils(driver);
	}
	
	//public methods
	
	public boolean userRegister(String fname,String lname,String emailid,String tele,String pass,String subscribelabel)
	{
		
		eleUtils.waitForElementPresence(firstname, AppConstants.DEFAULT_SHORT_WAIT).sendKeys(fname);
		eleUtils.doSendKeys(lastname, lname);
		eleUtils.doSendKeys(email, emailid);
		eleUtils.doSendKeys(telephone, tele);
		eleUtils.doSendKeys(password, pass);
		eleUtils.doSendKeys(confirmpassword, pass);
		
		if(subscribelabel.equalsIgnoreCase("yes"))
		{
			eleUtils.doClick(subscribeYes);
		}
		else
		{
			eleUtils.doClick(subscribeNo);
		}
		
		System.out.println("Entered data : Firstname :"+fname+" LastName :" +lname+" email :"+emailid+" Telephone :"+tele);
		
		
		eleUtils.doClick(checkbox);
			
		eleUtils.doClick(continueButton);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//eleUtils.normalwait(AppConstants.DEFAULT_MEDIUM_WAIT);
		
		String text=eleUtils.waitForElementPresence(sucessmessage, AppConstants.DEFAULT_SHORT_WAIT).getText();
		
		System.out.println(text);
		
		
		//after created user its logged in directly.
		//So we need to logout and then click on register link
		eleUtils.doClick(logoutLink);    
		eleUtils.doClick(registerLink);
		
		if(text.equals(AppConstants.USER_REGISTER_SUCESSMESSAGE))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	
	
}

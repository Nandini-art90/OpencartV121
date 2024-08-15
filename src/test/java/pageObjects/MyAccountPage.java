package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	
	public  MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Capture My account element
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgHeading;
	
	
	//Capture logout element
//@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
//WebElement lnkLogout;

@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
WebElement lnkLogout2;


	
	//Verify Myaccount element is displayed or not
	public boolean isMyAccountPageExist()
	{
		try
		{
		return (msgHeading.isDisplayed());//returns true if myaccount page exist else go to catch block,executes false
		}
		catch(Exception e)
		{
			return (false);
		}
				
	}
	
	public void clickLogout() throws InterruptedException
	{
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("argument[0].click()",lnkLogout );
		//Thread.sleep(2000);
		lnkLogout2.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}

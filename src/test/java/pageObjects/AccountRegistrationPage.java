package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageObjects.BasePage;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver )
	{
		super(driver);
	}
	
	//Capture elements in registration page
	
	//capture firstname
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement Firstname;
	
	//capture lasttname
		@FindBy(xpath="//input[@id='input-lastname']")
		WebElement lasttname;
	
	//capture email
		@FindBy(xpath="//input[@id='input-email']")
		WebElement email;		
			
	//capture telephone
		@FindBy(xpath="//input[@id='input-telephone']")
		WebElement telephone;		
	
	//capture password
		@FindBy(xpath="//input[@id='input-password']")
		WebElement pwd;
		
	//capture confirm password
		@FindBy(xpath="//input[@id='input-confirm']")
		WebElement pwd_cnfm;
		
	//capture policy items checkbox
		@FindBy(xpath="//input[@name='agree']")
		WebElement ckdPolicy;
		
	//capture continue button
		@FindBy(xpath="//input[@value='Continue']")
		WebElement btnContinu;
		
	//capture msg confirmation
		@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
		WebElement msgconfmtin;

  //action methods
		//set first name
		public void setFirstName(String fname)
		{
			Firstname.sendKeys(fname);
		}
		
		//set first name
		public void setLasttName(String lname)
		{
			lasttname.sendKeys(lname);
		}
		
		//set first name
		public void email(String mail)
		{
			email.sendKeys(mail);
		}
		
		//set telephone
		public void settelephone(String tele)
		{
			telephone.sendKeys(tele);
		}
		
		//set password
		public void setPassword(String pwd1)
		{
			pwd.sendKeys(pwd1);
		}
			
		//set confirm password.(both passwords of setPassword and setcofirmpwd)
		public void setcofirmpwd(String pwd1)
		{
			pwd_cnfm.sendKeys(pwd1);
		}

		
		public void setPrivacyPolicy()
		{
			ckdPolicy.click();
		}
		
		public void clickContnue()
		{
			btnContinu.click();
		}
				
	
	public String getConfirmationMsg()
	{
		try
		{
			return (msgconfmtin.getText());
		}
		catch(Exception e)
					{
				       return (e.getMessage());
					}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	


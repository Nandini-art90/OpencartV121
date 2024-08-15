package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	 @Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		 logger.info("****Starting TC001_AccountRegistrationTest****");//Info level log .Information like comment
		try
		{
		 HomePage hp=new HomePage(driver);//create homepage class.driver will be passed
		//to page objects class 
		hp.clickMyAccount();
		logger.info("Clicked on myaccount link");
		hp.clickRegister();
		logger.info("Clicked on register link");
		
		AccountRegistrationPage regpage= new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details..");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLasttName(randomeString().toUpperCase());
		regpage.email(randomeString()+"@gmail.com");
		regpage.settelephone(randomeNumber());
		String password=randomeAlphanumeric();//To store same random data in string so that password and confirm password should accept same
		regpage.setPassword(password);
		regpage.setcofirmpwd(password);
		regpage.setPrivacyPolicy();
		regpage.clickContnue();
		
		logger.info("Validating expected message..");
		String cnfmsg=regpage.getConfirmationMsg();
		System.out.println(cnfmsg);
		if(cnfmsg.equals("Your Account Has Been Created!"))//Failing intentionally
		{
			logger.info("Test passed..."); 
			Assert.assertTrue(true);
		}
		
		//Assert.assertEquals(cnfmsg, "Your Account Has Been Created!!!");//We can write this as well instead of if
		else
		{
			logger.error("Test failed..."); 
			logger.debug("Debug logs..."); //debug level log
			Assert.assertTrue(false);
		}
	}
	
	 catch(Exception e)
	 {
		
		Assert.fail();//test method fails
	 }
		 logger.info("****Starting TC001_AccountRegistratinTest Finished***");	
	}
	 
	
} 
	 



	  	
		
		
	
	 
	 
	 
	 
	 
	 
	 
	 


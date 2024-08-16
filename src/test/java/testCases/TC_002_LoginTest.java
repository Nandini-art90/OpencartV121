package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		logger.info("*****Starting Testcase*********");//Info log
		
		try
		{
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();//click on my account
		hp.clickLogin();//click on login
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));//access data from config.properties file using pageobjects
		lp.setPassword(p.getProperty("password"));//access data from config.properties file using pageobjects
		lp.clickLogin();
		
		//Verify myaccount
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetpage=macc.isMyAccountPageExists();
		//Assert.assertEquals(targetpage, true);//If page exist,returns true else return"login failed"
		Assert.assertTrue(targetpage);//same as above
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("*********Finished TC_002_LoginTest*********");
		
		
	}

}

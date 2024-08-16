package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {
	
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")
	//dataProvider="LoginData"=name of the dataprovider .dataProviderClass=DataProviders.class=class in which dataprovider is present.This is used when dataprovider is in different class/package
	
	public void verify_LoginDDT(String email,String password,String exp)//Takes email ,pwd and res(exp)from dataprovider(internally it will take from excel)
	{
		logger.info("*******Starting TC_003_LoginDDTest******");
		
		try
		{
		//HomePage
				HomePage hp=new HomePage(driver);
				hp.clickMyAccount();//click on my account
				hp.clickLogin();//click on login
				
				//LoginPage
				LoginPage lp=new LoginPage(driver);
				lp.setEmail(email);//access data from config.properties file using pageobjects
				lp.setPassword(password);//access data from config.properties file using pageobjects
				Thread.sleep(1000);
				lp.clickLogin();
				Thread.sleep(1000);
				
				
				//Verify myaccount
				MyAccountPage macc=new MyAccountPage(driver);
				boolean targetpage=macc.isMyAccountPageExists();
				//Assert.assertEquals(targetpage, true);//If page exist,returns true else return"login failed"
				//Assert.assertTrue(targetpage);//same as above
				
				/*Testcase scenarios 
				data is valid-login success-test pass-logout
				              login unsuccess-test fail
				    
				
				              
				*/
				if(exp.equalsIgnoreCase("Valid"))//equalsIgnoreCase ignores case fo the text.It will compare result in excel sheet with valid.Valid means login success
				{
					if(targetpage==true)//If my account page shown after successful login,
					{
						macc.clickLogout();//logout from the page
						
						Assert.assertTrue(true);//make test method pass
						
					}
					else
					{
						Assert.assertTrue(false);//If login unsuccess,then test fails
					}
				}
				
			/*	data is invalid-login success-test fail-logout
	              login unsuccess-test pass	
				
				*/
				if(exp.equalsIgnoreCase("Invalid"))//equalsIgnoreCase ignores case fo the text.It will compare result in excel sheet with invalid.Valid means login unsuccess
				{
					if(targetpage==true)//If my account page shown after successful login,
					{
						macc.clickLogout();//logout from the page
						Thread.sleep(1000);
						Assert.assertTrue(false);//make test method fail
						
					}
					else
					{
						Assert.assertTrue(true);//If login success,then test method  pass
					}
				
				}
		}
		
		catch(Exception e)
		{
			Assert.fail("An Exception occured  " +e.getMessage());
		}
		
				logger.info("*******Finished TC_003_LoginDDTest******");
				
				
				
				
				
				
				
				
				
				
	}
	












}

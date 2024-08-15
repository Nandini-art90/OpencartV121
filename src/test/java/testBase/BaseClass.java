package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;//log4j variable
	public Properties p;//class related to properties file
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	
	{
		FileReader file=new FileReader("./src//test//resources//config.properties");//to get location of property file.  ./=refers current project location
		p=new Properties();//create object for properties class
		p.load(file);//loads property file
		
		logger=LogManager.getLogger(this.getClass());// getLogger(this.getClass()=captures logs from currently running testcase class and stores that in logger variable
		//this.getClass()=stores logs in xml fle in test\resources,those will be stored in Logger variable
		
		
		//GRID SETUP(same for standalone and distributed setup)
		//If system is remote
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) //verify system is remote
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();//class to set os and browser
			
			//Operating System selection
			if(os.equalsIgnoreCase("Windows"))//verifying os in master xml is windows
			{
				capabilities.setPlatform(Platform.WIN11);//sets os to windows 11
			}
			else if(os.equalsIgnoreCase("Linux"))//verifying os in master xml is linux
			{
				capabilities.setPlatform(Platform.LINUX);//sets os to linux
			}
			else if(os.equalsIgnoreCase("Mac"))//verifying os in master xml is mac
			{
				capabilities.setPlatform(Platform.MAC);//sets os to mac
			}
			else
			{
				System.out.println("No matching Operating System");
				return;//auto exit from if 
			}
			
			//Browser selection
			switch(br.toLowerCase())//capture browser from master xml and compare with case
			
			{
			case "chrome" :capabilities.setBrowserName("chrome");break;
			case "edge" :capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox" :capabilities.setBrowserName("firefox");break;
			default :System.out.println("No matching browser");  return;//auto exit from switch 
			
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);//launch in grid setup
		}
			
		
			

		//If system is local
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) //verify system is local
		{
		switch(br.toLowerCase())
		{
		case "chrome" :driver=new ChromeDriver();break;
		case "edge" :driver=new EdgeDriver();break;
		case "firefox" :driver=new FirefoxDriver();break;
		default : System.out.println("Invalid browser");return;
		
		}
		}
		
		//driver=new ChromeDriver();
		driver.manage().deleteAllCookies();//deletes all cookies
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("http://localhost/opencart/upload/index.php");//Hardcoded value
		driver.get(p.getProperty("appURL2"));//Reading url from properties file.Here taking value dynamically from properties file
		driver.manage().window().maximize();
	}
 
	 @AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() throws InterruptedException
	{
		 Thread.sleep(2000);
		driver.quit();
	}

	 public String randomeString()
	 {
		String generatedstring= RandomStringUtils.randomAlphabetic(5);//RandomStringUtils is from commom.lang3 library
	//randomAlphabetic=Takes 5  alphabetic letters randomly 
		
	  return generatedstring;
	  
	 }
	 
	 //To generate random numbers
	 public String randomeNumber()
	 {
		String generatednumber= RandomStringUtils.randomNumeric(10);//RandomStringUtils is from commom.lang3 library
	//randomAlphabetic=Takes 10  random numbers randomly 
		return generatednumber;
	 } 

	 
	 //To generate random alphabets and numbers
	public String randomeAlphanumeric()
	{
		//String generatednumber= RandomStringUtils.randomAlphanumeric(10);
		 String generatedstring= RandomStringUtils.randomAlphabetic(3);
		 String generatednumber= RandomStringUtils.randomNumeric(3);
		return (generatedstring+generatednumber);
		 
		// return (generatedstring+"@"+generatednumber); //random alphanumeric with special characters
	 }  
	 
	public String captureScreen(String tname) throws IOException {
//tname=name of the screenshot.usually same as testmethod name
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());//current timestamp
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;//assign driver to baseclass interface
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);//screenshot default location
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";//Desired location of screenshot and name of the screeshot
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);//copy sourcefile to targetfile
			
		return targetFilePath;//return target file path
 
	}
}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 


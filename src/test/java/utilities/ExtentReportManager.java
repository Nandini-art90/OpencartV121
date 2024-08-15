package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;

//Extent report 5.x...//version

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;//stores report name

	public void onStart(ITestContext testContext) {
		//testContext=stores test method details and which test executed
		
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");//date format
		Date dt=new Date();//date class object creation
		String currentdatetimestamp=df.format(dt);//passing date format 
		*/
		
		//To generate date and time
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// current time stamp.same as above
		repName = "Test-Report-" + timeStamp + ".html";//reportname+timestamp+file extension
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the report
         //We should not hardcode report name as it s difficult maintain history of reports.To overcome this add timestamp to name of the report 
		sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of report
		sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		
		//To  set common info  dynamically
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "opencart");//project specific,we should hardcode
		extent.setSystemInfo("Module", "Admin");//project specific,we should hardcode
		extent.setSystemInfo("Sub Module", "Customers");//project specific,we should hardcode
		extent.setSystemInfo("User Name", System.getProperty("user.name"));//System.getProperty("user.name")=Returns current user of the system.Tester name
		extent.setSystemInfo("Environemnt", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		//getCurrentXmlTest()=returns the xml in which test method executed.getParameter("os")=Returns the OS info where test is ran from the xml
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		//getCurrentXmlTest()=returns the xml in which test method executed.getParameter("browser")=Returns the browser info where test is ran  from the xml
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();//to get group info
		if(!includedGroups.isEmpty()) {//verify group name is there or not in xml
		extent.setSystemInfo("Groups", includedGroups.toString());//includedGroups.toString()=returns group name in report
		}
	}

	public void onTestSuccess(ITestResult result) {
		//result captures result info of test method
	
		test = extent.createTest(result.getTestClass().getName());//createTest=create report
		//result.getTestClass()=returns which class is executed.getName()=Returns name of the class.
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report test methodwise
		test.log(Status.PASS,result.getName()+" got successfully executed");//,result.getName()=name of the class
		
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {//To capture screenshot
			String imgPath = new BaseClass().captureScreen(result.getName());//call capturescreenshot method from baseclass through baseclass object as it is from 
			//different class and returns the path of the screenshot.result.getName()=return the failed test method name 
			//Since we create new object of baseclas, new driver will be in the baseclass,so conflict arises which driver extentreport should consider.
			//To avoid this,we make driver as static in baseclass.
			test.addScreenCaptureFromPath(imgPath);//add screenshot to report
			
		} catch (IOException e1) {
			e1.printStackTrace();//To avoid filenotfoundexception (if screenshot is not available in the location/screeshot not taken properly)
		//printStackTrace()=prints some warning messages
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		//To auto open report in browser
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;//location of report
		File extentReport = new File(pathOfExtentReport);//storig in file class object
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());//opens report in browser automatically.Desktop is predefined class
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		/*      //To auto send email. add apache email dependency in pom.xml
		
		 try {
			  URL url = new  URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);//url form of report
		  
		  // Create the email message 
		 ImageHtmlEmail email = new ImageHtmlEmail();
		  email.setDataSourceResolver(new DataSourceUrlResolver(url));
		  email.setHostName("smtp.googlemail.com"); 
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator("opencartdemo@gmail.com","Abc@1234")); 
		  email.setSSLOnConnect(true);
		  email.setFrom("opencartdemo@gmail.com"); //Sender
		  email.setSubject("Test Results");
		  email.setMsg("Please find Attached Report....");
		  email.addTo("pavankumar.busyqa@gmail.com"); //Receiver .we can add muliple emails
		  //distribution mail=add multiple emails in single mail id.
		  email.attach(url, "extent report", "please check report..."); //attach report
		  email.send(); // send the email 
		  }
		  */
			  
		  /*catch(Exception e) 
		  { 
			  e.printStackTrace(); 
			  }
			  Finally
		 */
		 
	}

}

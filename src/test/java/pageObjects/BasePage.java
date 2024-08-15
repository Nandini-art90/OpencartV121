package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//THIS IS PARENT OF ALL PAGE OBJECT CLASSES
public class BasePage {
	WebDriver driver;
	
	//THIS IS SAME CONSTRUCTOR FOR ALL PAGE OBJECTS
	public BasePage(WebDriver driver)//driver will take 
	//driver value from pageobject class(which internally access driver value from testcase class) and initiates the webelements 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

}

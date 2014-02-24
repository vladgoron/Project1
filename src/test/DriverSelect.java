package test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverSelect {
	  static WebDriver driver= null;
	  
	public static WebDriver driverSelect(String string){
		
		
		if (("firefox").equals(string.toLowerCase())){driver = new FirefoxDriver();}
		else 
		if (("chrome").equals(string.toLowerCase())){
			System.setProperty("webdriver.chrome.driver","D:\\Kit\\Selenium\\chromedriver.exe");
			driver = new ChromeDriver();}
		else 
		if (("explorer").equals(string.toLowerCase())){
			System.setProperty("webdriver.ie.driver","D:\\Kit\\Selenium\\IEDriverServer.exe");		
			driver = new InternetExplorerDriver();}
		
		
			
	return driver;
	}
}

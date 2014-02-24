package test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class IMDBHomePage {
	
	@FindBy(xpath=".//*[@id='navbar-submit-button']")
	public WebElement submitLocator;

	
	@FindBy(xpath = ".//*[@id='navbar-query']")
	public WebElement searchLocator;
	
	private WebDriver driver; 
	

	public IMDBHomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
	
		Assert.assertEquals(driver.getTitle(), "IMDb - Movies, TV and Celebrities", "Current page is not the home page");
	}
	 
	public IMDBSearchResults searchBar(String title){
		
		searchLocator.sendKeys(title);
		submitLocator.click();

	return new IMDBSearchResults(driver);	
	}
	
}

package test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;




public class MoviePage {
	
	@FindBy(xpath=".//*[@id='overview-top']/div[3]/div[1]")
	public WebElement ratingLocator;

	public MoviePage(WebDriver driver,String title){
		PageFactory.initElements(driver, this);
		System.out.println(driver.getTitle());
		Assert.assertTrue(driver.getTitle().toLowerCase().contains(title.toLowerCase()),  "This is not the movie page");
	}
	
	public String getRating(String title){
		String rating = ratingLocator.getText();
						
		return rating;
	}	
}

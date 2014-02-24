package test;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;




public class IMDBSearchResults {
	@FindBy(xpath=".//*[@id='main']/div/div[2]/table/tbody")
	public WebElement searchResultLocator;

	private WebDriver driver;

	public IMDBSearchResults(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
		Assert.assertEquals(driver.getTitle(), "Find - IMDb", "This is not the Find page");
	}
		
	public MoviePage getSearchResult(String title, String year){
		List <WebElement> found = searchResultLocator.findElements(By.className("result_text"));
		for (WebElement find : found)
			{
			String txt = find.getText();
			System.out.println(txt);
			if (txt.contains(year)) {
				
				if (txt.toLowerCase().contains(title.toLowerCase())){

					WebElement fnd = find.findElement(By.tagName("a"));

				fnd.click();
				break;
				}}
			}
	return new MoviePage(driver,title);
		
	}
}	

package testng;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.IMDBHomePage;
import test.IMDBSearchResults;
import test.MoviePage;

public class Test1 {

	WebDriver driver = null;

	@Parameters({ "platform", "browser", "version", "url" })
	@BeforeTest(alwaysRun=true)
	public void setup(String platform, String browser, String version,
			String url) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		
				if (platform.equalsIgnoreCase("windows")) {caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);}
				if (platform.equalsIgnoreCase("LINUX")) {caps.setPlatform(Platform.LINUX);}

				if (browser.equalsIgnoreCase("firefox")) {
					caps = DesiredCapabilities.firefox();
				}
				if (browser.equalsIgnoreCase("internet explorer")) {	
					System.setProperty("webdriver.ie.driver","C:\\SeleniumGridSetup\\IEDriverServer.exe");	
					caps = DesiredCapabilities.internetExplorer();			
				}
				if (browser.equalsIgnoreCase("chrome")) {
					caps = DesiredCapabilities.chrome();
				}

				caps.setVersion(version);

				driver = new RemoteWebDriver(new URL("http://localhost:7321/wd/hub"), caps);
				driver.get(url);
			}


	@Parameters({"title","year"})
	@Test
	private void runTest(String title, String year) {

		IMDBHomePage hPage = new IMDBHomePage(driver);
		IMDBSearchResults searchPage = hPage.searchBar(title);
		MoviePage mvi = searchPage.getSearchResult(title, year);

		String rating = mvi.getRating(title);

		System.out.println("Imdb rating is " + rating);
		driver.close();

	}


}

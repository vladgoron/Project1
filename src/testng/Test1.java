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
					System.setProperty("webdriver.ie.driver","D:\\Kit\\Selenium\\IEDriverServer.exe");	
					caps = DesiredCapabilities.internetExplorer();			

				}
				if (browser.equalsIgnoreCase("chrome")) {
					caps = DesiredCapabilities.chrome();
				}

				caps.setVersion(version);

				driver = new RemoteWebDriver(new URL("http://localhost:7321/wd/hub"), caps);
				driver.get(url);
			}

		
//    <test name="Internet Explorer">
//    <parameter name="platform" value="windows"></parameter>
//    <parameter name="version" value=""></parameter>
//    <parameter name="browser" value="firefox"></parameter>
//    <parameter name="url" value="http://www.imdb.com"></parameter>
//
//    <classes>
//        <class name="testng.Test1" />
//    </classes>
//</test>
	

	//
//	 <parameter name="title" value="Desolation of smaug"></parameter>
//	 <parameter name="year" value="2013"></parameter>
	//
//	@DataProvider
//	public Object[][] Data() {
//
//		Object[][] data = new Object[2][2];
//
//		data = Utils.readXLSXFile("C:\\Users\\vlad.goron\\workspace\\PageObjectProject\\src\\testng\\data.xls",0);
//
//		return data;
//
//	}
	@Parameters({"title","year"})
	@Test//(dataProvider = "Data")
	private void runTest(String title, String year) {

//		 driver.get("http://imdb.com");
		IMDBHomePage hPage = new IMDBHomePage(driver);
		IMDBSearchResults searchPage = hPage.searchBar(title);
		MoviePage mvi = searchPage.getSearchResult(title, year);

		String rating = mvi.getRating(title);

		System.out.println("Imdb rating is " + rating);
		driver.close();

	}


}

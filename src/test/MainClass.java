package test;

import org.openqa.selenium.WebDriver;



public class MainClass {

	public static void main(String[] args) {

		String browser = "chrome";
	
		String title="thor";
		
		String year = "2013";
		
		WebDriver driver = DriverSelect.driverSelect(browser);
		driver.get("http://imdb.com");
	
		IMDBHomePage hPage = new IMDBHomePage(driver);
		IMDBSearchResults searchPage = hPage.searchBar(title);
		MoviePage mvi = searchPage.getSearchResult(title,year);
		
		String rating = mvi.getRating(title);
		
		System.out.println("Imdb rating is "+ rating);
		
		driver.close();
	}


	
}

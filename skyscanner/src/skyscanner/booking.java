package skyscanner;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import javax.print.attribute.standard.Fidelity;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class booking {
	
	public WebDriver driver = new ChromeDriver(); // intialize driver from chrome driver 
	public static String driver_path = "chromedriver.exe"; // path for chrome .exe driver
	public static String url = "https://www.skyscanner.net"; // base url of the website
	public static WebElement origin; 
	public static WebElement destination;
	public static WebElement trip_type;
	public static WebElement date;
	public static WebElement search_btn;
	public static String expected_url = "https://www.skyscanner.net/transport/flights/cai/lond/200314/?adults=1&children=0&adultsv2=1&childrenv2=&infants=0&cabinclass=economy&rtn=0&preferdirects=false&outboundaltsenabled=false&inboundaltsenabled=false&ref=home";
	
	@BeforeTest
	public void setup() {
		// using chrome driver and set window property to be maximum
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver.manage().window().maximize();
        driver.get(url);
    
        
	}
	
	@Test
	public void book_flight() throws Exception{	
		
		
		trip_type = driver.findElement(By.xpath("//*[@id=\"flights-search-controls-root\"]/div/div/form/div[1]/div/label[2]/div"));
        origin = driver.findElement(By.id("fsc-origin-search"));
        driver.findElement(By.id("fsc-destination-search"));
        search_btn = driver.findElement(By.xpath("//*[@id=\"flights-search-controls-root\"]/div/div/form/div[3]/button"));
        // select one way journy from cairo to london
		trip_type.click();
		
		origin.sendKeys("cairo");
		destination.sendKeys("london");
		
		// enter journey date 
		
		// search trips 
		search_btn.click();
		
       // File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       		
		// press on the cheapest flight button
		driver.findElement(By.xpath("//*[@id=\"app-root\"]/div[2]/div[2]/div[1]/div[2]/button[2]")).click();
		String acutual_url = driver.getCurrentUrl();
		Assert.assertEquals(acutual_url, expected_url);
	}
	
	@AfterTest
	public void end_test() {
		//driver.quit();
	}
}
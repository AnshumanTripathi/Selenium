package selenium.selenium.jetairwaystest;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.http.HttpResponse;

public class JetAirwaysTest {

	WebDriver driver;
	int invalidImageCount;

	// Initialize Driver
	public JetAirwaysTest() {
		this.driver = new FirefoxDriver();
	}

	// Start Browser
	public void initBrowser() {
		driver.get("http://www.jetairways.com/EN/IN/Home.aspx");
		driver.manage().window().maximize();
	}

	// Check Page Title
	public void checkTitle() {
		String title = driver.getTitle();
		System.out.println(title);

		// Check Title
		if (title.equals("Book Air Tickets - International & Domestic Flights - Jet Airways")) {
			System.out.println("Title Valid!");
		}
	}

	// Check Flight Input
	public void checkInput() {
		WebElement fromFieldText = driver.findElement(By.id("ObeFlights1_autoOrigin_AutoText"));
		fromFieldText.sendKeys("Mumbai");
		WebElement toText = driver.findElement(By.id("ObeFlights1_autoDestination_AutoText"));
		toText.sendKeys("Chennai");

		// Check Validation
		if (fromFieldText.getAttribute("value").isEmpty()) {
			System.out.println("\nFrom field Validation Verified!");
		} else {
			System.out.println("From Field Validation Failed!");
		}

	}

	// Check Return Date Function
	public void checkReturnDate() {
		System.out.println("\nBefore clicking");
		System.out.println(driver.findElement(By.id("Return")).isEnabled());
		WebElement oneWayRadioButton = driver.findElement(By.id("OneWay"));
		oneWayRadioButton.click();
		System.out.println("After Clicking");
		System.out.println(driver.findElement(By.id("txtEndDate")).isEnabled());
	}

	// Check BookLink Tab
	public void checkBookLink() {
		System.out.println("\nBefore Clicking Book");
		System.out.println(driver.findElement(By.id("HeaderLinks_liJetEscapes")).isDisplayed());
		driver.findElement(By.linkText("BOOK")).click();
		System.out.println("\nAfter Clicking Book");
		System.out.println(driver.findElement(By.id("HeaderLinks_liJetEscapes")).isDisplayed());
	}

	// Check MyTrip Tab
	public void checkMyTripLink() {
		System.out.println("\nBefore My Trip");
		System.out.println(driver.findElement(By.id("myTrip")).isDisplayed());
		driver.findElement(By.linkText("MY TRIP")).click();
		System.out.println("\nAfter Clicking My Trip");
		System.out.println(driver.findElement(By.id("myTrip")).isDisplayed());
	}

	// Check login dropdown
	public void checkLoginLink() {
		System.out.println("\nBefore clicking Login");
		System.out.println(driver.findElement(By.id("jp-login-panel")).isDisplayed());
		driver.findElement(By.linkText("LOGIN")).click();
		System.out.println("\nAfter clicking Login");
		System.out.println(driver.findElement(By.id("jp-login-panel")).isDisplayed());
	}

	// Check Images
	public void checkImages() {
		try {
			List<WebElement> imagesList = driver.findElements(By.tagName("img"));
			System.out.println("\nTotal no. of images are " + imagesList.size());
			for (WebElement imgElement : imagesList) {
				if (imgElement != null) {
					verifyimageActive(imgElement);
				}
			}
			System.out.println("Total no. of invalid images are " + invalidImageCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	// Check Image Sources
	public void verifyimageActive(WebElement imgElement) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
			org.apache.http.HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() != 200)
				invalidImageCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Change Internal Landing page
	public void checkInternalLanding() throws InterruptedException {
		System.out.println("\nBefore clicking Plan Your Travel");
		System.out.println(driver.findElement(By.id("planTravel")).isDisplayed());
		System.out.println("clicking plan your travel");
		driver.findElement(By.linkText("PLAN YOUR TRAVEL")).click();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.id("planTravel")).isDisplayed());

		System.out.println("\nBefore clicking Travel Information");
		System.out.println(driver.findElement(By.id("travelInfo")).isDisplayed());
		System.out.println("clicking plan your travel");
		driver.findElement(By.linkText("TRAVEL INFORMATION")).click();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.id("travelInfo")).isDisplayed());

		System.out.println("\nBefore clicking Jet Privilege");
		System.out.println(driver.findElement(By.id("privilege")).isDisplayed());
		System.out.println("clicking Jet Privilege");
		driver.findElement(By.linkText("JETPRIVILEGE")).click();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.id("privilege")).isDisplayed());

		System.out.println("\nBefore clicking Jet Experience");
		System.out.println(driver.findElement(By.id("jetExp")).isDisplayed());
		System.out.println("clicking Jet Experience");
		driver.findElement(By.linkText("JET EXPERIENCE")).click();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.id("jetExp")).isDisplayed());
	}

	// Check Search Functionality
	public void checkSearch() throws InterruptedException {
		String[] input = { "Mumbai", "Chennai", "San Fransisco", "Abu Dhabi", " ", "!^", "0", "Mobile" };
		for (int i = 0; i < input.length; i++) {
			WebElement searchBox = driver.findElement(By.id("searchInput"));
			searchBox.click();
			System.out.println("Checking.... " + input[i]);
			searchBox.sendKeys(input[i]);
			Thread.sleep(2000);
			searchBox.sendKeys(Keys.RETURN);
			Thread.sleep(5000);
			System.out.println("Search Successful?");
			System.out.println(driver.findElement(By.id("MainBody_IWOVID_item_1_lbl_PageInfo")).isDisplayed());
			driver.findElement(By.className("logo")).click();
			Thread.sleep(2000);
		}
	}

	public void checkBackToTop() {
		driver.findElement(By.className("back-to-top")).click();
	}

	// Check All Links in Footer
	public void checkFooterElements() throws InterruptedException {
		WebElement footer = driver.findElement(By.tagName("footer"));
		System.out.println(footer.findElements(By.tagName("a")).size());
		List<WebElement> footlinks = footer.findElements(By.tagName("a"));
		int i = footer.findElements(By.tagName("a")).size() - 1;
		for (int j = 0; j < i - 1; j++) {
			footer = driver.findElement(By.tagName("footer"));
			footer.findElements(By.tagName("a")).get(j).getText();
			footer.findElements(By.tagName("a")).get(j).click();
			Thread.sleep(5000);
			System.out.println(driver.getTitle());
			
			//One Page found with a changed footer style, leading to automation faliure
			//End Condition on the final to get back to landing page	
			if (driver.getTitle().equals("Sitemap - JetAirways.com")) {
				driver.findElement(By.className("logo")).click();
				break;
			}
		}

	}

	public void closeBrowser() {
		driver.close();
	}
}

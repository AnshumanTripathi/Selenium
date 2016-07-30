package selenium.selenium;

import selenium.selenium.jetairwaystest.JetAirwaysTest;

public class SeleniumTest {
	public static void main(String[] args) throws InterruptedException { 
		
		JetAirwaysTest jetTest = new JetAirwaysTest();
		
		//Initialize Browser
		jetTest.initBrowser();
		Thread.sleep(2000);
		
		// Check Title of the Page
		jetTest.checkTitle();
		Thread.sleep(2000);

		// Check Input
		jetTest.checkInput();
		Thread.sleep(2000);

		// Check Return Date for One Way
		jetTest.checkReturnDate();
		Thread.sleep(2000);
		
		//Check Book Link
		jetTest.checkBookLink();
		Thread.sleep(2000);
		
		//Check My Trip Link
		jetTest.checkMyTripLink();
		Thread.sleep(2000);
		
		//Check Login Link
		jetTest.checkLoginLink();
		Thread.sleep(2000);
		
		//Check Search
		jetTest.checkSearch();
		Thread.sleep(2000);
		
		//Click internal Landing
		jetTest.checkInternalLanding();
		Thread.sleep(2000);
		
		//Check Back to Top
		jetTest.checkBackToTop();
		Thread.sleep(2000);
		
		//Check Footer Elements
		jetTest.checkFooterElements();
		Thread.sleep(2000);
		
		//Check for Broken Images
		jetTest.checkImages();
		Thread.sleep(2000);
		
		// close Browser
		jetTest.closeBrowser();
	}
}

package run;

import com.TestWebPages;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.WebDriverManager;


public class RunWebPages {



	private WebDriver driver;
	TestWebPages pa = new TestWebPages();
	
	
	@BeforeClass(groups = {"Chromium-Browser"})
	public void setUp() throws Exception {
		String hub = "localhost:5556";
		driver = WebDriverManager.startDriver("Chromium-Browser", hub);
	}
	
	
	@AfterClass(alwaysRun = true, groups = {"Chromium-Browser"})
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	
	@Test(groups = {"Chromium-Browser"})
	public void runOpenSite() throws Exception {
		pa.openSite(driver);
	}
	
	
	@Test(dependsOnMethods = {"runOpenSite"}, groups = {"Chromium-Browser"})
	public void runCheckCurrentDate() throws Exception {
		pa.checkCurrentDate(driver);
	}
	
	
	@Test(dependsOnMethods = {"runCheckCurrentDate"}, groups = {"Chromium-Browser"})
	public void runTestBackgroundColour() throws Exception {
		pa.checkBackGroundColour(driver);
	}
	
	
	@Test(dependsOnMethods = {"runTestBackgroundColour"}, groups = {"Chromium-Browser"})
	public void runMandatoryFieldPetName() throws Exception {
		pa.checkMandatoryFieldPetName(driver);
	}
	
	
	@Test(dependsOnMethods = {"runTestBackgroundColour"}, groups = {"Chromium-Browser"})
	public void runMandatoryFieldPetStatus() throws Exception {
		pa.checkMandatoryFieldPetStatus(driver);
	}
		
	
	@Test(dependsOnMethods = {"runTestBackgroundColour"}, groups = {"Chromium-Browser"})
	public void runCheckNewPetCreateButton() throws Exception {
		String pet = "Horse";
		String status = "Finished";
		pa.validateNewPetCreateButton(driver, pet, status);
	}
	
	
	@Test(dependsOnMethods = {"runCheckNewPetCreateButton"}, groups = {"Chromium-Browser"})
	public void runCheckNewPetEnterKey() throws Exception {
		String pet = "Pig";
		String status = "On hold";
		pa.validateNewPetEnterKey(driver, pet, status);
	}
	
	
	@Test(dependsOnMethods = {"runCheckNewPetEnterKey"}, groups = {"Chromium-Browser"})
	public void runCheckTabFocus() throws Exception {
		 pa.checkTabFocus(driver);
	}
	 
	 
	@Test(dependsOnMethods = {"runCheckTabFocus"}, groups = {"Chromium-Browser"})
	public void runCheckShiftTabFocus() throws Exception {
		 pa.checkShiftTabFocus(driver);
	}
	 
	 
	@Test(dependsOnMethods = {"runCheckShiftTabFocus"}, groups = {"Chromium-Browser"})
	public void runViewListOfPets() throws Exception {
		 pa.viewListOfPets(driver);
	}
	 
	 
	@Test(dependsOnMethods = {"runViewListOfPets"}, groups = {"Chromium-Browser"})
	public void runQuitEditEscapeKey() throws Exception {
		 pa.quitEditESCKey(driver);
	}
	 
	 
	@Test(dependsOnMethods = {"runQuitEditEscapeKey"}, groups = {"Chromium-Browser"})
	public void runQuitEditEnterKey() throws Exception {
		 pa.quitEditEnterKey(driver);
	}
	 
	
	@Test(dependsOnMethods = {"runQuitEditEnterKey"}, groups = {"Chromium-Browser"})
	public void runQuitEditClickBody() throws Exception {
		 pa.quitEditClickBody(driver);
	}
	 
	
	@Test(dependsOnMethods = {"runQuitEditClickBody"}, groups = {"Chromium-Browser"})
	public void runValidateRenderingTime() throws Exception {
		String petName = "Doggy";
		String petStatus = "Avaliable";
		pa.createHundredPets(driver, petName, petStatus);
		setUp();
		pa.timePageRendering(driver);
	}
}

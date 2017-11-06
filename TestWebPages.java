package com;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.testng.Assert;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.CreatePetPage;


public class TestWebPages {

		
	//Open the Web Site
	public void openSite(WebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		driver.get("https://qa-petstore.herokuapp.com/");
		Thread.sleep(5000);
	}
	
	
	//Check the Current date
	public void  checkCurrentDate(WebDriver driver) throws Exception {
		DateTimeFormatter lcd = DateTimeFormatter.ofPattern("dd-MM-yyy");
		LocalDate localDate = LocalDate.now();
		System.out.println("Date of the Day : = " +lcd.format(localDate)); // print local date
		String browserDate = CreatePetPage.locateDate(driver).getText();
		Assert.assertTrue(driver.getPageSource().contains(browserDate));
		Assert.assertEquals(lcd.format(localDate), browserDate);
	}
	
	
	//Check the background colour
	public void checkBackGroundColour(WebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		String webSafeColourForBlack = "#333333";
		String backGroundColour = CreatePetPage.getDateBackgroundColour(driver).getCssValue("background-color").toString();
		System.out.println("Background Colour in rgba : = " + backGroundColour);
		
		//Split the CSS value of RGB
		  String[] numbers = backGroundColour.replace("rgba(", "").replace(")", "").split(",");
		  int number1=Integer.parseInt(numbers[0]);
		  numbers[1] = numbers[1].trim();
		  int number2=Integer.parseInt(numbers[1]);
		  numbers[2] = numbers[2].trim();
		  int number3=Integer.parseInt(numbers[2]);
		  String hexValue = String.format("#%02x%02x%02x", number1,number2,number3);
		  System.out.println("Background Colour in Hex : = " +hexValue);
		  Assert.assertEquals(webSafeColourForBlack, hexValue);
	}
	
	
	//Check if Pet's name field is mandatory
	public void checkMandatoryFieldPetName(WebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		CreatePetPage.enterPetname(driver).clear();
		CreatePetPage.enterPetname(driver).sendKeys("Tiger");
		CreatePetPage.enterPetStatus(driver).clear();
		CreatePetPage.clickCreatePet(driver).click();
	    Thread.sleep(2000);
	    Assert.assertFalse(driver.getPageSource().contains("Tiger"));	
	}
	
	
	//Check if Pet's name status field is mandatory
	public void checkMandatoryFieldPetStatus(WebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		CreatePetPage.enterPetname(driver).clear();
		CreatePetPage.enterPetStatus(driver).clear();
		CreatePetPage.enterPetStatus(driver).sendKeys("StatusTest");
		CreatePetPage.clickCreatePet(driver).click();
	    Thread.sleep(2000);
		Assert.assertFalse(driver.getPageSource().contains("StatusTest"));
	}

	
	//Validate the CreateButton to create new Pet
	public void validateNewPetCreateButton(WebDriver driver, String pet, String status) throws Exception {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		CreatePetPage.enterPetname(driver).clear();
		CreatePetPage.enterPetname(driver).sendKeys(pet);
		CreatePetPage.enterPetStatus(driver).clear();
		CreatePetPage.enterPetStatus(driver).sendKeys(status);
		CreatePetPage.clickCreatePet(driver).click();
	    Thread.sleep(3000);
	    Assert.assertTrue(driver.getPageSource().contains(pet));
	    Thread.sleep(2000);
	    Assert.assertTrue(driver.getPageSource().contains(status));
	    Thread.sleep(2000);
	}
	
	
	//Validate EnterKey to create new Pet
	public void validateNewPetEnterKey(WebDriver driver, String pet, String status) throws Exception {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		CreatePetPage.enterPetname(driver).clear();
		CreatePetPage.enterPetname(driver).sendKeys(pet);
		CreatePetPage.enterPetStatus(driver).clear();
		CreatePetPage.enterPetStatus(driver).sendKeys(status);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains(pet));
		Thread.sleep(2000);
	    Assert.assertTrue(driver.getPageSource().contains(status));
	}
	
	
	//Check Tab focus starting from Pet name field
	public void checkTabFocus(WebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Robot r = new Robot();
		CreatePetPage.enterPetname(driver).click();
		WebElement petName = CreatePetPage.enterPetname(driver);
		petName.equals(driver.switchTo().activeElement());
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		WebElement petStatus = CreatePetPage.enterPetStatus(driver);
		petStatus.equals(driver.switchTo().activeElement());
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		WebElement create = CreatePetPage.clickCreatePet(driver);
		create.equals(driver.switchTo().activeElement());
	}
	
	
	//Check Shift & Tab focus
	public void checkShiftTabFocus(WebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Robot r = new Robot();
		CreatePetPage.enterPetStatus(driver).click();
		r.keyPress(KeyEvent.VK_SHIFT);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_SHIFT);
		r.keyRelease(KeyEvent.VK_TAB);
		WebElement petName = CreatePetPage.enterPetname(driver);
		petName.equals(driver.switchTo().activeElement());
	}
	
	//View and validate list of Pets
	public void viewListOfPets(WebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		List<String> petList = new ArrayList<String>();
		List<String> statusList = new ArrayList<String>();
		
		petList.add("Cat1");
		petList.add("Dog2");
		petList.add("Rabbit3");
		
		statusList.add("Not-Available1");
		statusList.add("Available2");
		statusList.add("Available3");
		
		for (int i = 0; i < petList.size(); i++) {
			validateNewPetCreateButton(driver, petList.get(i), statusList.get(i));
			Thread.sleep(3000);
		}
	}
	
	
	//Edit Pet's Name and quit using the ESC key
	public void quitEditESCKey(WebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//tr[8]/td/span")).click();
	    driver.findElement(By.xpath("//input[@value='Cat1']")).clear();
	    driver.findElement(By.xpath("//input[@value='Cat1']")).sendKeys("Dock1");
	    
	    Robot r = new Robot();
	    r.keyPress(KeyEvent.VK_ESCAPE);
		r.keyRelease(KeyEvent.VK_ESCAPE);
		Assert.assertTrue(driver.getPageSource().contains("Cat1"));
		Thread.sleep(2000);
	}
	
	
	//Edit Pet's Status and quit using the Enter key
	public void quitEditEnterKey(WebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		WebElement element = driver.findElement(By.xpath("//tr[9]/td[2]/span"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[9]/td[2]/span")).click();
		Thread.sleep(1000);
	    driver.findElement(By.xpath("//input[@value='Available2']")).clear();
	    driver.findElement(By.xpath("//input[@value='Available2']")).sendKeys("Latest Arrival2");
	    Thread.sleep(2000);
	    Robot r = new Robot();
	    r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
	
	    Assert.assertTrue(driver.getPageSource().contains("Latest Arrival2"));
	    Thread.sleep(2000);
	}
	
	
	//Edit Pet's name and save by clicking out side the edit region
	public void quitEditClickBody(WebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		WebElement element = driver.findElement(By.xpath("//tr[10]/td/span"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
	    Thread.sleep(2000);

		driver.findElement(By.xpath("//tr[10]/td/span")).click();
		driver.findElement(By.xpath("//input[@value='Rabbit3']")).clear();
	    driver.findElement(By.xpath("//input[@value='Rabbit3']")).sendKeys("Goat3");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//tr[9]/td[2]/span")).click(); //save
		Thread.sleep(3000);
		
	    Assert.assertTrue(driver.getPageSource().contains("Goat3"));
		Thread.sleep(2000);
	}
	
	//Delete all Pets
	public void deleteAllPets(WebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		WebElement element = driver.findElement(CreatePetPage.firstDeleteButton());
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
	    Thread.sleep(2000);
		while (driver.findElements(CreatePetPage.firstDeleteButton()).size() != 0) {
			driver.findElement(CreatePetPage.firstDeleteButton()).click();
			Thread.sleep(2000);
		}
	}
	
	
	//Create Pets
	public void createPets(WebDriver driver, String petName, String petStatus) throws Exception {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		CreatePetPage.enterPetname(driver).clear();
		CreatePetPage.enterPetname(driver).sendKeys(petName);
		CreatePetPage.enterPetStatus(driver).clear();
		CreatePetPage.enterPetStatus(driver).sendKeys(petStatus);
		CreatePetPage.clickCreatePet(driver).click();
		Thread.sleep(1000);
	}
	
	
	//Create 100 Pets for rendering test
	public void create100Pets(WebDriver driver, String petName, String petStatus) throws Exception {
		deleteAllPets(driver);
		for (int i = 1; i <= 100; i++){
			createPets(driver, petName+i, petStatus+i);
		}
	}
	
	
	//Reload the created Pets and measure the rendering time.
	public void createHundredPets(WebDriver driver, String petName, String petStatus) throws Exception {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		create100Pets(driver, petName, petStatus);
		driver.close();
	}
	
	
	//Calculate rendering time
	public void timePageRendering(WebDriver driver) throws Exception {
		long start = System.currentTimeMillis(); 
		driver.get("https://qa-petstore.herokuapp.com/");
		long end = System.currentTimeMillis();
		long renderingTime = end - start;
		System.out.println("The rendering time is: " +renderingTime+ "ms");
	    Assert.assertTrue(renderingTime * 0.001 < 2);
	}
	
	
	
}

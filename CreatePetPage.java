package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreatePetPage {
	
	private static WebElement element = null;
	
	 
	//Pet name input text area element
	 public static WebElement enterPetname(WebDriver driver){
	 
	    element = driver.findElement(By.cssSelector("input.form-control.pet-name"));
	    
	    return element;
	 
	    }
	 
	 
	 //Pet status input text area element
	 public static WebElement enterPetStatus(WebDriver driver){
		 
		 element = driver.findElement(By.cssSelector("input.form-control.pet-status"));
		 
		 return element;
	 }
	 
	 
	 //Create button element
	 public static WebElement clickCreatePet(WebDriver driver){
		
		 element = driver.findElement(By.id("btn-create"));
		 
		 return element;
	 }

	 
	 //Date element
	 public static WebElement locateDate(WebDriver driver){
		 
		 element = driver.findElement(By.xpath("//div[1]/div/nav/div/span/div"));
		 
		 return element;
	 }
	 
	 
	 //Background colour element
	 public static WebElement getDateBackgroundColour(WebDriver driver){
		 
		 element = driver.findElement(By.xpath("//*[@class='assignment-masthead']"));
		 
		 return element;
	 }

	 
	 //First delete button element
	 public static By firstDeleteButton() {
		  
		 By element = By.cssSelector("button.btn.btn-danger.delete");
		 
		 return element;
	 }
}

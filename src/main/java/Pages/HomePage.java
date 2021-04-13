package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(css = "li._2TEfMi:nth-child(1)")
	public WebElement newCars;
	
	
	@FindBy(linkText = "Find New Cars")
	public WebElement findNewCars;
	
	
	public NewCarsPage findNewCar() {
		
		new Actions(driver).moveToElement(newCars).perform();
		findNewCars.click();
		return new NewCarsPage(driver);
		
		
	}
	


}

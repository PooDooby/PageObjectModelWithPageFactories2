package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class NewCarsPage extends BasePage {

	public NewCarsPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(linkText = "Maruti Suzuki")
	private WebElement maruti;

	@FindBy(linkText = "Hyundai")
	private WebElement hyundai;

	@FindBy(linkText = "Tata")
	private WebElement tata;

	@FindBy(linkText = "Kia")
	private WebElement kia;

	public void selectMarutiCar() {

		maruti.click();

	}

	public void selectHyundaiCar() {

		hyundai.click();
	}

	public void selectTataCar() {

		tata.click();
	}

	public void selectKiaCar() {

		kia.click();
	}

}

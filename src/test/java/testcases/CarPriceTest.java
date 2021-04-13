package testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import Pages.HomePage;
import base.BasePage;
import base.BaseTest;
import utilities.DUtility;

public class CarPriceTest extends BaseTest {
	
	@Test(dataProviderClass = DUtility.class, dataProvider = "podu")
	public void findCartest(String brandName,String browserName, String runmode) throws InterruptedException {
		
		if(runmode.equalsIgnoreCase("N")) {
			
			throw new SkipException("Skipping the TC as the RUNMODE is NO");
			
		}
		
		setUp(browserName);
		if(brandName.equalsIgnoreCase("maruti")) {
			
			HomePage hp = new HomePage(driver);
			hp.findNewCar().selectMarutiCar();;
			System.out.println(BasePage.car.getCarTitle());
			BasePage.car.getCarNameAndPrice();
			BasePage.car.updateCarPriceInFIle(brandName);
			
		}
		
		
		else if(brandName.equalsIgnoreCase("tata")){
			
			HomePage hp = new HomePage(driver);
			hp.findNewCar().selectTataCar();;
			System.out.println(BasePage.car.getCarTitle());
			BasePage.car.getCarNameAndPrice();
			BasePage.car.updateCarPriceInFIle(brandName);
			
		}
		
		else if(brandName.equalsIgnoreCase("hyundai")){
			
			HomePage hp = new HomePage(driver);
			hp.findNewCar().selectHyundaiCar();;
			System.out.println(BasePage.car.getCarTitle());
			BasePage.car.getCarNameAndPrice();
			BasePage.car.updateCarPriceInFIle(brandName);
			
		}
		
		else if(brandName.equalsIgnoreCase("kia")){
			
			HomePage hp = new HomePage(driver);
			hp.findNewCar().selectKiaCar();;
			System.out.println(BasePage.car.getCarTitle());
			BasePage.car.getCarNameAndPrice();
			BasePage.car.updateCarPriceInFIle(brandName);
			
		}
		
		
		Thread.sleep(2000);
	}

}



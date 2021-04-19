package base;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class CarBase {

	WebDriver driver;

	@FindBy(xpath = "//a[@class='o-dJmcbh o-eemiLE']") 

	public WebElement carTitle;

	public CarBase(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public String getCarTitle() {

		return carTitle.getText();

	}
	
	public ArrayList<String> list = new ArrayList<String>();

	@FindBy(xpath = "//h3[@class='o-jjpuv o-cVMLxW']")
	public List<WebElement> carNames;

	@FindBy(xpath = "//span[contains(text(),'Lakh')]")
	public List<WebElement> carPrices;

	public void getCarNameAndPrice() {

		for (int i = 0; i < carNames.size(); i++) {

			String details = "The Car Model is :-- " + carNames.get(i).getText() + "The Car Price is :-- "
					+ carPrices.get(i).getText();
			
			list.add(details);
			//System.out.println(details);
		}
		
		for (String li : list) {
			
			System.out.println(li);
		}

	}
	
	public void updateCarPriceInFIle(String fileName) {
		
		String info = fileName + " car brand name and price \n";
		
		File file  = new File(fileName);
		
		
		try {
			
			
			FileWriter fw = new FileWriter(file, true);
			
			fw.write(info);
			
			String lineSeparator  =  System.getProperty("line.separator");
			
			for(int j =0; j<list.size(); j++) {
				
				
				fw.write(list.get(j));
				fw.write(lineSeparator);
				
			}
			fw.flush();
			fw.close();
			FileUtils.copyFile(file, new File(".//carprice//" + fileName + ".txt" ));
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	

}

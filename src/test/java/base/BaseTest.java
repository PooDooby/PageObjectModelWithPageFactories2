package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DbManager;
import utilities.ExcelReader;

public class BaseTest {


	public WebDriver driver;
	public Logger log = Logger.getLogger(BaseTest.class.getName());
	public Properties Config = new Properties();
	public FileInputStream fis;
	public ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\testdata.xlsx");
	public WebDriverWait wwait;
	static WebElement dropdown;
	public String srcfileName;

	
	
	
	
	public void setUp(String browserName) {

		

			PropertyConfigurator.configure(".\\src\\test\\resources\\properties\\log4j.properties");

			// ----------------------------------------------------------------------------------------------------------------

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");

			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			try {
				Config.load(fis);
				log.info("Config {Properties are Loaded !!!!");
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		

		
		///***********************************************browser Details starts************************************************///
		/*
		 * if (System.getenv("browser") != null && !System.getenv("browser").isEmpty())
		 * { browser = System.getenv("browser"); } else { browser =
		 * Config.getProperty("browser"); } Config.setProperty("browser", browser);
		 */

		if (browserName.equals("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("Firefox launched !!!");

		} 
		else if (browserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();

			options.addArguments("--disable-web-security");
			options.addArguments("--no-proxy-server");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.default_content_setting_values.notifications", 2);

			options.setExperimentalOption("prefs", prefs);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.setExperimentalOption("useAutomationExtension", false);
			driver = new ChromeDriver(options);
			log.info("Chrome launched !!!");
		} else if (browserName.equals("edge")) {

			System.setProperty("webdriver.edge.driver", ".//src//test//resources//executables//msedgedriver.exe");
			driver = new EdgeDriver();
			log.info("edge launched !!!");
		}
		
		///**********************************************browser Details Ends*************************************************///
		
		

		
		
		///**********************************************Driver Initialization*************************************************///
		driver.manage().window().maximize();
		driver.get(Config.getProperty("testsiteurl"));
		log.info("Navigateed to :" + Config.getProperty("testsiteurl"));
		DbManager.setMysqlDbConnection();

		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);

		wwait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));

}
	
	// -----------------------------------------------SCREENSHOT UTIL STARTS----------------------------------------------------------------------//
		public void captureScreenshot() {
			

			Date d = new Date();

			srcfileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\target\\reports\\" + srcfileName));
				FileUtils.copyFile(screenshot,
						new File(System.getProperty("user.dir") + "\\test-output\\html\\" + srcfileName));

			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}

		public void captureElementScreenshot(WebElement element) {

			Date d = new Date();
			String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

			File screenshot = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\screenshot\\" + fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		// -----------------------------------------------SCREENSHOT UTIL END----------------------------------------------------------------------//

	@AfterMethod
	public void tearDown() {

		driver.quit();
		log.debug("Test completed !!!!");
	}

}

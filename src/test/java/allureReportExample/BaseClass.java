package allureReportExample;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	//initiate the driver and then extend this class 
	public WebDriver driver;
	
	//we are using ThreadLocal (WebDriver)instance
	//it basically create no. of threads in local
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	
	@SuppressWarnings("deprecation")
	public WebDriver intialize_driver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//the same driver will be shared across multiple files
		tdriver.set(driver);
		return getDriver();
	}
	//we are using this method because we want to share the driver 
	//between Test as well as Listener file
	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}

}

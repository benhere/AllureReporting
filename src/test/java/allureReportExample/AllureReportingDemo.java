package allureReportExample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

//associate Allure Listener class to this AllureReportingDemo class
@Listeners({AllureListenerDemo.class})

@SuppressWarnings("unused")
public class AllureReportingDemo extends BaseClass 
{
	
    public WebDriver wd;
	
	@BeforeClass
	public void setUp()
	{
//		WebDriverManager.chromedriver().setup();
//		wd = new ChromeDriver();
//		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		BaseClass bs = new BaseClass();
		wd = bs.intialize_driver();
		wd.get("https://demo.nopcommerce.com/");
	}
	
	@Test(priority=1, description = "verify logo presence on Home Page")
	@Description("Verify logo presence on Home Page")
	@Epic("EP001")
	@Feature("Feature1: Logo")
	@Story("Story: Logo Presence")
	@Step("Verify Logo Presence")
	@Severity(SeverityLevel.MINOR)
	public void check_logoPresence()
	{
		boolean logoImg_status = wd.findElement(By.xpath("//body/div[6]/div[1]/div[2]/div[1]/a[1]/img[1]")).isDisplayed();
	    Assert.assertEquals(logoImg_status, true);
	}
	
	@Test(priority=2)
	@Description("Verify login functionality")
	@Epic("EP002")
	@Feature("Feature2: login")
	@Story("Story: Login check")
	@Step("Verify Login Process")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest()
	{
		wd.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
		wd.findElement(By.id("Email")).sendKeys("benjamin.oldfriend@gmail.com");
		wd.findElement(By.id("Password")).sendKeys("Benjamin@123");
	
		//Click on Login button
		wd.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
	
		//verify the result
		Assert.assertEquals(wd.getTitle(), "nopCommerce demo store");
	}
	
	@Test(priority=3)
	@Description("Verify registration functionality")
	@Epic("EP003")
	@Feature("Feature2: register")
	@Story("Story: Registration check")
	@Step("Verify Register Process")
	@Severity(SeverityLevel.NORMAL)
	public void registrationTest()
	{
		//skip this test
		throw new SkipException("Skipping this Test");
	}
	
	@AfterClass
	public void tearDown()
	{
		wd.quit();
	}

}

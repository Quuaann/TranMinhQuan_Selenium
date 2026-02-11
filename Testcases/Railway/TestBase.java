package Railway;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Constant.Constant;

public class TestBase {
	HomePage homePage = new HomePage();
	
	@Parameters({"browser", "url"})
	@BeforeSuite
    public void beforeSuite(@Optional("chrome") String browser, @Optional("http://railway.somee.com") String url) {
        System.out.println("=== TEST SUITE STARTED ===");
        System.out.println("URL: " + url);
    }
	
	@Parameters({"browser", "url"})
	@BeforeMethod
    public void beforeMethod(@Optional("chrome") String browser, @Optional("http://railway.somee.com") String url) {
		
		switch (browser.toLowerCase()) {
        case "chrome":
        	Constant.WEBDRIVER = new ChromeDriver();
            System.out.println("ChromeDriver initialized");
            break;
            
        case "firefox":
        	Constant.WEBDRIVER = new FirefoxDriver();
            System.out.println("FirefoxDriver initialized");
            break;
            
        default:
            System.out.println("Browser " + browser + " is not valid. Using Chrome as default.");
            Constant.WEBDRIVER = new ChromeDriver();
		}
		
        Constant.WEBDRIVER.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition\n");
        
        //Constant.WEBDRIVER.quit();
    }
}

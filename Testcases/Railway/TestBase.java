package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
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
    public void beforeSuite(@Optional("chrome") String browser, 
                           @Optional("http://railway.somee.com") String url) {
        System.out.println("=== TEST SUITE STARTED ===");
        System.out.println("Browser: " + browser);
        System.out.println("URL: " + url);
        //System.out.println("Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
	
	@BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
        
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        
        Constant.WEBDRIVER.quit();
    }
}

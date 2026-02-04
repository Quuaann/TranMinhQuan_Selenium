package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

import Constant.Constant;

public class LoginTest {

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

    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username and password");
        
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;

        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }
    
    // TC2: User cannot login with blank "Password" textbox
    @Test
    public void TC02_LoginWithBlankPassword() {
        System.out.println("TC02 - User cannot login with blank Password");      

        // 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();
        
        // 2. Click on "Login" tab
        LoginPage loginPage = homePage.gotoLoginPage();

        // 3. User enters valid username but leaves password blank
        // 4. Click on "Login" button (đã được thực hiện trong login method)
        loginPage.login(Constant.USERNAME, "");
        
        // Verify error message appears
        String actualMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
    }
    
    // TC3: User cannot log into Railway with invalid password
    @Test
    public void TC03_LoginWithInvalidPassword() {
        System.out.println("TC03 - User cannot log into Railway with invalid password");

        // 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();
        
        // 2. Click on "Login" tab
        LoginPage loginPage = homePage.gotoLoginPage();

        // 3. Enter valid Email and invalid Password
        loginPage.login(Constant.USERNAME, Constant.WRONG_PASSWORD);

        // Verify error message is displayed
        String actualMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
    }
    
    // TC04: System shows message when user enters wrong password many times
    @Test
    public void TC04_LoginWithMultipleWrongPasswordAttempts() {
        System.out.println("TC04 - System shows message when user enters wrong password many times");
        SoftAssert softAssert = new SoftAssert();

        // 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();
        
        // 2. Click on "Login" tab
        LoginPage loginPage = homePage.gotoLoginPage();
        
        // 5th attempt - should show different message
        loginPage.login(Constant.USERNAME, Constant.WRONG_PASSWORD);
        
        // Verify second error message
        String actualFirstMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedFirstMsg = "Invalid username or password. Please try again";
        
        softAssert.assertEquals(actualFirstMsg,expectedFirstMsg,
            "Error message is not displayed correctly");

        // 3. Enter valid username but wrong password 4 times
        for (int i = 0; i < 4; i++) {
            loginPage.login(Constant.USERNAME, Constant.WRONG_PASSWORD);
        }

        // Verify first error message
        String actualSecondMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedSecondMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        
        softAssert.assertEquals(actualSecondMsg, expectedSecondMsg,
            "Attempt limit warning message is not displayed");
        
        softAssert.assertAll();
    }

    // TC05: User can't login with an account hasn't been activated
    @Test
    public void TC05_LoginWithNotActivatedAccount() {
        System.out.println("TC05 - User can't login with an account hasn't been activated");
        
        // 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();
        
        // 2. Click on "Login" tab       
        LoginPage loginPage = homePage.gotoLoginPage();

        // 3. Enter username and password of not-activated account
        // 4. Click on "Login" button
        loginPage.login(Constant.NOT_ACTIVATED_USERNAME, Constant.NOT_ACTIVATED_PASSWORD);

        // Verify error message (cần xác định message chính xác)
        String actualMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedMsg = "Invalid username or password. Please try again";
        
        Assert.assertTrue(actualMsg.contains(expectedMsg), 
            "Error message for not-activated account is not displayed correctly");
    }
    
    // TC06: User is redirected to Home after logout
    @Test
    public void TC06_RedirectToHomeAfterLogout() {
        System.out.println("TC06 - User is redirected to Home after logout");
        
        // 1. Navigate to QA Railway Website    
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        
        // 2. Login with valid Email and Password
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        
        // 3. Click on "FAQ" tab (optional step - có thể bỏ qua nếu không ảnh hưởng)
        FAQPage faqPage = loginPage.gotoFAQPage();
        
        // 4. Click on "Log out" tab
        faqPage.logoutPage();
        
        // Verify user is redirected to Home page
        String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
        String expectedHomeUrl = Constant.RAILWAY_URL;
        
        Assert.assertEquals(currentUrl, expectedHomeUrl, 
            "User is not redirected to Home page after logout");   
    }
}
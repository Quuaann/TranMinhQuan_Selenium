package Railway;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

import Constant.Constant;

public class CreatAccountTest {
	private static final String REGISTER_URL = "http://saferailway.somee.com/Account/Register.cshtml";
	
	
	@BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        
        //Constant.WEBDRIVER.quit();
    }
    
    // TC07: User can't create account with existing email
    @Test
    public void TC07_CannotCreateAccountWithExistingEmail() {
        System.out.println("TC07 - User can't create account with existing email");
                
        // 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();
        
        // 2. Click on "Register" tab       
        RegisterPage registerPage = homePage.gotoRegisterPage();
        
        // 3. Enter information of the existing account
        // 4. Click on "Register" button
        registerPage.enterRegistrationInfo(
            Constant.USERNAME,
            Constant.PASSWORD,
            Constant.PASSWORD,
            Constant.PID
        ); 
        
        // Verify error message
        String actualMsg = registerPage.getLblRegisterErrorMsg().getText();
        String expectedMsg = "This email address is already in use.";
        
        Assert.assertTrue(actualMsg.contains(expectedMsg), 
            "Error message for existing email is not displayed.");
        
        System.out.println("TC07 PASSED: Cannot register with existing email");
    }

    // TC08C: Register with invalid PID length
    @Test
    public void TC08C_RegisterWithInvalidPIDLength() {
        System.out.println("TC08C - Register with invalid PID length");
        SoftAssert softAssert = new SoftAssert();
        
        HomePage homePage = new HomePage();
        homePage.open();
        RegisterPage registerPage = homePage.gotoRegisterPage();
        
        // Enter valid data but PID quá ngắn
        registerPage.enterRegistrationInfo(
            Constant.NEWUSER,
            "",
            "",
            "" 
        );
                
        // Verify PID error message
        String actualGeneralMsg = registerPage.getLblRegisterErrorMsg().getText();
        String expectedGeneralMsg = "There're errors in the form. Please correct the errors and try again.";
        
        softAssert.assertEquals(actualGeneralMsg, expectedGeneralMsg, 
            "General error message không đúng");
        
     // Verify PID error message
        String actualPwdMsg = registerPage.getLblPasswordErrorMsg().getText();
        String expectedPwdMsg = "Invalid password length.";
        
        softAssert.assertEquals(actualPwdMsg, expectedPwdMsg,
            "Password error message không đúng");
        
     // Verify PID error message
        String actualPidMsg = registerPage.getLblPidErrorMsg().getText();
        String expectedPidMsg = "Invalid ID length.";
        
        softAssert.assertEquals(actualPidMsg, expectedPidMsg,
            "PID error message không đúng");
        
        softAssert.assertAll();
        
        System.out.println("TC08C PASSED");
    }
    
    // TC09: User create and activate account
    @Test
    public void TC09_CreateAndActivateAccount() {
        System.out.println("TC09 - User create and activate account");
        SoftAssert softAssert = new SoftAssert();
        
        // 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();
        
        // 2. Click on "Create an account" 
        RegisterPage registerPage = homePage.clickRegisterLink();
        
        // Verify user is redirected to Register page
        String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
        String expectedRegisterUrl = REGISTER_URL;
        
        softAssert.assertEquals(currentUrl, expectedRegisterUrl, 
            "User is not redirected to Register page");
        
        // 3. Enter valid information into all fields 
        // 4. Click on "Register" button
        registerPage.enterRegistrationInfo(
                Constant.NEWUSER_MAIL,
                Constant.PASSWORD,
                Constant.PASSWORD,
                Constant.PID 
            );
                
        // Verify: "Thank you for registering your account" message
        String successMessage = registerPage.getLblRegisterSuccessfulMsg().getText();
        String expectedMessage = "Thank you for registering your account";
        
        softAssert.assertEquals(successMessage.contains(expectedMessage),
            "Registration success message not displayed.");
        
        MailFake mailFake = new MailFake();
        mailFake.openMailFake();
        
        mailFake.activeNewMail(Constant.NEWUSER);
        
        // Verify: "Thank you for registering your account" message
        String successActiveMessage = registerPage.getLblActiveSuccessfulMsg().getText();
        String expectedActiveMessage = "Registration Confirmed! You can now log in to the site";
        
        softAssert.assertEquals(successActiveMessage, expectedActiveMessage, 
                "Active email success to Register page");
    }
}

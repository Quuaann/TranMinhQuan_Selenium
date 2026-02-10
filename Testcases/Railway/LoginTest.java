package Railway;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

import Constant.Constant;
import DataObject.Account;

public class LoginTest extends TestBase {

	@Test
	public void TC01_LoginWithValidUsernameAndPassword() {
		System.out.println("TC01 - User can log into Railway with valid username and password");
		final Account validAccount = new Account(Constant.USERNAME, Constant.PASSWORD, Constant.PID);
	    final String expectedMsg = "Welcome " + validAccount.getEmail();

	    System.out.println("1. Navigate to QA Railway Website");
	    homePage.open();

	    System.out.println("2. Click on 'Login' tab");
	    LoginPage loginPage = homePage.gotoLoginPage();

	    System.out.println("3. Enter valid username and password");
	    homePage = loginPage.login(validAccount);

	    System.out.println("4. Verify user is logged in");
	    String actualMsg = homePage.getWelcomeMessage();
	    
	    Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
	    System.out.println("TC01 PASSED");
	}

	@Test
	public void TC02_LoginWithBlankPassword() {
		System.out.println("TC02 - User cannot login with blank Password");
		final Account blankPasswordAccount = new Account(Constant.USERNAME, "", Constant.PID);
	    final String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

	    System.out.println("1. Navigate to QA Railway Website");
	    homePage.open();
	    
	    System.out.println("2. Click on 'Login' tab");
	    LoginPage loginPage = homePage.gotoLoginPage();

	    System.out.println("3. User enters valid username but leaves password blank");
	    System.out.println("4. Click on 'Login' button");
	    loginPage = loginPage.login(blankPasswordAccount);
	    
	    System.out.println("Verify error message appears");
	    String actualMsg = loginPage.getLblLoginErrorMsg().getText();

	    Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
	    System.out.println("TC02 PASSED");
	}

	@Test
	public void TC03_LoginWithInvalidPassword() {
	    System.out.println("TC03 - User cannot log into Railway with invalid password");
		final Account invalidPasswordAccount = new Account(Constant.USERNAME, Constant.WRONG_PASSWORD, Constant.PID);
	    final String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

	    System.out.println("1. Navigate to QA Railway Website");
	    homePage.open();
	    
	    System.out.println("2. Click on 'Login' tab");
	    LoginPage loginPage = homePage.gotoLoginPage();

	    System.out.println("3. Enter valid Email and invalid Password");
	    loginPage = loginPage.login(invalidPasswordAccount);

	    System.out.println("Verify error message is displayed");
	    String actualMsg = loginPage.getLblLoginErrorMsg().getText();

	    Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
	    System.out.println("TC03 PASSED");
	}

	@Test
	public void TC04_LoginWithMultipleWrongPasswordAttempts() {
	    System.out.println("TC04 - System shows message when user enters wrong password many times");
		final Account invalidPasswordAccount = new Account(Constant.USERNAME, Constant.WRONG_PASSWORD, Constant.PID);
	    final String expectedFirstMsg = "Invalid username or password. Please try again";
	    final String expectedSecondMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

	    SoftAssert softAssert = new SoftAssert();

	    System.out.println("1. Navigate to QA Railway Website");
	    homePage.open();
	    
	    System.out.println("2. Click on 'Login' tab");
	    LoginPage loginPage = homePage.gotoLoginPage();
	    
	    System.out.println("3. Enter valid information into 'Username'textbox except 'Password' textbox.");
	    System.out.println("4. Click on 'Login' button");
	    loginPage = loginPage.login(invalidPasswordAccount);
	    
	    System.out.println("Verify second error message");
	    String actualFirstMsg = loginPage.getLblLoginErrorMsg().getText();
	    
	    softAssert.assertEquals(actualFirstMsg, expectedFirstMsg,
	        "Error message is not displayed correctly");

	    System.out.println("5. Repeat step 3 and 4 three more times.");
	    for (int i = 0; i < 4; i++) {
	        System.out.println("   Attempt " + (i + 2) + " with invalid password");
	        loginPage.login(invalidPasswordAccount);
	    }

	    System.out.println("Verify error message appears");
	    String actualSecondMsg = loginPage.getLblLoginErrorMsg().getText();
	    
	    softAssert.assertEquals(actualSecondMsg, expectedSecondMsg,
	        "Attempt limit warning message is not displayed");
	    
	    softAssert.assertAll();
	    System.out.println("TC04 PASSED");
	}

	@Test
	public void TC05_LoginWithNotActivatedAccount() {
	    System.out.println("TC05 - User can't login with an account hasn't been activated");
		final Account notActivatedAccount = new Account(Constant.NOT_ACTIVATED_USERNAME, Constant.NOT_ACTIVATED_PASSWORD, Constant.PID);
		final String expectedMsg = "Invalid username or password. Please try again";
	    
	    System.out.println("1. Navigate to QA Railway Website");
	    homePage.open();
	    
	    System.out.println("2. Click on 'Login' tab");       
	    LoginPage loginPage = homePage.gotoLoginPage();

	    System.out.println("3. Enter username and password of not-activated account");
	    System.out.println("4. Click on 'Login' button");
	    loginPage = loginPage.login(notActivatedAccount);

	    System.out.println("Verify error message appears");
	    String actualMsg = loginPage.getLblLoginErrorMsg().getText();
	    
	    Assert.assertTrue(actualMsg.contains(expectedMsg), 
	        "Error message for not-activated account is not displayed correctly");
	    System.out.println("TC05 PASSED");
	}

	@Test
	public void TC06_RedirectToHomeAfterLogout() {
	    System.out.println("TC06 - User is redirected to Home after logout");
		final Account validAccount = new Account(Constant.USERNAME, Constant.PASSWORD, Constant.PID);
	    
	    System.out.println("1. Navigate to QA Railway Website");    
	    homePage.open();
	    LoginPage loginPage = homePage.gotoLoginPage();
	    
	    System.out.println("2. Login with valid Email and Password");
	    homePage = loginPage.login(validAccount);
	    
	    System.out.println("3. Click on 'FAQ' tab");
	    FAQPage faqPage = homePage.gotoFAQPage();
	    
	    System.out.println("4. Click on 'Log out' tab");
	    faqPage.logoutPage();
	    
	    System.out.println("Verify user is redirected to Home page");
	    boolean isHomePageDisplayed = homePage.getSelectedHome().isDisplayed();

	    Assert.assertTrue(isHomePageDisplayed, 
	        "Home page is not displayed.");
	    System.out.println("TC06 PASSED");
	}
}
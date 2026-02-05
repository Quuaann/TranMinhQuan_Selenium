package Railway;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

import Constant.Constant;
import DataObject.Account;

public class CreatAccountTest extends TestBase {
    
    // Tạo các account objects
    private Account existingAccount = new Account(Constant.USERNAME, Constant.PASSWORD, Constant.PID);
    private Account newAccount = new Account(Constant.NEWUSER_MAIL, Constant.PASSWORD, Constant.PID);
    private Account invalidAccount = new Account(Constant.NEWUSER_MAIL, "", ""); // Tất cả fields blank

    // TC07: User can't create account with existing email
    @Test
    public void TC07_CannotCreateAccountWithExistingEmail() {
        System.out.println("TC07 - User can't create account with existing email");
        String expectedMsg = "This email address is already in use.";
        
        // 1. Navigate to QA Railway Website
        System.out.println("1. Navigate to QA Railway Website");
        homePage.open();
        
        // 2. Click on "Register" tab       
        System.out.println("2. Click on 'Register' tab");
        RegisterPage registerPage = homePage.gotoRegisterPage();
        
        // 3. Enter information of the existing account
        System.out.println("3. Enter information of the existing account");
        System.out.println("   Email: " + existingAccount.getEmail());
        System.out.println("   Password: " + existingAccount.getPassword());
        System.out.println("   PID: " + existingAccount.getPid());
        
        // 4. Click on "Register" button
        System.out.println("4. Click on 'Register' button");
        registerPage.enterRegistrationInfo(
            existingAccount.getEmail(),
            existingAccount.getPassword(),
            existingAccount.getPassword(), // confirm password
            existingAccount.getPid()
        ); 
        
        // Verify error message
        System.out.println("Verify error message");
        String actualMsg = registerPage.getLblRegisterErrorMsg().getText();
        
        Assert.assertTrue(actualMsg.contains(expectedMsg), 
            "Error message for existing email is not displayed.");
        
        System.out.println("✅ TC07 PASSED: Cannot register with existing email");
    }

    // TC08: Register with invalid PID length
    @Test
    public void TC08_RegisterWithEmptyPasswordAndPID() {
        System.out.println("TC08C - User can't create account while password and PID fields are empty");
        String expectedGeneralMsg = "There're errors in the form. Please correct the errors and try again.";
        String expectedPwdMsg = "Invalid password length";
        String expectedPidMsg = "Invalid ID length";

        SoftAssert softAssert = new SoftAssert();
        
        // 1. Navigate to QA Railway Website
        System.out.println("1. Navigate to QA Railway Website");
        homePage.open();
        
        // 2. Click on 'Register' tab
        System.out.println("2. Click on 'Register' tab");
        RegisterPage registerPage = homePage.gotoRegisterPage();
        
        // 3. Enter valid email address and leave other fields empty
        System.out.println("3. Enter valid email address and leave other fields empty");
        System.out.println("   Email: " + invalidAccount.getEmail());
        System.out.println("   Password: " + invalidAccount.getPassword());
        System.out.println("   PID: " + invalidAccount.getPid());
        
        registerPage.enterRegistrationInfo(
        		invalidAccount.getEmail(),
        		invalidAccount.getPassword(),
        		invalidAccount.getPassword(), 
        		invalidAccount.getPid()
        );
                
        // Verify general error message
        System.out.println("Verify general error message");
        String actualGeneralMsg = registerPage.getLblRegisterErrorMsg().getText();
        
        softAssert.assertEquals(actualGeneralMsg, expectedGeneralMsg, 
            "General error message không đúng");
        
        // Verify password error message
        System.out.println("Verify password error message");
        String actualPwdMsg = registerPage.getLblPasswordErrorMsg().getText();
        
        softAssert.assertEquals(actualPwdMsg, expectedPwdMsg,
            "Password error message không đúng");
        
        // Verify PID error message
        System.out.println("Verify PID error message");
        String actualPidMsg = registerPage.getLblPidErrorMsg().getText();
        
        softAssert.assertEquals(actualPidMsg, expectedPidMsg,
            "PID error message không đúng");
        
        softAssert.assertAll();
        
        System.out.println("✅ TC08 PASSED");
    }
    
    // TC09: User create and activate account
    @Test
    public void TC09_CreateAndActivateAccount() {
        System.out.println("TC09 - User create and activate account");
        SoftAssert softAssert = new SoftAssert();
        
        // 1. Navigate to QA Railway Website
        System.out.println("1. Navigate to QA Railway Website");
        homePage.open();
        
        // Verify Home page is shown with guide containing href "create an account" to "Register" page
        System.out.println("Verify Home page is shown with guide containing href 'create an account' to 'Register' page");
        boolean isRegisterLinkDisplayed = homePage.getLinkRegister().isDisplayed();

	    Assert.assertTrue(isRegisterLinkDisplayed, 
            "User is not redirected to Register page");
	    
        // 2. Click on "Create an account" 
        System.out.println("2. Click on 'Create an account'");
        RegisterPage registerPage = homePage.clickRegisterLink();
        
        // Verify user is redirected to Register page
        System.out.println("Verify user is redirected to Register page");
        boolean isHomePageDisplayed = registerPage.getSelectedRegister().isDisplayed();

	    Assert.assertTrue(isHomePageDisplayed, 
            "User is not redirected to Register page");
        
        // 3. Enter valid information into all fields 
        System.out.println("3. Enter valid information into all fields");
        System.out.println("   Email: " + newAccount.getEmail());
        System.out.println("   Password: " + newAccount.getPassword());
        System.out.println("   Confirm Password: " + newAccount.getPassword());
        System.out.println("   PID: " + newAccount.getPid());
        
        // 4. Click on "Register" button
        System.out.println("4. Click on 'Register' button");
        registerPage.enterRegistrationInfo(
            newAccount.getEmail(),
            newAccount.getPassword(),
            newAccount.getPassword(), // confirm password
            newAccount.getPid()
        );
                
        // Verify: "Thank you for registering your account" message
        System.out.println("Verify registration success message");
        String successMessage = registerPage.getLblRegisterSuccessfulMsg().getText();
        String expectedMessage = "Thank you for registering your account";
        
        softAssert.assertTrue(successMessage.contains(expectedMessage),
            "Registration success message not displayed.");
        
        // 5. Get email information (webmail address, mailbox and password) and navigate to that webmail 
        // 6. Login to the mailbox
        System.out.println("5. Open email service for activation");
        MailFake mailFake = new MailFake();
        mailFake.openMailFake();
        
        // 7. Open email with subject containing "Please confirm your account"  and the email of the new account at step 3
        // 8. Click on the activate link
        System.out.println("6. Activate new email");
        mailFake.activeNewMail(newAccount.getEmail());
        
        // Verify: "Registration Confirmed!" message
        System.out.println("Verify activation success message");
        String successActiveMessage = registerPage.getLblActiveSuccessfulMsg().getText();
        String expectedActiveMessage = "Registration Confirmed! You can now log in to the site.";
        
        softAssert.assertEquals(successActiveMessage, expectedActiveMessage, 
                "Active email success to Register page");
        
        softAssert.assertAll();
        System.out.println("✅ TC09 PASSED");
    }
}

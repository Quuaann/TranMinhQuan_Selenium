package Railway;

import org.testng.annotations.Test;
import org.testng.Assert;

import Constant.Constant;
import Constant.TabMenu;
import DataObject.Account;
import Guerrillamail.MailFake;

public class RegisterTest extends TestBase {

    @Test
    public void TC07_CannotCreateAccountWithExistingEmail() {
        System.out.println("TC07 - User can't create account with existing email");
        final Account newAccount = new Account(Constant.getNewUserMail(), Constant.PASSWORD, Constant.PID);
        final String expectedMsg = "This email address is already in use.";
        
        System.out.println("PRE-CONDITION: Create and active new account");
        System.out.println("1. Navigate to QA Railway Website");
        homePage.open();
        CreateActiveAccountFlow flow = new CreateActiveAccountFlow();
        flow.createActiveAccount(newAccount);
        
        System.out.println("2. Click on 'Register' tab");
        RegisterPage registerPage = homePage.gotoPage(TabMenu.REGISTER,RegisterPage.class);
        
        System.out.println("3. Enter information of the existing account");
        System.out.println("4. Click on 'Register' button");
        registerPage.enterRegistrationInfo(newAccount);
        
        System.out.println("Verify error message");
        String actualMsg = registerPage.getLblRegisterErrorMsg().getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Error message for existing email is not displayed.");
    }

    @Test
    public void TC08_RegisterWithEmptyPasswordAndPID() {
        System.out.println("TC08 - User can't create account while password and PID fields are empty");
    	final Account invalidAccount = new Account(Constant.getNewUserMail(), "", "");
    	final String expectedGeneralMsg = "There're errors in the form. Please correct the errors and try again.";
    	final String expectedPwdMsg = "Invalid password length";
    	final String expectedPidMsg = "Invalid ID length";
        
        System.out.println("1. Navigate to QA Railway Website");
        homePage.open();
        
        System.out.println("2. Click on 'Register' tab");
        RegisterPage registerPage = homePage.gotoPage(TabMenu.REGISTER,RegisterPage.class);
        
        System.out.println("3. Enter valid email address and leave other fields empty");
        registerPage.enterRegistrationInfo(invalidAccount);
                
        System.out.println("Verify general error message");
        String actualGeneralMsg = registerPage.getLblRegisterErrorMsg().getText();
        Assert.assertEquals(actualGeneralMsg, expectedGeneralMsg, "General error message is not correct");
        
        System.out.println("Verify password error message");
        String actualPwdMsg = registerPage.getLblPasswordErrorMsg().getText();       
        Assert.assertEquals(actualPwdMsg, expectedPwdMsg, "Password error message is not correct");
        
        System.out.println("Verify PID error message");
        String actualPidMsg = registerPage.getLblPidErrorMsg().getText();
        Assert.assertEquals(actualPidMsg, expectedPidMsg,"PID error message is not correct");
    }
    
    @Test
    public void TC09_CreateAndActivateAccount() {
    	System.out.println("TC09 - User create and activate account");
    	final Account newAccount = new Account(Constant.getNewUserMail(), Constant.PASSWORD, Constant.PID);
    	final String expectedMessage = "Thank you for registering your account";
    	final String expectedActiveMessage = "Registration Confirmed! You can now log in to the site.";
        
        System.out.println("1. Navigate to QA Railway Website");
        homePage.open();
        
        System.out.println("Verify Home page is shown with guide containing href 'create an account' to 'Register' page");
        boolean isRegisterLinkDisplayed = homePage.getLinkRegister().isDisplayed();
        Assert.assertTrue(isRegisterLinkDisplayed, "User is not redirected to Register page");
	    
        System.out.println("2. Click on 'Create an account'");
        RegisterPage registerPage = homePage.clickRegisterLink();
        
        System.out.println("Verify user is redirected to Register page");
        boolean isHomePageDisplayed = registerPage.getSelectedRegister().isDisplayed();
	    Assert.assertTrue(isHomePageDisplayed, "User is not redirected to Register page");
        
        System.out.println("3. Enter valid information into all fields");
        System.out.println("4. Click on 'Register' button");
        registerPage.enterRegistrationInfo(newAccount);
                
        System.out.println("Verify registration success message");
        String successMessage = registerPage.getLblRegisterSuccessfulMsg().getText();
        Assert.assertTrue(successMessage.contains(expectedMessage),"Registration success message not displayed.");
        
        System.out.println("5. Open email service for activation");
        MailFake mailFake = new MailFake();
        mailFake.openMailFake();
        
        System.out.println("6. Activate new email");
        System.out.println("7. Open email with subject containing 'Please confirm your account' and the email of the new account at step 3");
        System.out.println("8. Click on the activate link");
        mailFake.activeNewMail(newAccount.getEmail());
        
        System.out.println("Verify user is redirect to Railways page and message 'Registration Confirmed! You can now log in to the site' is shown");
        String successActiveMessage = registerPage.getLblActiveSuccessfulMsg().getText();
        Assert.assertEquals(successActiveMessage, expectedActiveMessage, "Active email success to Register page");
    }
}

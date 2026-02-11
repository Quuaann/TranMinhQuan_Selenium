package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import Constant.TabMenu;
import DataObject.Account;
import Guerrillamail.MailFake;

public class ResetPasswordTest extends TestBase {
    
	@Test
	public void TC10_ResetPasswordShowsErrorIfTheSamePassword() {
	    System.out.println("TC10 - Reset password shows error if user resets password with new password same as old password");
	    final Account newAccount = new Account(Constant.getNewUserMail(), Constant.PASSWORD, Constant.PID);
	    final String expectedErrorMsg = "The new password cannot be the same as the current password";
	    
	    System.out.println("PRE-CONDITION: Create and active new account");
		homePage.createActiveAccount(homePage, newAccount);
	    
	    System.out.println("1. Navigate to QA Railway Login page");
	    LoginPage loginPage = homePage.gotoPage(TabMenu.LOGIN,LoginPage.class);
	    
	    System.out.println("2. Click on 'Forgot Password page' link");
	    ResetPasswordPage forgotPasswordPage = loginPage.gotoForgotPasswordPage();
	    
	    System.out.println("3. Enter the email address of the activated account");
	    System.out.println("4. Click on 'Send Instructions' button");
	    forgotPasswordPage.sendInstructions(newAccount.getEmail());
	    
	    System.out.println("5. Login to the mailbox");
	    MailFake mailFake = new MailFake();
        mailFake.openMailFake();
	    
	    System.out.println("6. Open email with subject containing 'Please reset your password'");
	    System.out.println("7. Click on reset link");
	    String token = mailFake.forgotMail(newAccount.getEmail());
	    
	    System.out.println("Verify user is redirected to Railways page and Form 'Password Change Form' is shown with the reset password token");
	    Assert.assertTrue(loginPage.isShowTrueToken(token),
	        "User was redirected away from reset password page or Form 'Password Change Form' is not shown with the reset password token");

	    System.out.println("8. Input same password into 'new password' and 'confirm password'");
	    System.out.println("9. Click Reset Password");
	    loginPage.resetPassword(Constant.PASSWORD, Constant.PASSWORD);
	    	      
	    System.out.println("Verify that message 'The new password cannot be the same with the current password' is shown");
	    String actualErrorMsg = loginPage.getLblResetSucces().getText();
	    Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
	}
	
	@Test
    public void TC11_ResetPasswordShowsErrorIfDifferentConfirmPassword() {
        System.out.println("TC11 - Reset password shows error if the new password and confirm password are different");
        final Account newAccount = new Account(Constant.getNewUserMail(), Constant.PASSWORD, Constant.PID);
        final String newPassword = "123456789";
        final String confirmPassword = "987654321";
        final String expectedErrorMsg = "Could not reset password. Please correct the errors and try again.";
        final String expectedConfirmErrorMsg = "The password confirmation did not match the new password.";
        
        System.out.println("PRE-CONDITION: Create and activate new account");
		homePage.createActiveAccount(homePage, newAccount);
        
        System.out.println("1. Navigate to QA Railway Login page");
        LoginPage loginPage = homePage.gotoPage(TabMenu.LOGIN,LoginPage.class);
        
        System.out.println("2. Click on 'Forgot Password page' link");
        ResetPasswordPage forgotPasswordPage = loginPage.gotoForgotPasswordPage();
        
        System.out.println("3. Enter the email address of the activated account");        
        System.out.println("4. Click on 'Send Instructions' button");
        forgotPasswordPage.sendInstructions(newAccount.getEmail());
        
        System.out.println("5. Login to the mailbox");
        MailFake mailFake = new MailFake();
        mailFake.openMailFake();
        
        System.out.println("6. Open email with subject containing 'Please reset your password'");
        System.out.println("7. Click on reset link");
        String token = mailFake.forgotMail(newAccount.getEmail());
        
        System.out.println("Verify user is redirected to Railways page and Form 'Password Change Form' is shown with the reset password token");
        Assert.assertTrue(loginPage.isShowTrueToken(token),
    	    "User was redirected away from reset password page or Form 'Password Change Form' is not shown with the reset password token");
        
        System.out.println("8. Input different passwords:");
        System.out.println("9. Click Reset Password");
        loginPage.resetPassword(newPassword, confirmPassword);
	         
        System.out.println("Verify Error message 'Could not reset password. Please correct the errors and try again.' displays above the form.");     
        String actualErrorMsg = loginPage.getLblResetError().getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg,
            "Error message 'Could not reset password. Please correct the errors and try again.' is not displayed as expected");
        
        System.out.println("Verify Error message 'The password confirmation did not match the new password.' displays next to the confirm password field.");
        String actualErrorConfirmMsg = loginPage.getLblConfirmPasswordErrorMsg().getText();
        Assert.assertEquals(actualErrorConfirmMsg, expectedConfirmErrorMsg,
            "Error message 'The password confirmation did not match the new password.' is not displayed as expected");
    }
}

package Railway;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Constant.Constant;
import DataObject.Account;
import Guerrillamail.MailFake;

public class ForgotPasswordTest extends TestBase {
    
	@Test
	public void TC10_ResetPasswordShowsErrorIf() {
	    final Account newAccount = new Account(Constant.NEWUSER_MAIL, Constant.PASSWORD, Constant.PID);
	    final String newPassword = "123456789";
		
	    System.out.println("TC10 - Reset password shows error if user resets password with new password same as old password");
	    String expectedErrorMsg = "The new password cannot be the same as the current password";
	    
	    System.out.println("Pre_Condition: Create and active new account");
		homePage.open();
		RegisterPage registerPage = homePage.clickRegisterLink();
		registerPage.enterRegistrationInfo(newAccount);
		MailFake mailFake2 = new MailFake();
        mailFake2.openMailFake();
        mailFake2.activeNewMail(newAccount.getEmail());
	    
	    // 1. Navigate to QA Railway Login page
	    System.out.println("1. Navigate to QA Railway Login page");
	    homePage.open();
	    LoginPage loginPage = homePage.gotoLoginPage();
	    
	    // 2. Click on "Forgot Password page" link
	    System.out.println("2. Click on 'Forgot Password page' link");
	    ForgotPasswordPage forgotPasswordPage = loginPage.gotoForgotPasswordPage();
	    
	    // 3. Enter the email address of the activated account
	    // 4. Click on "Send Instructions" button
	    System.out.println("3. Enter the email address of the activated account");
	    System.out.println("   Email: " + newAccount.getEmail());	    
	    System.out.println("4. Click on 'Send Instructions' button");
	    forgotPasswordPage.sendInstructions(newAccount.getEmail());
	    
	    // 5. Login to the mailbox (the same mailbox when creating account)
	    System.out.println("5. Login to the mailbox");
	    System.out.println("   Email: " + newAccount.getEmail());
	    MailFake mailFake = new MailFake();
        mailFake.openMailFake();
	    
	    System.out.println("6. Open email with subject containing 'Please reset your password'");
	    System.out.println("7. Click on reset link");
	    String token = mailFake.forgotMail(newAccount.getEmail());     

	    System.out.println("8. Input same password into 'new password' and 'confirm password'");
	    System.out.println("   New Password: " + newPassword);
	    System.out.println("   Confirm Password: " + newPassword);
	    System.out.println("9. Click Reset Password");
	    loginPage.resetPassword(newAccount.getPassword(), newPassword);
	    
	    SoftAssert softAssert = new SoftAssert();
	    
	    System.out.println("Verify user is redirected to Railways page and Form 'Password Change Form' is shown with the reset password token");
	    softAssert.assertTrue(loginPage.isShowTrueToken(token),
	        "User was redirected away from reset password page or Form 'Password Change Form' is not shown with the reset password token");
	    
	    System.out.println("Verify that message 'The new password cannot be the same with the current password' is shown");
	    String actualErrorMsg = loginPage.getLblResetSucces().getText();  
	    softAssert.assertEquals(actualErrorMsg, expectedErrorMsg,
	        "Error message is not displayed as expected");
	    
	    softAssert.assertAll();
	    System.out.println("✅ TC10 PASSED");
	}
	
	@Test
    public void TC11_ResetPasswordShowsErrorIfDifferentPasswords() {
        final Account newAccount = new Account(Constant.NEWUSER_MAIL, Constant.PASSWORD, Constant.PID);
        final String newPassword = "123456789";
        final String confirmPassword = "987654321"; // Khác với newPassword
        
        SoftAssert softAssert = new SoftAssert();
        
        System.out.println("TC11 - Reset password shows error if the new password and confirm password are different");
        String expectedErrorMsg = "Could not reset password. Please correct the errors and try again.";
        String expectedError2Msg = "The password confirmation did not match the new password.";
        
        // Pre-condition: Create and activate new account
        System.out.println("=== PRE-CONDITION: Create and activate new account ===");
        homePage.open();
		RegisterPage registerPage = homePage.clickRegisterLink();
		registerPage.enterRegistrationInfo(newAccount);
		MailFake mailFake2 = new MailFake();
        mailFake2.openMailFake();
        mailFake2.activeNewMail(newAccount.getEmail());
        
        // 1. Navigate to QA Railway Login page
        System.out.println("\n1. Navigate to QA Railway Login page");
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        
        // 2. Click on "Forgot Password page" link
        System.out.println("2. Click on 'Forgot Password page' link");
        ForgotPasswordPage forgotPasswordPage = loginPage.gotoForgotPasswordPage();
        
        // 3. Enter the email address of the activated account
        System.out.println("3. Enter the email address of the activated account");
        System.out.println("   Email: " + newAccount.getEmail());
        
        // 4. Click on "Send Instructions" button
        System.out.println("4. Click on 'Send Instructions' button");
        forgotPasswordPage.sendInstructions(newAccount.getEmail());
        
        // 5. Login to the mailbox (the same mailbox when creating account)
        System.out.println("\n5. Login to the mailbox");
        System.out.println("   Email: " + newAccount.getEmail());
        MailFake mailFake = new MailFake();
        mailFake.openMailFake();
        
        // 6. Open email with subject containing "Please reset your password"
        System.out.println("6. Open email with subject containing 'Please reset your password'");
        
        // 7. Click on reset link
        System.out.println("7. Click on reset link");
        String token = mailFake.forgotMail(newAccount.getEmail());
        
        System.out.println("Verify user is redirected to Railways page and Form 'Password Change Form' is shown with the reset password token");
        softAssert.assertTrue(loginPage.isShowTrueToken(token),
    	    "User was redirected away from reset password page or Form 'Password Change Form' is not shown with the reset password token");
        
        // 8. Input different input into 2 fields "new password" and "confirm password"
        System.out.println("\n8. Input different passwords:");
        System.out.println("   New Password: " + newPassword);
        System.out.println("   Confirm Password: " + confirmPassword);
        
        // 9. Click Reset Password
        System.out.println("9. Click Reset Password");
        loginPage.resetPassword(newPassword, confirmPassword);
	         
        System.out.println("Verify Error message 'Could not reset password. Please correct the errors and try again.' displays above the form.");     
        String actualErrorMsg = loginPage.getLblConfirmPasswordErrorMsg().getText();
        softAssert.assertEquals(actualErrorMsg, expectedErrorMsg,
            "Error message 'Could not reset password. Please correct the errors and try again.' is not displayed as expected");
        
        System.out.println("Verify Error message 'The password confirmation did not match the new password.' displays next to the confirm password field.");
        String actualError2Msg = loginPage.getLblResetSucces().getText();
        softAssert.assertEquals(actualError2Msg, expectedError2Msg,
            "Error message 'The password confirmation did not match the new password.' is not displayed as expected");
        
        softAssert.assertAll();
        System.out.println("✅ TC11 PASSED");
    }
}

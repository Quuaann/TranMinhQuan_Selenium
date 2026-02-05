package Railway;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

import Constant.Constant;
import DataObject.Account;

public class ForgotPasswordTest extends TestBase {
	private Account validAccount = new Account(Constant.USERNAME, Constant.PASSWORD, Constant.PID);
    
	@Test
	public void TC10_ResetPasswordShowsErrorIf() {
	    System.out.println("TC10 - Reset password shows error if user resets password with new password same as old password");
	    String expectedErrorMsg = "The new password cannot be the same as the current password";
	    
	    // Pre-condition: an activated account is existing
	    System.out.println("Pre-condition: an activated account is existing");
	    System.out.println("   Account: " + validAccount.getEmail());
	    
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
	    System.out.println("   Email: " + validAccount.getEmail());	    
	    System.out.println("4. Click on 'Send Instructions' button");
	    forgotPasswordPage.sendInstructions(validAccount.getEmail());
	    
//	    // Verify email sent successfully
//	    System.out.println("Verify reset password email sent successfully");
//	    String emailSentMsg = forgotPasswordPage.getSuccessMessage();
//	    Assert.assertTrue(emailSentMsg.contains("instructions for changing your password"),
//	        "Reset password email was not sent successfully");
	    
//	    // 5. Login to the mailbox (the same mailbox when creating account)
//	    System.out.println("5. Login to the mailbox");
//	    System.out.println("   Email: " + validAccount.getEmail());
//	    
//	    // 6. Open email with subject containing "Please reset your password" and the email
//	    System.out.println("6. Open email with subject containing 'Please reset your password'");
//	    EmailService emailService = new EmailService();
//	    String resetLink = emailService.getResetPasswordLink(validAccount.getEmail());
//	    
//	    Assert.assertNotNull(resetLink, "Reset password link not found in email");
//	    System.out.println("   Reset link retrieved: " + resetLink.substring(0, 50) + "...");
//	    
//	    // 7. Click on reset link
//	    System.out.println("7. Click on reset link");
//	    ForgotPasswordPage resetPasswordPage = forgotPasswordPage.navigateToResetPasswordPage(resetLink);
//	    
//	    // 8. Input same password into 2 fields "new password" and "confirm password"
//	    System.out.println("8. Input same password into 'new password' and 'confirm password'");
//	    System.out.println("   New Password: " + validAccount.getPassword());
//	    System.out.println("   Confirm Password: " + validAccount.getPassword());
//	    resetPasswordPage.enterNewPassword(validAccount.getPassword());
//	    resetPasswordPage.enterConfirmPassword(validAccount.getPassword());
//	    
//	    // 9. Click Reset Password
//	    System.out.println("9. Click Reset Password");
//	    resetPasswordPage.clickResetPassword();
//	    
//	    // Verify user stays on reset password page
//	    System.out.println("Verify user stays on reset password page and error message appears");
//	    boolean isResetPageDisplayed = resetPasswordPage.isResetPasswordPageDisplayed();
//	    String actualErrorMsg = resetPasswordPage.getErrorMessage();
//	    
//	    SoftAssert softAssert = new SoftAssert();
//	    softAssert.assertTrue(isResetPageDisplayed, 
//	        "User was redirected away from reset password page");
//	    softAssert.assertEquals(actualErrorMsg, expectedErrorMsg,
//	        "Error message is not displayed as expected");
//	    
//	    softAssert.assertAll();
//	    System.out.println("✅ TC10 PASSED");
	}
}

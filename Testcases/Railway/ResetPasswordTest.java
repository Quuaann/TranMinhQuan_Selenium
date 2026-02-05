package Railway;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

import Constant.Constant;

public class ResetPasswordTest extends TestBase {
    
    // TC10: New password is same as current password
    @Test
    public void TC10_ResetPasswordWithSameAsCurrent() {
        System.out.println("TC10 - New password is same as current password");        
        
        // 1. Navigate to QA Railway Login page
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        
        // 2. Click on "Forgot Password page" link
        ForgotPasswordPage forgotPasswordPage = loginPage.gotoForgotPasswordPage();
        
        // 3. Enter email address of the existing account
        forgotPasswordPage.enterEmail(Constant.EXISTING_EMAIL);
        
        // 4. Click on "Send Instructions" button
        forgotPasswordPage.clickSendInstructions();
        
        // Verify: Message "Instructions for resetting your password have been sent to your email" appears
        String sendSuccessMsg = forgotPasswordPage.getSuccessMessage();
        Assert.assertTrue(sendSuccessMsg.contains("Instructions for resetting your password"),
            "Password reset instructions should be sent");
        
        // 5. Open mailbox of account created at step 3
        // 6. Open reset password email
        // 7. Click on reset link
        
        // Giả sử có helper để lấy reset link từ email
        String resetLink = getResetPasswordLinkFromEmail(Constant.EXISTING_EMAIL);
        Assert.assertNotNull(resetLink, "Reset password link should be received");
        
        // Mở reset link
        Constant.WEBDRIVER.get(resetLink);
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage();
        
        // 8. Enter new password same as current password
        // Password cũ đã biết từ Constant
        String currentPassword = Constant.EXISTING_PASSWORD;
        
        resetPasswordPage.enterNewPassword(currentPassword);
        resetPasswordPage.enterConfirmPassword(currentPassword);
        
        // 9. Click Reset Password
        resetPasswordPage.clickResetPassword();
        
        // 10. Expected: Error message "The new password cannot be the same as the current password" is shown
        String errorMessage = resetPasswordPage.getErrorMessage();
        String expectedError = "The new password cannot be the same as the current password";
        
        Assert.assertTrue(errorMessage.contains(expectedError),
            "Error message for same password should be displayed. Actual: " + errorMessage);
        
        System.out.println("TC10 PASSED: Cannot reset to same password");
    }
}

package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class ResetPasswordPage extends GeneralPage {

	//Loactors
    private final By _txtCurrentPassword = By.xpath("//input[@id='currentPassword']");
    private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
    private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By _btnChgPassword = By.xpath("//input[@value='Change Password']");
        
    private final By _lblRegisterSuccessMsg = By.xpath("//h1[text()='Thank you for registering your account']");
    private final By _lblActiveSuccessMsg = By.xpath("//p[text()='Registration Confirmed! You can now log in to the site.']");

    // Elements
    public WebElement getTxtCurrentPassword() {
        return Constant.WEBDRIVER.findElement(_txtCurrentPassword);
    }

    public WebElement getTxtNewPassword() {
        return Constant.WEBDRIVER.findElement(_txtNewPassword);
    }

    public WebElement getBtnChgPassword() {
        return Constant.WEBDRIVER.findElement(_btnChgPassword);
    }
    
    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }

    // Methods
    public HomePage login(String username, String password) {
        // Submit login credentials
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();
        
        // Land on Home page
        return new HomePage();
    }
}

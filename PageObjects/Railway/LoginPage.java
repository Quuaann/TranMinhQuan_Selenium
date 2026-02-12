package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import DataObject.Account;

public class LoginPage extends GeneralPage {
	
    // Locators
    private final By txtUsername = By.xpath("//input[@id='username']");
    private final By txtPassword = By.xpath("//input[@id='password']");
    private final By btnLogin = By.xpath("//input[@value='login']");
    private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final By linkForgotPassword = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");
    
    private final By txtNewPassword = By.xpath("//input[@id='newPassword']");
    private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By txtResetToken = By.xpath("//input[@id='resetToken']");
    private final By btnResetPassword = By.xpath("//p[@class='form-actions']//input");
    private final By lblResetSucces = By.xpath("//p[@class='message success' and .//a]");
    private final By lblErrorMsg = By.xpath("//p[@class='message error']");
    private final By linkResetSucces = By.xpath("//p//a");
    private final By lblConfirmPasswordErrorMsg = By.xpath("//label[@class='validation-error' and @for='confirmPassword']");


    // Elements
    public WebElement getTxtUsername() {
        return Constant.WEBDRIVER.findElement(txtUsername);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(txtPassword);
    }

    public WebElement getBtnLogin() {
        return Constant.WEBDRIVER.findElement(btnLogin);
    }
    
    public WebElement getLblLoginErrorMsg() {
        return Constant.WEBDRIVER.findElement(lblLoginErrorMsg);
    }
    
    public WebElement getLinkForgotPassword() {
        return Constant.WEBDRIVER.findElement(linkForgotPassword);
    }
    
    public WebElement getTxtNewPassword() {
        return Constant.WEBDRIVER.findElement(txtNewPassword);
    }
    
    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(txtConfirmPassword);
    }
    
    public WebElement getTxtResetToken() {
        return Constant.WEBDRIVER.findElement(txtResetToken);
    }
    
    public WebElement getBtnResetPassword() {
        return Constant.WEBDRIVER.findElement(btnResetPassword);
    }
    
    public WebElement getLblResetSucces() {
        return Constant.WEBDRIVER.findElement(lblResetSucces);
    }
    
    public WebElement getLblResetError() {
        return Constant.WEBDRIVER.findElement(lblErrorMsg);
    }
    
    public WebElement getLinkResetSucces() {
        return Constant.WEBDRIVER.findElement(linkResetSucces);
    }
    
    public WebElement getLblConfirmPasswordErrorMsg() {
        return Constant.WEBDRIVER.findElement(lblConfirmPasswordErrorMsg);
    }
    
    // Methods
    public boolean isLogined() {
    	 String welcomeMsg = getWelcomeMessage();
         if (welcomeMsg.equals("Welcome guest!")) {
             return false;
         }
         return true;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T login(Account account) {       
        this.getTxtUsername().sendKeys(account.getEmail());
        this.getTxtPassword().sendKeys(account.getPassword());
        Utilities.scrollToElement(btnLogin);
        this.getBtnLogin().click();
        
        if (isLogined()) {
            return (T) new HomePage();
        } else {
            return (T) this;
        }
    }
    
    public void repeatLogin(Account account, int time) {
    	for (int i = 0; i < time; i++) {
	        this.login(account);
	    }
    }
    
    public ResetPasswordPage gotoForgotPasswordPage() {
    	Utilities.scrollToElement(linkForgotPassword);
    	this.getLinkForgotPassword().click();
    	return new ResetPasswordPage();
    }
    
    public boolean isResetSuccess() {
   	 String welcomeMsg = getWelcomeMessage();
        if (welcomeMsg.equals("Welcome guest!")) {
            return false;
        }
        return true;
   }
    
    @SuppressWarnings("unchecked")
    public <T> T resetPassword(String newPassword, String confirmPassword) {       
        this.getTxtNewPassword().sendKeys(newPassword);
        this.getTxtConfirmPassword().sendKeys(confirmPassword);
        Utilities.scrollToElement(btnResetPassword);
        this.getBtnResetPassword().click();
        
        if (isLogined()) {
            return (T) new HomePage();
        } else {
            return (T) this;
        }
    }
    
    public boolean isShowTrueToken(String token) {
    	String currentToken = this.getTxtResetToken().getAttribute("value");
    	String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
        if(currentUrl.contains("saferailway.somee.com/Account/PasswordReset") && currentToken.equals(token)) {
        	return true;
        }
        
        return false;
    }
}
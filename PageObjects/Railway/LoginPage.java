package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import DataObject.Account;

public class LoginPage extends GeneralPage {
	
    // Locators
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='login']");
    private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final By _linkForgotPassword = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");
    
    private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
    private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By _txtResetToken = By.xpath("//input[@id='resetToken']");
    private final By _btnResetPassword = By.xpath("//p[@class='form-actions']//input");
    private final By _lblResetSucces = By.xpath("//p[@class='message success' and .//a]");
    private final By _lblErrorMsg = By.xpath("//p[@class='message error']");
    private final By _linkResetSucces = By.xpath("//p//a");
    private final By _lblConfirmPasswordErrorMsg = By.xpath("//label[@class='validation-error' and @for='confirmPassword']");


    // Elements
    public WebElement getTxtUsername() {
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }

    public WebElement getBtnLogin() {
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }
    
    public WebElement getLblLoginErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
    }
    
    public WebElement getLinkForgotPassword() {
        return Constant.WEBDRIVER.findElement(_linkForgotPassword);
    }
    
    public WebElement getTxtNewPassword() {
        return Constant.WEBDRIVER.findElement(_txtNewPassword);
    }
    
    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }
    
    public WebElement getTxtResetToken() {
        return Constant.WEBDRIVER.findElement(_txtResetToken);
    }
    
    public WebElement getBtnResetPassword() {
        return Constant.WEBDRIVER.findElement(_btnResetPassword);
    }
    
    public WebElement getLblResetSucces() {
        return Constant.WEBDRIVER.findElement(_lblResetSucces);
    }
    
    public WebElement getLblResetError() {
        return Constant.WEBDRIVER.findElement(_lblErrorMsg);
    }
    
    public WebElement getLinkResetSucces() {
        return Constant.WEBDRIVER.findElement(_linkResetSucces);
    }
    
    public WebElement getLblConfirmPasswordErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblConfirmPasswordErrorMsg);
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
        this.getBtnLogin().click();
        
        if (isLogined()) {
            return (T) new HomePage();
        } else {
            return (T) this;
        }
    }
    
    public ForgotPasswordPage gotoForgotPasswordPage() {
    	Utilities.scrollToElement(_linkForgotPassword);
    	this.getLinkForgotPassword().click();
    	return new ForgotPasswordPage();
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
        this.getBtnResetPassword().click();
        
        if (isLogined()) {
            return (T) new HomePage();
        } else {
            return (T) this;
        }
    }
    
    public boolean isShowTrueToken(String token) {
    	String currentToken = this.getTxtResetToken().getText();
    	String expectedToken= token;
    	String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
        String expectedUrl = Constant.RAILWAY_URL;
        if(currentUrl == expectedUrl && currentToken == expectedToken) {
        	return true;
        }
        return false;
    }
}
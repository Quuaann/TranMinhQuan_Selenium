package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class RegisterPage extends GeneralPage {
	
	//Loactors
	private final By _txtEmail = By.xpath("//input[@id='email']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By _txtPid = By.xpath("//input[@id='pid']");
    private final By _btnRegister = By.xpath("//input[@value='Register']");
    private final By _lblRegisterErrorMsg = By.xpath("//p[@class='message error']");
    private final By _lblPasswordErrorMsg = By.xpath("//label[@class='validation-error' and @for='password']");
    private final By _lblPidErrorMsg = By.xpath("//label[@class='validation-error' and @for='pid']");
    
    private final By _lblRegisterSuccessMsg = By.xpath("//h1[text()='Thank you for registering your account']");
    private final By _lblActiveSuccessMsg = By.xpath("//p[text()='Registration Confirmed! You can now log in to the site.']");
    
	private final By _selectedRegister = By.xpath("//li[@class='selected']//span[text()='Register']");

	//Elements
    public WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(_txtEmail);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }
    
    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }
    
    public WebElement getTxtPid() {
        return Constant.WEBDRIVER.findElement(_txtPid);
    }
    
    public WebElement getBtnRegister() {
        return Constant.WEBDRIVER.findElement(_btnRegister);
    }
    
    public WebElement getLblRegisterErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblRegisterErrorMsg);
    }
    
    public WebElement getLblPasswordErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblPasswordErrorMsg);
    }
    
    public WebElement getLblPidErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblPidErrorMsg);
    }
    
    public WebElement getLblRegisterSuccessfulMsg() {
        return Constant.WEBDRIVER.findElement(_lblRegisterSuccessMsg);
    }
    
    public WebElement getLblActiveSuccessfulMsg() {
        return Constant.WEBDRIVER.findElement(_lblActiveSuccessMsg);
    } 
    
    public WebElement getSelectedRegister() {
        return Constant.WEBDRIVER.findElement(_selectedRegister);
    }
    
	//Methods	
	public RegisterPage enterRegistrationInfo(String email, String password, String confirmPassword, String pid) {
		
		this.getTxtEmail().sendKeys(email);
		this.getTxtPassword().sendKeys(password);
		this.getTxtConfirmPassword().sendKeys(confirmPassword);
		this.getTxtPid().sendKeys(pid);
		
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		this.getBtnRegister().click();
		
		return this;
	}
	
			
}

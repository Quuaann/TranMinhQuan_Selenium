package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import DataObject.Account;

public class RegisterPage extends GeneralPage {
	
	//Locators
	private final By txtEmail = By.xpath("//input[@id='email']");
    private final By txtPassword = By.xpath("//input[@id='password']");
    private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By txtPid = By.xpath("//input[@id='pid']");
    private final By btnRegister = By.xpath("//input[@value='Register']");
    private final By lblRegisterErrorMsg = By.xpath("//p[@class='message error']");
    private final By lblPasswordErrorMsg = By.xpath("//label[@class='validation-error' and @for='password']");
    private final By lblPidErrorMsg = By.xpath("//label[@class='validation-error' and @for='pid']");
    
    private final By lblRegisterSuccessMsg = By.xpath("//h1[text()='Thank you for registering your account']");
    private final By lblActiveSuccessMsg = By.xpath("//p[text()='Registration Confirmed! You can now log in to the site.']");
    
	private final By selectedRegister = By.xpath("//li[@class='selected']//span[text()='Register']");

	//Elements
    public WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(txtEmail);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(txtPassword);
    }
    
    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(txtConfirmPassword);
    }
    
    public WebElement getTxtPid() {
        return Constant.WEBDRIVER.findElement(txtPid);
    }
    
    public WebElement getBtnRegister() {
        return Constant.WEBDRIVER.findElement(btnRegister);
    }
    
    public WebElement getLblRegisterErrorMsg() {
        return Constant.WEBDRIVER.findElement(lblRegisterErrorMsg);
    }
    
    public WebElement getLblPasswordErrorMsg() {
        return Constant.WEBDRIVER.findElement(lblPasswordErrorMsg);
    }
    
    public WebElement getLblPidErrorMsg() {
        return Constant.WEBDRIVER.findElement(lblPidErrorMsg);
    }
    
    public WebElement getLblRegisterSuccessfulMsg() {
        return Constant.WEBDRIVER.findElement(lblRegisterSuccessMsg);
    }
    
    public WebElement getLblActiveSuccessfulMsg() {
        return Constant.WEBDRIVER.findElement(lblActiveSuccessMsg);
    } 
    
    public WebElement getSelectedRegister() {
        return Constant.WEBDRIVER.findElement(selectedRegister);
    }
    
	//Methods	
	public RegisterPage enterRegistrationInfo(Account account) {
		
		this.getTxtEmail().sendKeys(account.getEmail());
		this.getTxtPassword().sendKeys(account.getPassword());
		this.getTxtConfirmPassword().sendKeys(account.getConfirmPassword());
		this.getTxtPid().sendKeys(account.getPid());
		
		Utilities.scrollToElement(btnRegister);
		this.getBtnRegister().click();
		
		return this;
	}
}

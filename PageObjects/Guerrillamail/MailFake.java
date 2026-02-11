package Guerrillamail;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Constant.Constant;
import Common.Utilities;
public class MailFake {
	
	//Locators
	private final By _tbEmail = By.xpath("//span[contains(@class, 'editable')]//input");
	private final By _trEmail = By.xpath("//tbody[@id='email_list']//td[normalize-space()='thanhletraining03@gmail.com']");
	private final By _trForgotEmail = By.xpath("//tbody[@id='email_list']//span[starts-with(., 'Use this')]");
	private final By _btnForget = By.xpath("//a[@id='forget_button']");
	private final By _btnNewMail = By.xpath("//span[@id='inbox-id']");
	private final By _checkbox = By.xpath("//input[@id='use-alias']");
	private final By _btnSet = By.xpath("//button[@class='save button small']");
	public static final String FAKEMAIL_URL = "https://www.guerrillamail.com";
	private final By _activeLink = By.xpath("//div[contains(@class, 'email_body')]//a");
	private final By _bodyEmail = By.xpath("//div[@class= 'email_body']");
	
	//Elements
	public WebElement getTbNewEmail() {
        return Constant.WEBDRIVER.findElement(_tbEmail);
    }
	
	public WebElement getBtnForget() {
        return Constant.WEBDRIVER.findElement(_btnForget);
    }
	
	public WebElement getBtnNewMail() {
        return Constant.WEBDRIVER.findElement(_btnNewMail);
    }
	
	public WebElement getCheckbox() {
        return Constant.WEBDRIVER.findElement(_checkbox);
    }
	
	public WebElement getBtnSet() {
        return Constant.WEBDRIVER.findElement(_btnSet);
    }
	
	public WebElement getTrNewMail() {
        return Constant.WEBDRIVER.findElement(_trEmail);
    }
	
	public WebElement getActiveLink() {
        return Constant.WEBDRIVER.findElement(_activeLink);
    }
	
	public WebElement getTrForgotMail() {
        return Constant.WEBDRIVER.findElement(_trForgotEmail);
    }
	
	public WebElement getBodyEmail() {
        System.out.println("Test :"+_bodyEmail );

        return Constant.WEBDRIVER.findElement(_bodyEmail);
    }
	
	//Methods
	public String getToken() {
		Utilities.waitForVisible(_bodyEmail);
		String emailContent = this.getBodyEmail().getText();
		System.out.println(emailContent);
		if (emailContent.contains("The token is: ")) {
	        String[] parts = emailContent.split("The token is: ");
	        if (parts.length > 1) {
	            String tokenPart = parts[1].split("\\.")[0]; // Lấy phần trước dấu chấm
	            return tokenPart.trim();
	        }
	    }
        return "";
	}
		
	public void waitAndRefreshUntilVisible(WebDriver driver, By locator, int retries) {
	    for (int i = 0; i <= retries; i++) { 
	    	try {
		        Utilities.waitForVisible(locator,10); 
		        Constant.WEBDRIVER.navigate().refresh();
		        this.getCheckbox().click();
		        return;
	    	} catch (Exception e) {
	    		
		    }
	    }
	}
	
	public String forgotMail(String newmail) {
		this.getCheckbox().click();
		this.getBtnNewMail().click();
		this.getTbNewEmail().clear();
		this.getTbNewEmail().sendKeys(newmail);
		this.getBtnSet().click();
		
		this.waitAndRefreshUntilVisible(Constant.WEBDRIVER, _trForgotEmail, 4);
        
        Utilities.scrollToElement(_checkbox);
        this.getTrForgotMail().click();
        
        final String token = this.getToken();
		
        Utilities.waitForVisible(_activeLink);
        this.getActiveLink().click();
        
		ArrayList<String> tabs = new ArrayList<>(Constant.WEBDRIVER.getWindowHandles());
		tabs.get(2);
	    Constant.WEBDRIVER.switchTo().window(tabs.get(tabs.size() - 1));
	    
	    return token;
	}
	
	public void activeNewMail(String newmail) {
		this.getCheckbox().click();
		this.getBtnNewMail().click();
		this.getTbNewEmail().sendKeys(newmail);
		this.getBtnSet().click();
        
		this.waitAndRefreshUntilVisible(Constant.WEBDRIVER, _trEmail, 4);
        
        Utilities.scrollToElement(_checkbox);
        this.getTrNewMail().click();
		
        Utilities.waitForVisible(_activeLink);
        this.getActiveLink().click();
        
		ArrayList<String> tabs = new ArrayList<>(Constant.WEBDRIVER.getWindowHandles());
		tabs.get(2);
	    Constant.WEBDRIVER.switchTo().window(tabs.get(tabs.size() - 1));
	}
	
	public MailFake openMailFake() {
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
	    js.executeScript("window.open();");
	    ArrayList<String> tabs = new ArrayList<>(Constant.WEBDRIVER.getWindowHandles());
	    tabs.get(1); 
	    Constant.WEBDRIVER.switchTo().window(tabs.get(tabs.size() - 1));
	    Constant.WEBDRIVER.navigate().to(FAKEMAIL_URL);
		return this;
	}
	
	
}

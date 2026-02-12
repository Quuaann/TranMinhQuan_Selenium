package Guerrillamail;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import Constant.Constant;
import Common.Utilities;
public class MailFake {
	
	//Locators
	private final By tbEmail = By.xpath("//span[contains(@class, 'editable')]//input");
	private final By trEmail = By.xpath("//tbody[@id='email_list']//td[normalize-space()='thanhletraining03@gmail.com']");
	private final By trForgotEmail = By.xpath("//tbody[@id='email_list']//span[starts-with(., 'Use this')]");
	private final By btnForget = By.xpath("//a[@id='forget_button']");
	private final By btnNewMail = By.xpath("//span[@id='inbox-id']");
	private final By checkbox = By.xpath("//input[@id='use-alias']");
	private final By btnSet = By.xpath("//button[@class='save button small']");
	private final By activeLink = By.xpath("//div[contains(@class, 'email_body')]//a");
	private final By bodyEmail = By.xpath("//div[@class= 'email_body']");
	
	//Elements
	public WebElement getTbNewEmail() {
        return Constant.WEBDRIVER.findElement(tbEmail);
    }
	
	public WebElement getBtnForget() {
        return Constant.WEBDRIVER.findElement(btnForget);
    }
	
	public WebElement getBtnNewMail() {
        return Constant.WEBDRIVER.findElement(btnNewMail);
    }
	
	public WebElement getCheckbox() {
        return Constant.WEBDRIVER.findElement(checkbox);
    }
	
	public WebElement getBtnSet() {
        return Constant.WEBDRIVER.findElement(btnSet);
    }
	
	public WebElement getTrNewMail() {
        return Constant.WEBDRIVER.findElement(trEmail);
    }
	
	public WebElement getActiveLink() {
        return Constant.WEBDRIVER.findElement(activeLink);
    }
	
	public WebElement getTrForgotMail() {
        return Constant.WEBDRIVER.findElement(trForgotEmail);
    }
	
	public WebElement getBodyEmail() {
        return Constant.WEBDRIVER.findElement(bodyEmail);
    }
	
	//Methods
	public String getToken() {
		Utilities.waitForVisible(bodyEmail);
		String emailContent = this.getBodyEmail().getText();
		if (emailContent.contains("The token is: ")) {
	        String[] parts = emailContent.split("The token is: ");
	        if (parts.length > 1) {
	            String tokenPart = parts[1].split("\\.")[0]; // Lấy phần trước dấu chấm
	            return tokenPart.trim();
	        }
	    }
        return "";
	}
	
	public String forgotMail(String newUser) {
		this.getCheckbox().click();
		this.getBtnNewMail().click();
		this.getTbNewEmail().clear();
		this.getTbNewEmail().sendKeys(newUser);
		this.getBtnSet().click();
		
		Utilities.waitForVisible(trForgotEmail);
        Utilities.scrollToElement(checkbox);
        this.getTrForgotMail().click();
        
        final String token = this.getToken();
		
        Utilities.waitForVisible(activeLink);
        this.getActiveLink().click();
        
		ArrayList<String> tabs = new ArrayList<>(Constant.WEBDRIVER.getWindowHandles());
		tabs.get(2);
	    Constant.WEBDRIVER.switchTo().window(tabs.get(tabs.size() - 1));
	    
	    return token;
	}
	
	public void activeNewMail(String newUser) {
		this.getCheckbox().click();
		this.getBtnNewMail().click();
		this.getTbNewEmail().sendKeys(newUser);
		this.getBtnSet().click();
        
		Utilities.waitForVisible(trEmail);
        Utilities.scrollToElement(checkbox);
        this.getTrNewMail().click();
		
        Utilities.waitForVisible(activeLink);
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
	    Constant.WEBDRIVER.navigate().to(Constant.FAKEMAIL_URL);
		return this;
	}
	
	
}

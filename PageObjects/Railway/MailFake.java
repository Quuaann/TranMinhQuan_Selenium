package Railway;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait; 
import Constant.Constant;

public class MailFake {
	//Loactors
	private final By _tbEmail = By.xpath("//span[contains(@class, 'editable')]//input");
	private final By _trEmail = By.xpath("//tr[@class='mail_row  email_unread pointer click-set']");
	private final By _btnForget = By.xpath("//a[@id='forget_button']");
	private final By _btnNewMail = By.xpath("//span[@id='inbox-id']");
	private final By _checkbox = By.xpath("//input[@id='use-alias']");
	private final By _btnSet = By.xpath("//button[@class='save button small']");
	public static final String FAKEMAIL_URL = "https://www.guerrillamail.com";
	private final By _activeLink = By.xpath("//div[contains(@class, 'email_page')]//a[@target='_blank']");
	
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
	
	//Methods
	public void activeNewMail(String newmail) {
		//this.getBtnForget().click();
		this.getCheckbox().click();
		this.getBtnNewMail().click();
		this.getTbNewEmail().sendKeys(newmail);
		this.getBtnSet().click();
		try {
	        System.out.println("Waiting 10 seconds for email activation...");
	        Thread.sleep(12000);
	        System.out.println("10 seconds wait completed");
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	        System.out.println("Wait was interrupted");
	    }
		
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        
		this.getTrNewMail().click();
		try {
	        System.out.println("Waiting 10 seconds for email activation...");
	        Thread.sleep(1000);
	        System.out.println("10 seconds wait completed");
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	        System.out.println("Wait was interrupted");
	    }
		this.getActiveLink().click();
	}
	
	public MailFake openMailFake() {
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
	    js.executeScript("window.open();");
	    ArrayList<String> tabs = new ArrayList<>(Constant.WEBDRIVER.getWindowHandles());
	    String aboutBlankTab = tabs.get(1);  // Tab thứ 2 là about:blank
	    Constant.WEBDRIVER.switchTo().window(tabs.get(tabs.size() - 1));
		Constant.WEBDRIVER.navigate().to(FAKEMAIL_URL);
		return this;
	}
	
	
}

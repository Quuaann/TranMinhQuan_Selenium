package Railway;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait; 
import Constant.Constant;
import Common.Utilities;
public class MailFake {
	//Loactors
	private final By _tbEmail = By.xpath("//span[contains(@class, 'editable')]//input");
	private final By _trEmail = By.xpath("//tbody[@id='email_list']//td[normalize-space()='thanhletraining03@gmail.com']");
	private final By _btnForget = By.xpath("//a[@id='forget_button']");
	private final By _btnNewMail = By.xpath("//span[@id='inbox-id']");
	private final By _checkbox = By.xpath("//input[@id='use-alias']");
	private final By _btnSet = By.xpath("//button[@class='save button small']");
	public static final String FAKEMAIL_URL = "https://www.guerrillamail.com";
	private final By _activeLink = By.xpath("//div[contains(@class, 'email_body')]//a");
	
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
		this.getCheckbox().click();
		this.getBtnNewMail().click();
		this.getTbNewEmail().sendKeys(newmail);
		this.getBtnSet().click();
        
        Utilities.waitForVisible(_trEmail);
        Utilities.scrollToElement(_checkbox);
        this.getTrNewMail().click();
		
        Utilities.waitForVisible(_activeLink);
        this.getActiveLink().click();
        
		ArrayList<String> tabs = new ArrayList<>(Constant.WEBDRIVER.getWindowHandles());
		String railwayTab = tabs.get(2);
	    Constant.WEBDRIVER.switchTo().window(tabs.get(tabs.size() - 1));
	}
	
	public MailFake openMailFake() {
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
	    js.executeScript("window.open();");
	    ArrayList<String> tabs = new ArrayList<>(Constant.WEBDRIVER.getWindowHandles());
	    String aboutBlankTab = tabs.get(1); 
	    Constant.WEBDRIVER.switchTo().window(tabs.get(tabs.size() - 1));
	    Constant.WEBDRIVER.navigate().to(FAKEMAIL_URL);
		return this;
	}
	
	
}

package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;
import Constant.TabMenu;

public class HomePage extends GeneralPage {
	
	//Locators
	private final By _linkRegister = By.xpath("//a[@href='/Account/Register.cshtml']");
	private final By _selectedHome = By.xpath("//li[@class='selected']//span[text()='Home']");
	
	//Elements
	public WebElement getLinkRegister() {
        return Constant.WEBDRIVER.findElement(_linkRegister);
    }
	
	public WebElement getSelectedHome() {
        return Constant.WEBDRIVER.findElement(_selectedHome);
    }
	
	//Methods
	
	public HomePage open() {
		
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}
	
	public RegisterPage clickRegisterLink() {
		this.getLinkRegister().click();
		
		return new RegisterPage();
	}
	
	public boolean isLogoutTabPresent() {
		String xpathLogout = this.getTabXpath(TabMenu.LOGOUT);
    	return !Constant.WEBDRIVER.findElements(By.xpath(xpathLogout)).isEmpty();
    }
}

package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class HomePage extends GeneralPage {
	
	//Loactors
	private final By _linkRegister = By.xpath("//a[@href='/Account/Register.cshtml']");
	
	//Elements
	public WebElement getLinkRegister() {
        return Constant.WEBDRIVER.findElement(_linkRegister);
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
}

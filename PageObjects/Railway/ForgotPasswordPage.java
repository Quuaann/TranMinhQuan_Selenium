package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class ForgotPasswordPage extends GeneralPage {

	// Locators
    private final By _txtEmail = By.xpath("//input[@id='email']");
    private final By _btnSendIntruction = By.xpath("//p[@class='form-actions']//input");
    
    // Elements
    public WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(_txtEmail);
    }
    
    public WebElement getBtnSendIntruction() {
        return Constant.WEBDRIVER.findElement(_btnSendIntruction);
    }

    // Methods
    public void sendInstructions(String email) {       
        this.getTxtEmail().sendKeys(email);
        this.getBtnSendIntruction().click();
    }
}

package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class ResetPasswordPage extends GeneralPage {

	// Locators
    private final By txtEmail = By.xpath("//input[@id='email']");
    private final By btnSendIntruction = By.xpath("//p[@class='form-actions']//input");
    
    // Elements
    public WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(txtEmail);
    }
    
    public WebElement getBtnSendIntruction() {
        return Constant.WEBDRIVER.findElement(btnSendIntruction);
    }
    
    // Methods
    public void sendInstructions(String email) {       
        this.getTxtEmail().sendKeys(email);
        Utilities.scrollToElement(btnSendIntruction);
        Utilities.waitForClickable(btnSendIntruction);
        this.getBtnSendIntruction().click();
    }
    
    
}

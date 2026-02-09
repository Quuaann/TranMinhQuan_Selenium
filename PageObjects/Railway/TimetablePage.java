package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class TimetablePage {
    
    // Locators
    private final By _linkCheckPriceSgDn = By.xpath("//tr[td[2][text()='Đà Nẵng'] and td[3][text()='Sài Gòn']]//a[contains(@href, 'TicketPrice')]");
    private final By _linkBookTicketQnH = By.xpath("//tr[td[2][text()='Quảng Ngãi'] and td[3][text()='Huế']]//a[contains(@href, 'BookTicket')]");
    
    // Elements
    public WebElement getLinkCheckPrice() {
        return Constant.WEBDRIVER.findElement(_linkCheckPriceSgDn);
    }
    
    public WebElement getLinkBookTicket() {
        return Constant.WEBDRIVER.findElement(_linkBookTicketQnH);
    }
    
    // Methods
    public TicketPricePage clickCheckPriceForRoute() {
    	Utilities.scrollToElement(_linkCheckPriceSgDn);
        this.getLinkCheckPrice().click();
        return new TicketPricePage();
    }
    
    public BookTicketPage clickBookTicketForRoute() {
    	Utilities.scrollToElement(_linkBookTicketQnH);
        this.getLinkBookTicket().click();
        return new BookTicketPage();
    }
}

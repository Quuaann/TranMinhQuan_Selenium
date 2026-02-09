package Railway;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class TicketPricePage {
	
	// Locators
    private final By _selectedTicketPrice = By.xpath("//li[@class='selected']//span[text()='Ticket price']");
    private final By _thHeader = By.xpath("//tr[@class='TableSmallHeader']//th");
    private final By _trLastTicket = By.xpath("//table[@class='MyTable MedTable']//tr[last()]");
    
    // Elements
    public WebElement getSelectedTicketPrice() {
        return Constant.WEBDRIVER.findElement(_selectedTicketPrice);
    }
    
    public WebElement getThHeader() {
        return Constant.WEBDRIVER.findElement(_thHeader);
    }
    
    public WebElement getLastTicketPrice() {
        return Constant.WEBDRIVER.findElement(_trLastTicket);
    }
    
    // Methods
    public String getTxtTicketTableCell(int td) {

        WebElement lastRow = getLastTicketPrice();
        
        List<WebElement> cells = lastRow.findElements(By.tagName("td"));
        
        if (cells.size() >= 6) {
        	return cells.get(td).getText().trim();
        }
        return "";
    }
}

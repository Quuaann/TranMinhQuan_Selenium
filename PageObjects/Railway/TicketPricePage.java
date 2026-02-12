package Railway;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;
import DataObject.TicketPrice;

public class TicketPricePage extends GeneralPage{
	
	// Locators
    private final By selectedTicketPrice = By.xpath("//li[@class='selected']//span[text()='Ticket price']");
    private final By thHeader = By.xpath("//tr[@class='TableSmallHeader']//th");
    private final By trLastTicket = By.xpath("//table[@class='MyTable MedTable']//tr[last()]");
    
    // Elements
    public WebElement getSelectedTicketPrice() {
        return Constant.WEBDRIVER.findElement(selectedTicketPrice);
    }
    
    public WebElement getThHeader() {
        return Constant.WEBDRIVER.findElement(thHeader);
    }
    
    public WebElement getLastTicketPrice() {
        return Constant.WEBDRIVER.findElement(trLastTicket);
    }
    
    // Methods
    public TicketPrice getTxtTicketTableCell() {
    	TicketPrice ticketPrice = new TicketPrice();
        List<WebElement> cells = getLastTicketPrice().findElements(By.tagName("td"));
        if (cells.size() >= 6)
        	for (int i = 0; i<6; i++) {
	        	ticketPrice.setPriceByIndex(i, cells.get(i).getText().trim());
	        }
        return ticketPrice;
    }
}

package Railway;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import DataObject.BookTicket;

public class MyTicketPage extends GeneralPage{
	
	// Locators
	private final By msgManageTicket = By.xpath("//div[@id='content']//h1");
    private final String tbMyTicket = "//table[@class='MyTable']//tr["
    	    + "td[2][normalize-space()='%s'] and "
    	    + "td[3][normalize-space()='%s'] and "
    	    + "td[4][normalize-space()='%s'] and "
    	    + "td[5][normalize-space()='%s']"
    	    + "]//input[@value='Cancel']";
   
    // Elements
    public WebElement getMsgManageTicket() {
        return Constant.WEBDRIVER.findElement(msgManageTicket);
    }
    
    // Methods
    public MyTicketPage clickCancelTicketByBookTicket(BookTicket bt) {
    	String xpath = String.format(tbMyTicket, bt.getDepartFrom(), bt.getArriveAt(), bt.getSeatType(), bt.getDepartDate(), bt.getTicketAmount()); 
    	Utilities.scrollToElement(Constant.WEBDRIVER.findElement(By.xpath(xpath)));
    	Constant.WEBDRIVER.findElement(By.xpath(xpath)).click();
      return this;
    }
    
    public boolean isTicketStillPresent(BookTicket bt) {
    	String xpath = String.format(tbMyTicket, bt.getDepartFrom(), bt.getArriveAt(), bt.getSeatType(), bt.getDepartDate(), bt.getTicketAmount()); 
    	return !Constant.WEBDRIVER.findElements(By.xpath(xpath)).isEmpty();
    }
    
    public String getTextMsgManageTicket() {
    	return this.getMsgManageTicket().getText();
    }
}

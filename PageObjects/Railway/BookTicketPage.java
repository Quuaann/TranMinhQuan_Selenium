package Railway;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Utilities;
import Constant.Constant;
import DataObject.BookTicket;

public class BookTicketPage {
	
	// Locators
    private final By _txtDepartDate = By.xpath("//select[@name='Date']");
    private final By _txtDepartFrom = By.xpath("//select[@name='DepartStation']");
    private final By _txtArriveAt = By.xpath("//select[@name='ArriveStation']");
    private final By _txtSeatType = By.xpath("//select[@name='SeatType']");
    private final By _txtTicketAmount = By.xpath("//select[@name='TicketAmount']");
    private final By _btnBookTicket = By.xpath("//input[@type='submit' and @value='Book ticket']");
    
    private final By _msgBookSuccess = By.xpath("//div[@id='content']//h1");
    private final By _trLastTicket = By.xpath("//table[@class='MyTable WideTable']//tr[last()]");

    
    // Elements
    public WebElement getTxtDepartDate() {
        return Constant.WEBDRIVER.findElement(_txtDepartDate);
    }
    
    public WebElement getTxtDepartFrom() {
        return Constant.WEBDRIVER.findElement(_txtDepartFrom);
    }

    public WebElement getTxtArriveAt() {
        return Constant.WEBDRIVER.findElement(_txtArriveAt);
    }

    public WebElement getTxtSeatType() {
        return Constant.WEBDRIVER.findElement(_txtSeatType);
    }

    public WebElement getTxtTicketAmount() {
        return Constant.WEBDRIVER.findElement(_txtTicketAmount);
    }
    
    public WebElement getBtnBookTicket() {
        return Constant.WEBDRIVER.findElement(_btnBookTicket);
    }
    
    public WebElement getMsgBookSuccess() {
        return Constant.WEBDRIVER.findElement(_msgBookSuccess);
    }
    
    public WebElement getTrLastTicket() {
        return Constant.WEBDRIVER.findElement(_trLastTicket);
    }
    
    // Methods
    public BookTicketPage bookTicket(BookTicket bt) { 
    	Select dropdownDepartDate = new Select(getTxtDepartDate());
    	dropdownDepartDate.selectByVisibleText(bt.getDepartDate());
    	
    	Select dropdownDepartFrom = new Select(getTxtDepartFrom());
    	dropdownDepartFrom.selectByVisibleText(bt.getDepartFrom());
    	
    	Utilities.waitUntilStale(getTxtArriveAt());
    	Select dropdownArriveAt = new Select(getTxtArriveAt());
    	dropdownArriveAt.selectByVisibleText(bt.getArriveAt());

    	Select dropdownSeatType = new Select(getTxtSeatType());
    	dropdownSeatType.selectByVisibleText(bt.getSeatType());
    	
    	Select dropdownTicketAmount = new Select(getTxtTicketAmount());
    	dropdownTicketAmount.selectByVisibleText(bt.getTicketAmount());
		
		Utilities.scrollToElement(_btnBookTicket);
		this.getBtnBookTicket().click();
		
		return this;
    }
    
    public BookTicketPage bookTicketApart(String departDate, String departFrom, String arriveAt, String seatType, String ticketAmount) { 
    	if (!"".equals(departDate)) {	
    		Select dropdownDepartDate = new Select(getTxtDepartDate());
	    	dropdownDepartDate.selectByVisibleText(departDate);
    	}
    	if (!"".equals(departFrom)) {	
	    	Select dropdownDepartFrom = new Select(getTxtDepartFrom());
	    	dropdownDepartFrom.selectByVisibleText(departFrom);
    	}
    	if (!"".equals(arriveAt)) {
        	Utilities.waitUntilStale(getTxtArriveAt());
	    	Select dropdownArriveAt = new Select(getTxtArriveAt());
	    	dropdownArriveAt.selectByVisibleText(arriveAt);
    	}
    	if (!"".equals(seatType)) {
	    	Select dropdownSeatType = new Select(getTxtSeatType());
	    	dropdownSeatType.selectByVisibleText(seatType);
    	}
    	if (!"".equals(ticketAmount)) {
	    	Select dropdownTicketAmount = new Select(getTxtTicketAmount());
	    	dropdownTicketAmount.selectByVisibleText(ticketAmount);
    	}
			Utilities.scrollToElement(_btnBookTicket);
			this.getBtnBookTicket().click();
		
		return this;
    }
    
//    public boolean isTicketDisplayed(BookTicket bt) {
//        String xpath = String.format(
//            "//table[@class='MyTable WideTable']//tr[td[1][normalize-space()='%s'] " +
//            "and td[2][normalize-space()='%s'] " +
//            "and td[3][normalize-space()='%s'] " +
//            "and td[4][normalize-space()='%s'] " +
//            "and td[5][normalize-space()='%s']]",
//            bt.getDepartDate(),
//            bt.getDepartFrom(),
//            bt.getArriveAt(),
//            bt.getSeatType(),
//            bt.getTicketAmount()
//        );
//        
//        return Constant.WEBDRIVER.findElements(By.xpath(xpath)).size() > 0;
//    }
    
    public BookTicket getActualBookTicket(BookTicket bt) {

        WebElement lastRow = getTrLastTicket();
        
        // Lấy tất cả các ô trong dòng
        List<WebElement> cells = lastRow.findElements(By.tagName("td"));
        
        if (cells.size() >= 8) {
            // Gán giá trị từ bảng vào đối tượng BookTicket
            bt.setDepartFrom(cells.get(0).getText().trim());
            bt.setArriveAt(cells.get(1).getText().trim());
            bt.setSeatType(cells.get(2).getText().trim());
            bt.setDepartDate(cells.get(3).getText().trim());
            bt.setTicketAmount(cells.get(6).getText().trim());
        }
        
        return bt;
    }

}

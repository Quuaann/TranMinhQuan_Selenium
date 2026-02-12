package Railway;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Utilities;
import Constant.Constant;
import Constant.Province;
import Constant.SeatType;
import DataObject.BookTicket;

public class BookTicketPage extends GeneralPage{
	
	// Locators
    private final By txtDepartDate = By.xpath("//select[@name='Date']");
    private final By txtDepartFrom = By.xpath("//select[@name='DepartStation']");
    private final By txtArriveAt = By.xpath("//select[@name='ArriveStation']");
    private final By txtSeatType = By.xpath("//select[@name='SeatType']");
    private final By txtTicketAmount = By.xpath("//select[@name='TicketAmount']");
    private final By btnBookTicket = By.xpath("//input[@type='submit' and @value='Book ticket']");
    
    private final By msgBookSuccess = By.xpath("//div[@id='content']//h1");
    private final By trLastTicket = By.xpath("//table[@class='MyTable WideTable']//tr[last()]");

    
    // Elements
    public WebElement getTxtDepartDate() {
        return Constant.WEBDRIVER.findElement(txtDepartDate);
    }
    
    public WebElement getTxtDepartFrom() {
        return Constant.WEBDRIVER.findElement(txtDepartFrom);
    }

    public WebElement getTxtArriveAt() {
        return Constant.WEBDRIVER.findElement(txtArriveAt);
    }

    public WebElement getTxtSeatType() {
        return Constant.WEBDRIVER.findElement(txtSeatType);
    }

    public WebElement getTxtTicketAmount() {
        return Constant.WEBDRIVER.findElement(txtTicketAmount);
    }
    
    public WebElement getBtnBookTicket() {
        return Constant.WEBDRIVER.findElement(btnBookTicket);
    }
    
    public WebElement getMsgBookSuccess() {
        return Constant.WEBDRIVER.findElement(msgBookSuccess);
    }
    
    public WebElement getTrLastTicket() {
        return Constant.WEBDRIVER.findElement(trLastTicket);
    }
    
    // Methods
    public BookTicketPage bookTicket(BookTicket bt) { 
    	Select dropdownDepartDate = new Select(getTxtDepartDate());
    	dropdownDepartDate.selectByVisibleText(bt.getDepartDate());;
    	
    	Select dropdownDepartFrom = new Select(getTxtDepartFrom());
    	dropdownDepartFrom.selectByVisibleText(bt.getDepartFrom().toString());
    	
    	Utilities.waitUntilStale(getTxtArriveAt());
    	Select dropdownArriveAt = new Select(getTxtArriveAt());
    	dropdownArriveAt.selectByVisibleText(bt.getArriveAt().toString());

    	Select dropdownSeatType = new Select(getTxtSeatType());
    	dropdownSeatType.selectByVisibleText(bt.getSeatType().toString());
    	
    	Select dropdownTicketAmount = new Select(getTxtTicketAmount());
    	dropdownTicketAmount.selectByVisibleText(bt.getTicketAmount());
		
		Utilities.scrollToElement(btnBookTicket);
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
			Utilities.scrollToElement(btnBookTicket);
			this.getBtnBookTicket().click();
		
		return this;
    }
    
    public BookTicket getActualBookTicket() {
    	BookTicket bt = new BookTicket();
        WebElement lastRow = getTrLastTicket();
        
        // Lấy tất cả các ô trong dòng
        List<WebElement> cells = lastRow.findElements(By.tagName("td"));
        
        if (cells.size() >= 8) {
            // Gán giá trị từ bảng vào đối tượng BookTicket
            bt.setDepartFrom(Province.fromVietnameseName(cells.get(0).getText().trim()));
            bt.setArriveAt(Province.fromVietnameseName(cells.get(1).getText().trim()));
            bt.setSeatType(SeatType.fromDisplayName(cells.get(2).getText().trim()));
            bt.setDepartDate(cells.get(3).getText().trim());
            bt.setTicketAmount(cells.get(6).getText().trim());
        }
        return bt;
    }
    
    public String getSelectedDepartFrom() {
        Select select = new Select(Constant.WEBDRIVER.findElement(txtDepartFrom));
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedArriveAt() {
        Select select = new Select(Constant.WEBDRIVER.findElement(txtArriveAt));
        return select.getFirstSelectedOption().getText();
    }
}

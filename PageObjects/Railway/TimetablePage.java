package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Constant.Province;

public class TimetablePage extends GeneralPage{
    
	// Locator
    private final String timetableRowTemplate = "//table[@class='MyTable WideTable']//tr[td[2][normalize-space()='%s'] and td[3][normalize-space()='%s']]";

    private final String checkPriceLinkTemplate = timetableRowTemplate + "//a[contains(@href, 'TicketPrice')]";
    private final String bookTicketLinkTemplate = timetableRowTemplate + "//a[contains(@href, 'BookTicket')]";  
    
    private By getCheckPriceLinkLocator(Province from, Province to) {
        String fromName = from.getName();
        String toName = to.getName();
        String xpath = String.format(checkPriceLinkTemplate, fromName, toName);
        return By.xpath(xpath);
    }
    
    private By getBookTicketLinkLocator(Province from, Province to) {
        String fromName = from.getName();
        String toName = to.getName();
        String xpath = String.format(bookTicketLinkTemplate, fromName, toName);
        return By.xpath(xpath);
    }
    
    private By getTimetableRowLocator(Province from, Province to) {
        String fromName = from.getName();
        String toName = to.getName();
        String xpath = String.format(timetableRowTemplate, fromName, toName);
        return By.xpath(xpath);
    }
    
    // Elements
    public WebElement getLinkCheckPrice(Province from, Province to) {
        return Constant.WEBDRIVER.findElement(getCheckPriceLinkLocator(from, to));
    }
    
    public WebElement getLinkBookTicket(Province from, Province to) {
        return Constant.WEBDRIVER.findElement(getBookTicketLinkLocator(from, to));
    }
    
    public WebElement getTimetableRow(Province from, Province to) {
        return Constant.WEBDRIVER.findElement(getTimetableRowLocator(from, to));
    }
    
    // Methods
    public TicketPricePage clickCheckPriceForRoute(Province from, Province to) {
        By locator = getCheckPriceLinkLocator(from, to);
        Utilities.scrollToElement(locator);
        Constant.WEBDRIVER.findElement(locator).click();
        return new TicketPricePage();
    }
    
    public BookTicketPage clickBookTicketForRoute(Province from, Province to) {
        By locator = getBookTicketLinkLocator(from, to);
        Utilities.scrollToElement(locator);
        Constant.WEBDRIVER.findElement(locator).click();
        return new BookTicketPage();
    }
}

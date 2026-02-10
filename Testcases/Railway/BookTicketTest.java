package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import DataObject.Account;
import DataObject.BookTicket;
import DataObject.TicketPrice;

public class BookTicketTest extends TestBase {
	
	@Test
    public void TC12_UserCanBookOneTicketAtATime() {
		System.out.println("TC12 - User can book 1 ticket at a time");
	    final Account testAccount = new Account(Constant.NEWUSER_MAIL, Constant.PASSWORD, Constant.PID);
	    final String twoDayFromeDepartDay = Utilities.convertDateToStringFromToday(2 + Constant.DEPART_DAY);
        final BookTicket expectedBookTicket = new BookTicket(twoDayFromeDepartDay,"Nha Trang","Huế","Soft bed with air conditioner","1");
        final String expectedSuccessMsg = "Ticket booked successfully!";
        
        System.out.println("PRE-CONDITION: Login with activated account");
        homePage.createActiveAccount(homePage, testAccount);
        
        System.out.println("1. Navigate to QA Railway Login page");
	    LoginPage loginPage = homePage.gotoLoginPage();
	    
	    System.out.println("2. Login with a valid account");
	    homePage = loginPage.login(testAccount);
        
        System.out.println("3. Click on 'Book ticket' tab");
        BookTicketPage bookTicketPage = homePage.gotoBookTicketPage();
        
        System.out.println("4. Select the next 2 days from 'Depart date'");        
        System.out.println("5. Select Depart from 'Nha Trang' and Arrive at 'Huế'");
        System.out.println("6. Select 'Soft bed with air conditioner' for 'Seat type'");
        System.out.println("7. Select '1' for 'Ticket amount'");
        System.out.println("8. Click on 'Book ticket' button");
        bookTicketPage = bookTicketPage.bookTicket(expectedBookTicket);      
                        
        System.out.println("Verify message 'Ticket booked successfully!' displays");
        String successMessage = bookTicketPage.getMsgBookSuccess().getText();
        
        Assert.assertTrue(successMessage.contains(expectedSuccessMsg),
            "Success message not displayed. Actual: " + successMessage);
        
        System.out.println("Verify ticket information displays correctly:");
        BookTicket actualbookTicket = new BookTicket();
        actualbookTicket = bookTicketPage.getActualBookTicket(actualbookTicket);
        Assert.assertEquals(actualbookTicket.getText(), expectedBookTicket.getText(),"Book Ticket not match");
        
        System.out.println("TC12 PASSED - User can book 1 ticket successfully");
    }
	    
    @Test
    public void TC13_UserCanBookManyTicketsAtATime() {
    	System.out.println("TC13 - User can book many tickets at a time");
        final Account testAccount = new Account(Constant.NEWUSER_MAIL, Constant.PASSWORD, Constant.PID);
        final BookTicket expectedBookTicket = new BookTicket(Utilities.convertDateToStringFromToday(25), "Nha Trang", "Sài Gòn", "Soft seat with air conditioner", "5");
        final String expectedSuccessMsg = "Ticket booked successfully!";
        
        System.out.println("PRE-CONDITION: Login with activated account");
        homePage.createActiveAccount(homePage, testAccount);
        
        System.out.println("1. Navigate to QA Railway Login page");
        LoginPage loginPage = homePage.gotoLoginPage();
        
        System.out.println("2. Login with a valid account");
        homePage = loginPage.login(testAccount);
        
        System.out.println("3. Click on 'Book ticket' tab");
        BookTicketPage bookTicketPage = homePage.gotoBookTicketPage();
        
        System.out.println("4. Select the next 25 days from 'Depart date'");
        System.out.println("5. Select Depart from 'Nha Trang' and Arrive at 'Sài Gòn'");
        System.out.println("6. Select 'Soft seat with air conditioner' for 'Seat type'");
        System.out.println("7. Select '5' for 'Ticket amount'");
        System.out.println("8. Click on 'Book ticket' button");
        bookTicketPage.bookTicket(expectedBookTicket);
                
        System.out.println("Verify message 'Ticket booked successfully!' displays");
        String successMessage = bookTicketPage.getMsgBookSuccess().getText();
        
        Assert.assertTrue(successMessage.contains(expectedSuccessMsg),
            "Success message not displayed. Actual: " + successMessage);
        
        System.out.println("Verify ticket information displays correctly:");
        
        BookTicket actualBookTicket = new BookTicket();
        actualBookTicket = bookTicketPage.getActualBookTicket(actualBookTicket);
        
        Assert.assertEquals(actualBookTicket.getText(), expectedBookTicket.getText(), "Book Ticket not match");
        
        System.out.println("TC13 PASSED - User can book many tickets at a time");
    }
    
    @Test
    public void TC14_UserCanCheckPriceOfTicketFromTimetable() {
    	System.out.println("TC14 - User can check price of ticket from Timetable");
        final Account testAccount = new Account(Constant.NEWUSER_MAIL, Constant.PASSWORD, Constant.PID);
        final TicketPrice expectedTicketPrice = new TicketPrice("310000","335000","360000","410000","460000","510000");
        final String expectedRoute = "Ticket price from Đà Nẵng to Sài Gòn"; 
        
        System.out.println("PRE-CONDITION: Login with activated account");
        homePage.createActiveAccount(homePage, testAccount);
        
        System.out.println("1. Navigate to QA Railway Login page");
        LoginPage loginPage = homePage.gotoLoginPage();
        
        System.out.println("2. Login with a valid account");
        homePage = loginPage.login(testAccount);
        
        System.out.println("3. Click on 'Timetable' tab");
        TimetablePage timetablePage = homePage.gotoTimetablePage();
        
        System.out.println("4. Click on 'check price' link of the route from 'Đà Nẵng' to 'Sài Gòn'");
        TicketPricePage ticketPricePage = timetablePage.clickCheckPriceForRoute();
                
        System.out.println("Verify 'Ticket Price' page is loaded");
        boolean isTicketPricePageDisplayed = ticketPricePage.getSelectedTicketPrice().isDisplayed();
	    Assert.assertTrue(isTicketPricePageDisplayed, "Ticket price page is not displayed.");
	    
	    System.out.println("Verify Ticket table shows 'Ticket price from Đà Nẵng to Sài Gòn'.");
        String displayedRoute = ticketPricePage.getThHeader().getText();        
        Assert.assertEquals(displayedRoute, expectedRoute, "Route not match");
        
        System.out.println("Verify Price for each seat displays correctly");
        TicketPrice actualTicketPrice = new TicketPrice();
        actualTicketPrice = ticketPricePage.getTxtTicketTableCell(actualTicketPrice);
        Assert.assertEquals(actualTicketPrice.getText(), expectedTicketPrice.getText(), "Ticket prices do not match");
        
        System.out.println("TC14 PASSED - User can check price of ticket from Timetable");
    }
    
    @Test
    public void TC15_UserCanBookTicketFromTimetable() {
    	System.out.println("TC15 - User can book ticket from Timetable");
        final Account testAccount = new Account(Constant.NEWUSER_MAIL, Constant.PASSWORD, Constant.PID);
        final BookTicket expectedBookTicket = new BookTicket(Utilities.convertDateToStringFromToday(4), "Quảng Ngãi", "Huế", "", "5");
        final String expectedSuccessMsg = "Ticket booked successfully!";
        
        System.out.println("PRE-CONDITION: Login with activated account");
        homePage.createActiveAccount(homePage, testAccount);
        
        System.out.println("1. Navigate to QA Railway Login page");
        LoginPage loginPage = homePage.gotoLoginPage();
        
        System.out.println("2. Login with a valid account");
        homePage = loginPage.login(testAccount);
        
        System.out.println("3. Click on 'Timetable' tab");
        TimetablePage timetablePage = homePage.gotoTimetablePage();
        
        System.out.println("4. Click on book ticket of route 'Quảng Ngãi' to 'Huế'");
        BookTicketPage bookTicketPage = timetablePage.clickBookTicketForRoute();
        
        System.out.println("5. Select Depart date = tomorrow");
        System.out.println("6. Select Ticket amount = 5");
        System.out.println("7. Click on 'Book ticket' button");
        bookTicketPage = bookTicketPage.bookTicketApart(expectedBookTicket.getDepartDate(), "", "", "", expectedBookTicket.getTicketAmount());
        
        System.out.println("Verify message 'Ticket booked successfully!' displays");
        String successMessage = bookTicketPage.getMsgBookSuccess().getText();
        
        Assert.assertTrue(successMessage.contains(expectedSuccessMsg),
            "Success message not displayed. Actual: " + successMessage);
        
        System.out.println("Verify ticket information displays correctly:");
        
        BookTicket actualBookTicket = new BookTicket();
        actualBookTicket = bookTicketPage.getActualBookTicket(actualBookTicket);
        
        expectedBookTicket.setSeatType(Constant.DEFAULT_SEATTYPE);
        Assert.assertEquals(actualBookTicket.getText(), expectedBookTicket.getText(), "Book Ticket not match");
        
        System.out.println("TC15 PASSED - User can book ticket from Timetable");
    }
    
    @Test
    public void TC16_UserCanCancelATicket() {
    	System.out.println("TC16 - User can cancel a ticket");
        final Account testAccount = new Account(Constant.NEWUSER_MAIL, Constant.PASSWORD, Constant.PID);
        final BookTicket bt = new BookTicket(
            Utilities.convertDateToStringFromToday(2 + Constant.DEPART_DAY),
            "Nha Trang", 
            "Huế", 
            "Soft bed with air conditioner", 
            "1"
        );

        System.out.println("PRE-CONDITION: Login with activated account");
        homePage.createActiveAccount(homePage, testAccount);
        
        System.out.println("1. Navigate to QA Railway Login page");
        LoginPage loginPage = homePage.gotoLoginPage();
        
        System.out.println("2. Login with a valid account");
        homePage = loginPage.login(testAccount);
        
        System.out.println("3. Book a ticket");
        BookTicketPage bookTicketPage = homePage.gotoBookTicketPage();
        bookTicketPage = bookTicketPage.bookTicket(bt);
        
        System.out.println("4. Click on 'My ticket' tab");
        MyTicketPage myTicketPage = homePage.gotoMyTicketPage();
        
        System.out.println("5. Click on 'Cancel' button of ticket which user want to cancel");
        myTicketPage = myTicketPage.clickCancelTicketByDetails(bt);
                
        System.out.println("6. Click on 'OK' button on Confirmation message 'Are you sure?'");
        Constant.WEBDRIVER.switchTo().alert().accept();
        
        System.out.println("Verify ticket is cancelled and removed from the list");
        boolean ticketStillExists = myTicketPage.isTicketStillPresent(bt);
        
        Assert.assertFalse(ticketStillExists, "Cancelled ticket should no longer be in the list");
        
        System.out.println("TC16 PASSED - User can cancel a ticket successfully");
    }
}

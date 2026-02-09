package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Common.Utilities;
import Constant.Constant;
import DataObject.Account;
import DataObject.BookTicket;

public class BookTicketTest extends TestBase {
	@Test
    public void TC12_UserCanBookOneTicketAtATime() {
	    final Account testAccount = new Account(Constant.NEWUSER_MAIL, Constant.PASSWORD, Constant.PID);
	    final String twoDayFromeDepartDay = Utilities.convertDateToStringFromToday(2 + Constant.DEPART_DAY);
        final BookTicket bt = new BookTicket(twoDayFromeDepartDay,"Nha Trang","Huế","Soft bed with air conditioner","1");
        
        System.out.println("TC12 - User can book 1 ticket at a time");
        String expectedSuccessMsg = "Ticket booked successfully!";
        
        System.out.println("=== PRE-CONDITION: Login with activated account ===");
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
        bookTicketPage = bookTicketPage.bookTicket(bt);      
                
        System.out.println("\n=== VERIFICATION ===");
        SoftAssert softAssert = new SoftAssert();
        
        System.out.println("Verify message 'Ticket booked successfully!' displays");
        String successMessage = bookTicketPage.getMsgBookSuccess().getText();
        
        softAssert.assertTrue(successMessage.contains(expectedSuccessMsg),
            "Success message not displayed. Actual: " + successMessage);
        
        System.out.println("\nVerify ticket information displays correctly:");
        
        BookTicket btActual = new BookTicket();
        btActual = bookTicketPage.getActualBookTicket(btActual);
        
        System.out.println("   Expected Depart Date: " + bt.getDepartDate());
        System.out.println("   Actual Depart Date: " + btActual.getDepartDate());
        softAssert.assertEquals(btActual.getDepartDate(),bt.getDepartDate(),
            "Depart date not match. Expected: " + bt.getDepartDate() + ", Actual: " + bt.getDepartDate());
        
        System.out.println("   Expected Depart Station: " + bt.getDepartFrom());
        System.out.println("   Actual Depart Station: " + btActual.getDepartFrom());
        softAssert.assertEquals(btActual.getDepartFrom(), bt.getDepartFrom(),
            "Depart station not match");
        
        System.out.println("   Expected Arrive Station: " + bt.getArriveAt());
        System.out.println("   Actual Arrive Station: " + btActual.getArriveAt());
        softAssert.assertEquals(btActual.getArriveAt(), bt.getArriveAt(),
            "Arrive station not match");
        
        System.out.println("   Expected Seat Type: " + bt.getSeatType());
        System.out.println("   Actual Seat Type: " + btActual.getSeatType());
        softAssert.assertEquals(btActual.getSeatType(),  bt.getSeatType(),
            "Seat type not match");
        
        System.out.println("   Expected Amount: " + bt.getTicketAmount());
        System.out.println("   Actual Amount: " + btActual.getTicketAmount());
        softAssert.assertEquals(btActual.getTicketAmount(), bt.getTicketAmount(),
            "Ticket amount not match");
        
        softAssert.assertAll();
        System.out.println("\n✅ TC12 PASSED - User can book 1 ticket successfully");
    }
	    
    @Test
    public void TC13_UserCanBookManyTicketsAtATime() {
        final Account testAccount = new Account(Constant.NEWUSER_MAIL, Constant.PASSWORD, Constant.PID);
        final BookTicket bt = new BookTicket(Utilities.convertDateToStringFromToday(25), "Nha Trang", "Sài Gòn", "Soft seat with air conditioner", "5");
        
        System.out.println("TC13 - User can book many tickets at a time");
        String expectedSuccessMsg = "Ticket booked successfully!";
        
        System.out.println("=== PRE-CONDITION: Login with activated account ===");
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
        bookTicketPage.bookTicket(bt);
        
        System.out.println("\n=== VERIFICATION ===");
        SoftAssert softAssert = new SoftAssert();
        
        System.out.println("Verify message 'Ticket booked successfully!' displays");
        String successMessage = bookTicketPage.getMsgBookSuccess().getText();
        
        softAssert.assertTrue(successMessage.contains(expectedSuccessMsg),
            "Success message not displayed. Actual: " + successMessage);
        
        System.out.println("\nVerify ticket information displays correctly:");
        
        BookTicket btActual = new BookTicket();
        btActual = bookTicketPage.getActualBookTicket(btActual);
        
        System.out.println("   Expected Depart Date: " + bt.getDepartDate());
        System.out.println("   Actual Depart Date: " + btActual.getDepartDate());
        softAssert.assertEquals(btActual.getDepartDate(), bt.getDepartDate(),
            "Depart date not match. Expected: " + bt.getDepartDate() + ", Actual: " + btActual.getDepartDate());
        
        System.out.println("   Expected Depart Station: " + bt.getDepartFrom());
        System.out.println("   Actual Depart Station: " + btActual.getDepartFrom());
        softAssert.assertEquals(btActual.getDepartFrom(), bt.getDepartFrom(),
            "Depart station not match");
        
        System.out.println("   Expected Arrive Station: " + bt.getArriveAt());
        System.out.println("   Actual Arrive Station: " + btActual.getArriveAt());
        softAssert.assertEquals(btActual.getArriveAt(), bt.getArriveAt(),
            "Arrive station not match");
        
        System.out.println("   Expected Seat Type: " + bt.getSeatType());
        System.out.println("   Actual Seat Type: " + btActual.getSeatType());
        softAssert.assertEquals(btActual.getSeatType(), bt.getSeatType(),
            "Seat type not match");
        
        System.out.println("   Expected Amount: " + bt.getTicketAmount());
        System.out.println("   Actual Amount: " + btActual.getTicketAmount());
        softAssert.assertEquals(btActual.getTicketAmount(), bt.getTicketAmount(),
            "Ticket amount not match");
        
        softAssert.assertAll();
        System.out.println("\n✅ TC13 PASSED - User can book many tickets at a time");
    }
    
    @Test
    public void TC14_UserCanCheckPriceOfTicketFromTimetable() {
        final Account testAccount = new Account(Constant.NEWUSER_MAIL, Constant.PASSWORD, Constant.PID);
        
        System.out.println("TC14 - User can check price of ticket from Timetable");
        String expectedRoute = "Ticket price from Đà Nẵng to Sài Gòn";
        
        System.out.println("=== PRE-CONDITION: Login with activated account ===");
        homePage.createActiveAccount(homePage, testAccount);
        
        System.out.println("1. Navigate to QA Railway Login page");
        LoginPage loginPage = homePage.gotoLoginPage();
        
        System.out.println("2. Login with a valid account");
        homePage = loginPage.login(testAccount);
        
        System.out.println("3. Click on 'Timetable' tab");
        TimetablePage timetablePage = homePage.gotoTimetablePage();
        
        System.out.println("4. Click on 'check price' link of the route from 'Đà Nẵng' to 'Sài Gòn'");
        TicketPricePage ticketPricePage = timetablePage.clickCheckPriceForRoute();
        
        System.out.println("\n=== VERIFICATION ===");
        SoftAssert softAssert = new SoftAssert();
        
        System.out.println("Verify 'Ticket Price' page is loaded");
        boolean isTicketPricePageDisplayed = ticketPricePage.getSelectedTicketPrice().isDisplayed();
	    Assert.assertTrue(isTicketPricePageDisplayed, 
	        "Ticket price page is not displayed.");
	    
	    System.out.println("Verify Ticket table shows 'Ticket price from Đà Nẵng to Sài Gòn'.");
        String displayedRoute = ticketPricePage.getThHeader().getText();
        System.out.println("   Displayed Route: " + displayedRoute);
        
        softAssert.assertEquals(displayedRoute, expectedRoute,
                "Route not match");
        
        System.out.println("Verify Price for each seat displays correctly");
        String HSPrice = "310000"; 
        String SSPrice = "335000";
        String SSCPrice = "360000";
        String HBPrice = "410000";
        String SBPrice = "460000";
        String SBCPrice = "510000";
        softAssert.assertEquals(ticketPricePage.getTxtTicketTableCell(0), HSPrice,"Price not match");
        softAssert.assertEquals(ticketPricePage.getTxtTicketTableCell(1), SSPrice,"Price not match");
        softAssert.assertEquals(ticketPricePage.getTxtTicketTableCell(2), SSCPrice,"Price not match");
        softAssert.assertEquals(ticketPricePage.getTxtTicketTableCell(3), HBPrice,"Price not match");
        softAssert.assertEquals(ticketPricePage.getTxtTicketTableCell(4), SBPrice,"Price not match");
        softAssert.assertEquals(ticketPricePage.getTxtTicketTableCell(5), SBCPrice,"Price not match");
        
        softAssert.assertAll();
        System.out.println("\n✅ TC14 PASSED - User can check price of ticket from Timetable");
    }
    
    @Test
    public void TC15_UserCanBookTicketFromTimetable() {
        final Account testAccount = new Account(Constant.NEWUSER_MAIL, Constant.PASSWORD, Constant.PID);
        final BookTicket bt = new BookTicket(Utilities.convertDateToStringFromToday(4), "Quảng Ngãi", "Huế", "", "5");
        
        System.out.println("TC15 - User can book ticket from Timetable");
        String expectedSuccessMsg = "Ticket booked successfully!";
        
        System.out.println("=== PRE-CONDITION: Login with activated account ===");
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
        bookTicketPage = bookTicketPage.bookTicketApart(bt.getDepartDate(), "", "", "", bt.getTicketAmount());
        
        System.out.println("\n=== VERIFICATION ===");
        SoftAssert softAssert = new SoftAssert();
        
        System.out.println("Verify message 'Ticket booked successfully!' displays");
        String successMessage = bookTicketPage.getMsgBookSuccess().getText();
        
        softAssert.assertTrue(successMessage.contains(expectedSuccessMsg),
            "Success message not displayed. Actual: " + successMessage);
        
        System.out.println("\nVerify ticket information displays correctly:");
        
        BookTicket btActual = new BookTicket();
        btActual = bookTicketPage.getActualBookTicket(btActual);
        
        System.out.println("   Expected Depart Date (tomorrow): " + bt.getDepartDate());
        System.out.println("   Actual Depart Date: " + btActual.getDepartDate());
        softAssert.assertEquals(btActual.getDepartDate(), bt.getDepartDate(),
            "Depart date not match. Expected (tomorrow): " + bt.getDepartDate() + ", Actual: " + btActual.getDepartDate());
        
        System.out.println("   Expected Depart Station: " + bt.getDepartFrom());
        System.out.println("   Actual Depart Station: " + btActual.getDepartFrom());
        softAssert.assertEquals(btActual.getDepartFrom(), bt.getDepartFrom(),
            "Depart station not match");
        
        System.out.println("   Expected Arrive Station: " + bt.getArriveAt());
        System.out.println("   Actual Arrive Station: " + btActual.getArriveAt());
        softAssert.assertEquals(btActual.getArriveAt(), bt.getArriveAt(),
            "Arrive station not match");
        
        System.out.println("   Expected Amount: " + bt.getTicketAmount());
        System.out.println("   Actual Amount: " + btActual.getTicketAmount());
        softAssert.assertEquals(btActual.getTicketAmount(), bt.getTicketAmount(),
            "Ticket amount not match");
        
        System.out.println("   Seat Type: " + btActual.getSeatType());
        softAssert.assertNotNull(btActual.getSeatType(), "Seat type should not be null");
        softAssert.assertFalse(btActual.getSeatType().isEmpty(), "Seat type should not be empty");
        
        softAssert.assertAll();
        System.out.println("\n✅ TC15 PASSED - User can book ticket from Timetable");
    }
    
    
}

package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Constant.Constant;
import DataObject.Account;
import Guerrillamail.MailFake;

public class GeneralPage {

    // Locators
    private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By tabFAQ = By.xpath("//div[@id='menu']//a[@href='/Page/FAQ.cshtml']");
    private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
    private final By tabBookTicket = By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
    private final By tabTimetable = By.xpath("//div[@id='menu']//a[@href='TrainTimeListPage.cshtml']");
    private final By tabTicketPrice = By.xpath("//div[@id='menu']//a[@href='/Page/TrainPriceListPage.cshtml']");
    private final By tabMyTicket = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");
    
    protected WebElement getTabLogin() {
        return Constant.WEBDRIVER.findElement(tabLogin);
    }

    protected WebElement getTabLogout() {
        return Constant.WEBDRIVER.findElement(tabLogout);
    }
    
    protected WebElement getTabFAQ() {
        return Constant.WEBDRIVER.findElement(tabFAQ);
    }
    
    protected WebElement getTabRegister() {
        return Constant.WEBDRIVER.findElement(tabRegister);
    }

    protected WebElement getLblWelcomeMessage() {
        return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
    }
    
    protected WebElement getTabBookTicket() {
        return Constant.WEBDRIVER.findElement(tabBookTicket);
    }
    
    protected WebElement getTabTimetable() {
        return Constant.WEBDRIVER.findElement(tabTimetable);
    }
    
    protected WebElement getTabTicketPrice() {
        return Constant.WEBDRIVER.findElement(tabTicketPrice);
    }
    
    protected WebElement getTabMyTicket() {
        return Constant.WEBDRIVER.findElement(tabMyTicket);
    }

    // Methods
    public String getWelcomeMessage() {
        return this.getLblWelcomeMessage().getText();
    }

    public LoginPage gotoLoginPage() {
        this.getTabLogin().click();
        return new LoginPage();
    }
    
    public HomePage logoutPage() {
        this.getTabLogout().click();
        return new HomePage();
    }
    
    public FAQPage gotoFAQPage() {
        this.getTabFAQ().click();
        return new FAQPage();
    }
    
    public RegisterPage gotoRegisterPage() {
        this.getTabRegister().click();
        return new RegisterPage();
    }
    
    public BookTicketPage gotoBookTicketPage() {
        this.getTabBookTicket().click();
        return new BookTicketPage();
    }
    
    public TimetablePage gotoTimetablePage() {
        this.getTabTimetable().click();
        return new TimetablePage();
    }
    
    public TicketPricePage gotoTicketPricePage() {
        this.getTabTicketPrice().click();
        return new TicketPricePage();
    }
    
    public MyTicketPage gotoMyTicketPage() {
        this.getTabMyTicket().click();
        return new MyTicketPage();
    }
    
    // From TC07
	public void createActiveAccount(HomePage homePage, Account newAccount) {
		homePage.open();
		RegisterPage registerPage = homePage.clickRegisterLink();
		registerPage.enterRegistrationInfo(newAccount);
		MailFake mailFake = new MailFake();
        mailFake.openMailFake();
        mailFake.activeNewMail(newAccount.getEmail());
	}
}
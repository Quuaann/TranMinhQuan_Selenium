package Constant;

public enum TabMenu {
    HOME("Home", "HomePage", "/"),
    BOOK_TICKET("Book ticket", "BookTicketPage", "/Page/BookTicketPage.cshtml"),
    MY_TICKET("My ticket", "MyTicketPage", "/Page/ManageTicket.cshtml"),
    TIMETABLE("Timetable", "TimetablePage", "/TrainTimeListPage.cshtml"),
    TICKET_PRICE("Ticket price", "TicketPricePage", "/Page/TrainPriceListPage.cshtml"),
    FAQ("FAQ", "FAQPage", "/Page/FAQ.cshtml"),
    LOGIN("Login", "LoginPage", "/Account/Login.cshtml"),
    REGISTER("Register", "RegisterPage", "/Account/Register.cshtml"),
    RESET_PASSWORD("Reset password", "ResetPasswordPage", "/Account/ResetPassword.cshtml"),
    LOGOUT("Log out", "LogoutPage", "/Account/Logout"); 
    
    private final String tabName;
    private final String pageName;
    private final String urlPath;
    
    // Constructor
    TabMenu(String tabName, String pageName, String urlPath) {
        this.tabName = tabName;
        this.pageName = pageName;
        this.urlPath = urlPath;
    }
    
    // Getters
    public String getTabName() {
        return tabName;
    }
    
    public String getPageName() {
        return pageName;
    }
    
    public String getUrlPath() {
        return urlPath;
    }
    
    // Tìm TabMenu theo tab name
    public static TabMenu fromTabName(String tabName) {
        if (tabName == null || tabName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tab name cannot be null or empty");
        }
        
        String trimmedName = tabName.trim();
        for (TabMenu tab : values()) {
            if (tab.getTabName().equalsIgnoreCase(trimmedName)) {
                return tab;
            }
        }
        throw new IllegalArgumentException("Unknown tab menu: " + tabName);
    }
    
    // Tìm TabMenu theo page name
    public static TabMenu fromPageName(String pageName) {
        if (pageName == null || pageName.trim().isEmpty()) {
            throw new IllegalArgumentException("Page name cannot be null or empty");
        }
        
        String trimmedName = pageName.trim();
        for (TabMenu tab : values()) {
            if (tab.getPageName().equalsIgnoreCase(trimmedName)) {
                return tab;
            }
        }
        throw new IllegalArgumentException("Unknown page: " + pageName);
    }
    
    // Tìm TabMenu theo URL path
    public static TabMenu fromUrlPath(String urlPath) {
        if (urlPath == null || urlPath.trim().isEmpty()) {
            throw new IllegalArgumentException("URL path cannot be null or empty");
        }
        
        String trimmedPath = urlPath.trim();
        for (TabMenu tab : values()) {
            if (tab.getUrlPath().equals(trimmedPath)) {
                return tab;
            }
        }
        throw new IllegalArgumentException("Unknown URL path: " + urlPath);
    }
    
    @Override
    public String toString() {
        return tabName;
    }
}
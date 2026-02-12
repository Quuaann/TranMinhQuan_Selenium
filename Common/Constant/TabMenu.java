package Constant;

public enum TabMenu {
    HOME("Home","/"),
    BOOK_TICKET("Book ticket", "/Page/BookTicketPage.cshtml"),
    MY_TICKET("My ticket", "/Page/ManageTicket.cshtml"),
    TIMETABLE("Timetable", "/TrainTimeListPage.cshtml"),
    TICKET_PRICE("Ticket price", "/Page/TrainPriceListPage.cshtml"),
    FAQ("FAQ", "/Page/FAQ.cshtml"),
    LOGIN("Login", "/Account/Login.cshtml"),
    REGISTER("Register", "/Account/Register.cshtml"),
    RESET_PASSWORD("Reset password", "/Account/ResetPassword.cshtml"),
    LOGOUT("Log out", "/Account/Logout"); 
    
    private final String tabName;
    private final String urlPath;
    
    // Constructor
    TabMenu(String tabName, String urlPath) {
        this.tabName = tabName;
        this.urlPath = urlPath;
    }
    
    // Getters
    public String getTabName() {
        return tabName;
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
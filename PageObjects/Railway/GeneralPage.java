package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Constant.Constant;
import Constant.TabMenu;

public class GeneralPage {

    // Locators
    private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
    
    protected String getTabXpath(TabMenu tabMenu) {
        String tabName = tabMenu.getTabName();
        return String.format(dynamicTabXpathTemplate, tabName);
        
    }
    
    private final String dynamicTabXpathTemplate = "//div[@id='menu']//a[normalize-space()='%s']";
    
    // Elements
    protected WebElement getTab(TabMenu tabMenu) {
        String tabName = tabMenu.getTabName();
        By locator = By.xpath(String.format(dynamicTabXpathTemplate, tabName));
        return Constant.WEBDRIVER.findElement(locator);
    }

    protected WebElement getLblWelcomeMessage() {
        return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
    }

    // Methods
    public String getWelcomeMessage() {
        return this.getLblWelcomeMessage().getText();
    }

    public <T> T gotoPage(TabMenu tabMenu, Class<T> pageClass) {
        try {
            getTab(tabMenu).click();
            return pageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate to page: " + tabMenu.getTabName(), e);
        }
    }
}
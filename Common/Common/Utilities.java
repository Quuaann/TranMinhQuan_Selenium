package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import Constant.Constant;

public class Utilities {
	
	public static WebElement waitForClickable(By locator) {
        return waitForClickable(locator, Constant.TIMEOUT);
    }
    
	public static WebElement waitForClickable(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public static WebElement waitForVisible(By locator) {
        return waitForVisible(locator, Constant.TIMEOUT);
    }
    
    public static WebElement waitForVisible(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be present trong DOM với timeout mặc định
     * @param locator Locator của element
     * @return WebElement đã present
     */
    public static WebElement waitForPresent(By locator) {
        return waitForPresent(locator, Constant.TIMEOUT);
    }
    
    /**
     * Wait for element to be present trong DOM với timeout tùy chỉnh
     * @param locator Locator của element
     * @param timeout Timeout tính bằng giây
     * @return WebElement đã present
     */
    public static WebElement waitForPresent(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Wait until element becomes stale (thường dùng khi element bị remove khỏi DOM)
     * @param element Element cần chờ trở nên stale
     */
    public static void waitUntilStale(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.stalenessOf(element));
        } catch (Exception e) {
            System.out.println("Element did not become stale: " + e.getMessage());
        }
    }
    
    /**
     * Wait for element to disappear với timeout mặc định
     * @param locator Locator của element
     */
    public static void waitForInvisible(By locator) {
        waitForInvisible(locator, Constant.TIMEOUT);
    }
    
    /**
     * Wait for element to disappear với timeout tùy chỉnh
     * @param locator Locator của element
     * @param timeout Timeout tính bằng giây
     */
    public static void waitForInvisible(By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println("Element is still visible: " + e.getMessage());
        }
    }
    
    /**
     * Wait for text to be present in element
     * @param locator Locator của element
     * @param text Text cần chờ xuất hiện
     */
    public static void waitForTextToBePresent(By locator, String text) {
        waitForTextToBePresent(locator, text, Constant.TIMEOUT);
    }
    
    /**
     * Wait for text to be present in element với timeout tùy chỉnh
     * @param locator Locator của element
     * @param text Text cần chờ xuất hiện
     * @param timeout Timeout tính bằng giây
     */
    public static void waitForTextToBePresent(By locator, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    
    /**
     * Wait for URL to contain specific text
     * @param text Text cần có trong URL
     */
    public static void waitForUrlToContain(String text) {
        waitForUrlToContain(text, Constant.TIMEOUT);
    }
    
    /**
     * Wait for URL to contain specific text với timeout tùy chỉnh
     * @param text Text cần có trong URL
     * @param timeout Timeout tính bằng giây
     */
    public static void waitForUrlToContain(String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.urlContains(text));
    }
    
    /**
     * Wait for page to load completely
     */
    public static void waitForPageLoad() {
        waitForPageLoad(Constant.TIMEOUT);
    }
    
    /**
     * Wait for page to load completely với timeout tùy chỉnh
     * @param timeout Timeout tính bằng giây
     */
    public static void waitForPageLoad(int timeout) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
        wait.until(driver -> {
            String state = (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return document.readyState");
            return state.equals("complete");
        });
    }
    
    /**
     * Wait với polling interval và timeout tùy chỉnh
     * @param condition Điều kiện cần chờ
     * @param timeout Timeout tính bằng giây
     * @param pollingInterval Polling interval tính bằng giây
     */
    public static void waitWithPolling(org.openqa.selenium.support.ui.ExpectedCondition<?> condition, 
                                      int timeout, int pollingInterval) {
        WebDriverWait wait = new WebDriverWait(
            Constant.WEBDRIVER, 
            Duration.ofSeconds(timeout),
            Duration.ofSeconds(pollingInterval)
        );
        wait.until(condition);
    }
    
    /**
     * Wait cho element có attribute cụ thể
     * @param locator Locator của element
     * @param attribute Tên attribute
     * @param value Giá trị attribute cần có
     */
    public static void waitForAttributeValue(By locator, String attribute, String value) {
        waitForAttributeValue(locator, attribute, value, Constant.TIMEOUT);
    }
    
    /**
     * Wait cho element có attribute cụ thể với timeout
     * @param locator Locator của element
     * @param attribute Tên attribute
     * @param value Giá trị attribute cần có
     * @param timeout Timeout tính bằng giây
     */
    public static void waitForAttributeValue(By locator, String attribute, String value, int timeout) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
        wait.until(driver -> {
            WebElement element = driver.findElement(locator);
            return value.equals(element.getAttribute(attribute));
        });
    }
}
package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import Constant.Constant;

public class Utilities {
	public String getFirstSelectedOptionText(By dropdownLocator) {
	    Select select = new Select(Constant.WEBDRIVER.findElement(dropdownLocator));
	    return select.getFirstSelectedOption().getText();
	}
	
	public static String convertDateToStringFromToday(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, day);
		Date tomorrow = calendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		return sdf.format(tomorrow);
	}
	
	public static void scrollToElement(WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
	    js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollToElement(By locator) {
	    WebElement element = Constant.WEBDRIVER.findElement(locator);
	    scrollToElement(element);
	}
	
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

    public static void waitUntilStale(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.stalenessOf(element));
        } catch (Exception e) {
            System.out.println("Element did not become stale: " + e.getMessage());
        }
    }
}
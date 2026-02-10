package Constant;

import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class Constant {
	
	public static WebDriver WEBDRIVER;
	public static final String RAILWAY_URL = "http://saferailway.somee.com/Page/HomePage.cshtml";
	public static final String USERNAME = "yyyyyyyyyy@sharklasers.com";
	public static final String PASSWORD = "987654321";
	public static final String WRONG_PASSWORD = RandomStringUtils.randomAlphabetic(10);
	public static final String INVALID_PASSWORD = "";
	public static final String NOT_ACTIVATED_USERNAME = "demo@gmail.com";
	public static final String PID = "12345678";
	
	public static String getNewUserMail() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        return String.format("%s@sharklasers.com", timestamp);
    }
	
	public static final Integer DEPART_DAY = 3;
	
	public static final Integer TIMEOUT = 30;
	
	public static final String DEFAULT_SEATTYPE = "Hard seat";
	
}


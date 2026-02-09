package Constant;

import org.openqa.selenium.WebDriver;

import org.apache.commons.lang3.RandomStringUtils;

public class Constant {
	
	public static WebDriver WEBDRIVER;
	public static final String RAILWAY_URL = "http://saferailway.somee.com/Page/HomePage.cshtml";
	public static final String USERNAME = "rjck024vipro@gmail.com";
	public static final String PASSWORD = "123456789";
	public static final String WRONG_PASSWORD = RandomStringUtils.randomAlphabetic(10);
	public static final String NOT_ACTIVATED_USERNAME = "demo@gmail.com";
	public static final String NOT_ACTIVATED_PASSWORD = "12345678";
	public static final String PID = "12345678";
	public static final String NEWUSER = RandomStringUtils.randomAlphabetic(10);
	public static final String NEWUSER_MAIL = NEWUSER + "@sharklasers.com";
	
	public static final Integer DEPART_DAY = 3;
	
	public static final Integer TIMEOUT = 20;
	

	
}


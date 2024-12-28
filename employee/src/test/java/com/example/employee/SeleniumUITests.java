package com.example.employee;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SeleniumUITests {
    
	   @Test
        public void testHomePage() {
        System.setProperty("webdriver.chrome.driver", "D:\\softwares\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8081");
        assertEquals("Employee Management", driver.getTitle());
        driver.quit();
    }
}

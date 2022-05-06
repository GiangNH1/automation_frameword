package com.nopcommerce.testCases;

import com.nopcommerce.commons.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Textarea extends BaseTest {
    WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriverManager(browserName, url);

    }

    @Test
    public void TC_01_Register_Success() {

        String textArea = driver.findElement(By.xpath("//div[@class='exBrowser']//textarea")).getText();
        System.out.println(textArea);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

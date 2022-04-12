package com.nopcommerce.pageObjects;

import com.nopcommerce.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class MyAccountPO extends BasePage {
    private WebDriver driver;

    public MyAccountPO(WebDriver driver){
        this.driver = driver;
    }
}

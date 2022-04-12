package com.nopcommerce.pageObjects;

import com.nopcommerce.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePO extends BasePage {
    private WebDriver driver;

    public HomePO(WebDriver driver){
        this.driver = driver;
    }
}

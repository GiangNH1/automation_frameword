package com.nopcommerce.pageObjects;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.AddressPageUI;
import com.nopcommerce.pageUIs.CustomerInfoPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class AddressPO extends BasePage {
    private WebDriver driver;

    public AddressPO(WebDriver driver){
        this.driver = driver;
    }

    @Step("get title page open")
    public String getTitlePage(){
        waitForElementVisible(driver, CustomerInfoPageUI.GET_TITLE_PAGE);
        return getElementText(driver, CustomerInfoPageUI.GET_TITLE_PAGE);
    }

    @Step("get value of Address at class {0}")
    public String getTextNewAddress(String className){
        waitForElementVisible(driver, AddressPageUI.GET_TEXT_NEW_ADDRESS, className);
        return getElementText(driver, AddressPageUI.GET_TEXT_NEW_ADDRESS, className);
    }
}

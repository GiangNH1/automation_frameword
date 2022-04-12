package com.nopcommerce.pageObjects;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.CustomerInfoPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class CustomerInfoPO extends BasePage {
    private WebDriver driver;

    public CustomerInfoPO(WebDriver driver){
        this.driver = driver;
    }

    @Step("get title page open")
    public String getTitlePage(){
        waitForElementVisible(driver, CustomerInfoPageUI.GET_TITLE_PAGE);
        return getElementText(driver, CustomerInfoPageUI.GET_TITLE_PAGE);
    }

    @Step("verify to 'Gender' check box")
    public boolean isCheckedToGender(String idRadio){
        waitForElementVisible(driver, CustomerInfoPageUI.RADIO_CHECKED, idRadio);
        return isElementSelected(driver, CustomerInfoPageUI.RADIO_CHECKED, idRadio);
    }
}

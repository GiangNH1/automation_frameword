package com.nopcommerce.pageObjects;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.commons.PageGeneratorManager;
import com.nopcommerce.pageUIs.ChangePasswordPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ChangePasswordPO extends BasePage {
    private WebDriver driver;

    public ChangePasswordPO(WebDriver driver){
        this.driver = driver;
    }

    @Step("get title page open")
    public String getTitlePage(){
        waitForElementVisible(driver, ChangePasswordPageUI.GET_TITLE_PAGE);
        return getElementText(driver, ChangePasswordPageUI.GET_TITLE_PAGE);
    }

    @Step("get value of change password")
    public String getTextChangePassword(){
        waitForElementVisible(driver, ChangePasswordPageUI.GET_TEXT_CHANGE_PASSWORD);
        return getElementText(driver, ChangePasswordPageUI.GET_TEXT_CHANGE_PASSWORD);
    }

    @Step("click to close message button")
    public void clickCloseMessage(){
        waitForElementClickable(driver, ChangePasswordPageUI.CLICK_CLOSE_MESSAGE);
        clickToElement(driver, ChangePasswordPageUI.CLICK_CLOSE_MESSAGE);
    }

}

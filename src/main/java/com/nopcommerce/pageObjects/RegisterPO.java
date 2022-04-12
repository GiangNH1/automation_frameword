package com.nopcommerce.pageObjects;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.RegisterPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class RegisterPO extends BasePage {
    private WebDriver driver;

    public RegisterPO(WebDriver driver){
        this.driver = driver;
    }

    @Step("get value message Register success")
    public String getRegisterSuccessMessage(){
        waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    @Step("Verify message error at Register")
    public String getErrorExistingEmailMessageTextBox() {
        waitForElementVisible(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
    }
}

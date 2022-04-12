package com.nopcommerce.pageObjects;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.commons.PageGeneratorManager;
import com.nopcommerce.pageUIs.LoginPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class LoginPO extends BasePage {
    private WebDriver driver;

    public LoginPO (WebDriver driver){
        this.driver = driver;
    }

    @Step("Verify login success")
    public boolean isDisplayMyAccount(){
        waitForElementVisible(driver, LoginPageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, LoginPageUI.MY_ACCOUNT_LINK);
    }

    @Step("get value message error 'Login'")
    public String getErrorMessageText() {
        waitForElementVisible(driver, LoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
        return getElementText(driver, LoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
    }

    @Step("Click to 'Home' Page Link")
    public HomePO openHomePage() {
        waitForElementClickable(driver, LoginPageUI.HOME_URL);
        clickToElement(driver, LoginPageUI.HOME_URL);
        return PageGeneratorManager.getHomePage(driver);
    }

    @Step("Login with role User")
    public void loginAsUser(String emailAddress, String password){
        enterToTextboxByID(driver, "Email", emailAddress);

        enterToTextboxByID(driver, "Password",password);

        clickToButtonByText(driver, "buttons","Log in");

    }
}

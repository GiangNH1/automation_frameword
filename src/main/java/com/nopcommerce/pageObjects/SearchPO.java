package com.nopcommerce.pageObjects;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.SearchUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPO extends BasePage {
    private WebDriver driver;
    public SearchPO(WebDriver driver){
        this.driver = driver;
    }

    @Step("Verify message is display on search")
    public boolean isDisplayErrorMessage(WebDriver driver, String messageError){
        waitForElementVisible(driver, SearchUI.DYNAMIC_MESSAGE_BY_TEXT, messageError);
        isElementDisplayed(driver, SearchUI.DYNAMIC_MESSAGE_BY_TEXT, messageError);
        return true;
    }

    @Step("Verify search result by product name with {0}")
    public boolean verifyProductName(String productRevative){
        boolean isDisplayName = false;
        waitForElementVisible(driver, SearchUI.PRODUCT_LIST);
        List<WebElement> listElements = getListWebElement(driver, SearchUI.PRODUCT_LIST);
        for (WebElement elements: listElements) {
            String productName = elements.getText();
            isDisplayName = productName.toLowerCase().contains(productRevative.toLowerCase());
            System.out.println(productName);
        }

        return isDisplayName;
    }
}

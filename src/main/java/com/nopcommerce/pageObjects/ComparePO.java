package com.nopcommerce.pageObjects;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.commons.PageGeneratorManager;
import com.nopcommerce.pageUIs.LoginPageUI;
import com.nopcommerce.pageUIs.WishListPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;


public class ComparePO extends BasePage {
    private WebDriver driver;

    public ComparePO(WebDriver driver){
        this.driver = driver;
    }

    @Step("Verify product name add to wish list success")
    public boolean isProductAddToWishListSuccess(String productName) {
        waitForElementVisible(driver, WishListPageUI.DYNAMIC_PRODUCT_NAME_ADD_TO_WISHLIST, productName);
        return isElementDisplayed(driver, WishListPageUI.DYNAMIC_PRODUCT_NAME_ADD_TO_WISHLIST, productName);
    }

    @Step("Click to 'Your wish list URL for sharing")
    public void clickToViewWishListLink(){
        waitForElementClickable(driver, WishListPageUI.VIEW_TO_WISHLIST_LINK);
        clickToElement(driver, WishListPageUI.VIEW_TO_WISHLIST_LINK);
    }

    @Step("click to {1} check box")
    public void clickToCheckboxByName(WebDriver driver, String productName) {
        waitForElementClickable(driver, WishListPageUI.DYNAMIC_CHECKBOX_BY_NAME, productName);
        checkToDefaultCheckboxRadio(driver, WishListPageUI.DYNAMIC_CHECKBOX_BY_NAME, productName);
    }

    @Step("click to remove button")
    public void clickToRemoveButton() {
        waitForElementClickable(driver, WishListPageUI.REMOVER_BUTTON);
        clickToElement(driver, WishListPageUI.REMOVER_BUTTON);
    }

    @Step("get message wish list is empty")
    public String getMessageWishListIsEmpty(){
        waitForElementVisible(driver, WishListPageUI.MESSAGE_WISHLIST_IS_EMPTY);
        return getElementText(driver, WishListPageUI.MESSAGE_WISHLIST_IS_EMPTY);
    }

    @Step("Click to 'Home' Page Link")
    public HomePO openHomePage() {
        waitForElementClickable(driver, LoginPageUI.HOME_URL);
        clickToElement(driver, LoginPageUI.HOME_URL);
        return PageGeneratorManager.getHomePage(driver);
    }
}

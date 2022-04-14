package com.nopcommerce.pageObjects;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.ProductDetailPageUI;
import com.nopcommerce.pageUIs.ShoppingCartPageUI;
import com.nopcommerce.pageUIs.WishListPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPO extends BasePage {
    private WebDriver driver;

    public ShoppingCartPO(WebDriver driver){
        this.driver = driver;
    }

    @Step("Verify notification message display")
    public boolean isNotificationMessage() {
        waitForElementVisible(driver, ProductDetailPageUI.NOTIFICATION_SUCCESS);
        return isElementDisplayed(driver, ProductDetailPageUI.NOTIFICATION_SUCCESS);
    }

    @Step("Verify product name add to wish list success")
    public boolean isProductAddToCartSuccess(String productName) {
        waitForElementVisible(driver, ShoppingCartPageUI.DYNAMIC_PRODUCT_NAME_ADD_TO_CART, productName);
        return isElementDisplayed(driver, ShoppingCartPageUI.DYNAMIC_PRODUCT_NAME_ADD_TO_CART, productName);
    }


}

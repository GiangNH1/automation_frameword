package com.nopcommerce.pageObjects;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.NotebooksPageUI;
import com.nopcommerce.pageUIs.WishListComparePageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WishlistComparePO extends BasePage {
    private WebDriver driver;
    public String productName, productName1, productName2, priceP1, priceP2;

    public WishlistComparePO(WebDriver driver){
        this.driver = driver;
    }

    public WishlistComparePO getNotebooksPage(WebDriver driver){
        return new WishlistComparePO(driver);
    }

    @Step("Verify number product is display per page")
    public boolean isDisplayNumberProductPerPage(int numberProduct) {
        boolean isProduct = false;
        waitForElementVisible(driver, NotebooksPageUI.PRODUCT_LIST);
        int productSize = getElementSize(driver, NotebooksPageUI.PRODUCT_LIST);

        if (productSize == numberProduct || productSize < numberProduct){
            isProduct = true;
        }
        return isProduct;
    }

    @Step("Click to product random")
    public void clickToProductRanDom() {
        waitForElementVisible(driver, NotebooksPageUI.PRODUCT_LIST);
        int productSize = getElementSize(driver, NotebooksPageUI.PRODUCT_LIST);
        int random_int = (int)(Math.random() * (productSize - 1 + 1) + 1);

        productName = getElementText(driver, NotebooksPageUI.CLICK_TO_PRODUCT_RANDOM, String.valueOf(random_int));

        clickToElement(driver, NotebooksPageUI.CLICK_TO_PRODUCT_RANDOM, String.valueOf(random_int));
    }

    @Step("Click to add any product 1 to the comparison list")
    public void clickToButtonCompareListRanDom() {
        waitForElementVisible(driver, NotebooksPageUI.PRODUCT_LIST);
        int productSize = getElementSize(driver, NotebooksPageUI.PRODUCT_LIST);
        int random_int = (int)(Math.random() * (productSize - 1 + 1) + 1);

        productName1 = getElementText(driver, NotebooksPageUI.CLICK_TO_PRODUCT_RANDOM, String.valueOf(random_int));
        priceP1 = getElementText(driver, NotebooksPageUI.GET_PRICE_PRODUCT, String.valueOf(random_int));

        clickToElement(driver, NotebooksPageUI.CLICK_TO_BUTTON_COMPARE_LIST, String.valueOf(random_int));
    }

    @Step("Click to add any product 2 to the comparison list")
    public void clickToButtonCompareListRanDom2() {
        waitForElementVisible(driver, NotebooksPageUI.PRODUCT_LIST);
        int productSize = getElementSize(driver, NotebooksPageUI.PRODUCT_LIST);
        int random_int = (int)(Math.random() * (productSize - 1 + 1) + 1);

        productName2 = getElementText(driver, NotebooksPageUI.CLICK_TO_PRODUCT_RANDOM, String.valueOf(random_int));
        priceP2 = getElementText(driver, NotebooksPageUI.GET_PRICE_PRODUCT, String.valueOf(random_int));

        clickToElement(driver, NotebooksPageUI.CLICK_TO_BUTTON_COMPARE_LIST, String.valueOf(random_int));
    }

    @Step("Verify product name 1 is display compare list page")
    public boolean isDisplayProductNameCompareListPage(String productName) {

        waitForElementVisible(driver, WishListComparePageUI.NAME_PRODUCT_COMPARE_LIST, productName);
        return isElementDisplayed(driver, WishListComparePageUI.NAME_PRODUCT_COMPARE_LIST, productName);
    }

    @Step("Verify product price is display compare list page")
    public boolean isDisplayProductPriceCompareListPage(String price) {

        waitForElementVisible(driver, WishListComparePageUI.PRICE_PRODUCT_COMPARE_LIST, price);
        return isElementDisplayed(driver, WishListComparePageUI.PRICE_PRODUCT_COMPARE_LIST, price);
    }

    @Step("Verify product price is display compare list page")
    public String getMessageClearList() {
        waitForElementVisible(driver, WishListComparePageUI.MESSAGE_DISPLAY_CLEAR_LIST);
        return getElementText(driver, WishListComparePageUI.MESSAGE_DISPLAY_CLEAR_LIST);
    }
}

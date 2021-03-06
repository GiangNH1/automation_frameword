package com.nopcommerce.pageUIs;

public class NotebooksPageUI {
    public static final String PRODUCT_TITLE = "css=.product-title";
    public static final String PRODUCT_PRICE = "css=.actual-price";
    public static final String PRODUCT_LIST = "xpath=//div[@class='products-container']//div[@class='item-box']";
    public static final String CLICK_TO_PRODUCT_RANDOM = "xpath=//div[@class='products-container']//div[@class='item-box'][%s]//h2[@class='product-title']//a";
    public static final String CLICK_TO_BUTTON_COMPARE_LIST = "xpath=//div[@class='products-container']//div[@class='item-box'][%s]//button[text()='Add to compare list']";
    public static final String GET_PRICE_PRODUCT = "xpath=//div[@class='products-container']//div[@class='item-box'][%s]//span[@class='price actual-price']";
}

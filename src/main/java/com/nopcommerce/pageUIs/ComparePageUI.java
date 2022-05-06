package com.nopcommerce.pageUIs;

public class ComparePageUI {
    public static final String DYNAMIC_PRODUCT_NAME_ADD_TO_WISHLIST = "xpath=//table[@class='cart']/tbody/tr/td/a[text()='%s']";
    public static final String VIEW_TO_WISHLIST_LINK = "xpath=//div[@class='share-info']//a";
    public static final String DYNAMIC_CHECKBOX_BY_NAME = "xpath=//a[text()='%s']//preceding::input[@name='addtocart']";
    public static final String REMOVER_BUTTON = "xpath=//button[@class='remove-btn']";
    public static final String MESSAGE_WISHLIST_IS_EMPTY = "xpath=//div[@class='no-data']";
}

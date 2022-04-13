package com.nopcommerce.pageObjects;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.NotebooksPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotebooksPO extends BasePage {
    private WebDriver driver;

    public NotebooksPO(WebDriver driver){
        this.driver = driver;
    }

    @Step("Verify product sort by name A - Z")
    public boolean isProductNameSortAToZ() {
        List<WebElement> productNameElements = getListWebElement(driver, NotebooksPageUI.PRODUCT_TITLE);

        List<String> productNameValue = new ArrayList<String>();

        for (WebElement productName : productNameElements) {
            productNameValue.add(productName.getText());
        }

        System.out.println("Before sort asc: -------------------------------------");
        for(String productPrice:productNameValue) {
            System.out.println(productPrice);
        }

        List<String> productNameClone = new ArrayList<String>(productNameValue);
        Collections.sort(productNameClone);

        System.out.println("After sort asc: -------------------------------------");
        for(String productPrice:productNameClone) {
            System.out.println(productPrice);
        }

        return productNameValue.equals(productNameClone);
    }

    @Step("Verify product sort by name A - Z")
    public boolean isProductNameSortZtoA() {
        List<WebElement> productNameElements = getListWebElement(driver, NotebooksPageUI.PRODUCT_TITLE);

        List<String> productNameValue = new ArrayList<String>();

        for (WebElement productName : productNameElements) {
            productNameValue.add(productName.getText());
        }

        System.out.println("Before sort desc: -------------------------------------");
        for(String productPrice:productNameValue) {
            System.out.println(productPrice);
        }

        List<String> productNameClone = new ArrayList<String>(productNameValue);
        Collections.sort(productNameClone);
        Collections.reverse(productNameClone);

        System.out.println("After sort desc: -------------------------------------");
        for(String productPrice:productNameClone) {
            System.out.println(productPrice);
        }

        return productNameValue.equals(productNameClone);
    }

    @Step("Verify product sort by price low to high")
    public boolean isProductPriceSortLowToHigh() {
        List<WebElement> productPriceElements = getListWebElement(driver, NotebooksPageUI.PRODUCT_PRICE);

        List<Integer> productPriceValue = new ArrayList<Integer>();

        for (WebElement productPrice : productPriceElements) {
            productPriceValue.add(Integer.parseInt(productPrice.getText().substring(1, productPrice.getText().length()-3).replace(",", "")));
        }

        System.out.println("Before sort asc: -------------------------------------");
        for(Integer productPrice:productPriceValue) {
            System.out.println(productPrice);
        }

        List<Integer> productNamePriceClone = new ArrayList<Integer>(productPriceValue);
        Collections.sort(productNamePriceClone);

        System.out.println("After sort asc: -------------------------------------");
        for(Integer productPrice:productNamePriceClone) {
            System.out.println(productPrice);
        }

        return productPriceValue.equals(productNamePriceClone);
    }

    @Step("Verify product sort by price high to low")
    public boolean isProductPriceSortHighToLow() {
        List<WebElement> productPriceElements = getListWebElement(driver, NotebooksPageUI.PRODUCT_PRICE);

        List<Float> productPriceValue = new ArrayList<Float>();

        for (WebElement productPrice : productPriceElements) {
            productPriceValue.add(Float.parseFloat(productPrice.getText().substring(1, productPrice.getText().length()-3).replace(",", ".")));
        }

        System.out.println("Before sort desc: -------------------------------------");
        for(Float productPrice:productPriceValue) {
            System.out.println(productPrice);
        }

        List<Float> productNamePriceClone = new ArrayList<Float>(productPriceValue);
        Collections.sort(productNamePriceClone);
        Collections.reverse(productNamePriceClone);

        System.out.println("After sort desc: -------------------------------------");
        for(Float productPrice:productNamePriceClone) {
            System.out.println(productPrice);
        }

        return productPriceValue.equals(productNamePriceClone);
    }

    @Step("Verify number product is display per page")
    public boolean isDisplayNumberProductPerPage(WebDriver driver, int numberProduct) {
        boolean isProduct = false;
        waitForElementVisible(driver, NotebooksPageUI.PRODUCT_LIST);
        int productSize = getElementSize(driver, NotebooksPageUI.PRODUCT_LIST);

        if (productSize == numberProduct || productSize < numberProduct){
            isProduct = true;
        }
        return isProduct;
    }
}

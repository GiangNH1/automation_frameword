package com.nopcommerce.pageObjects;

import com.nopcommerce.commons.BasePage;
import com.nopcommerce.pageUIs.ChangePasswordPageUI;
import com.nopcommerce.pageUIs.ProductDetailPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class ProductDetailPO extends BasePage {
    private WebDriver driver;

    public ProductDetailPO(WebDriver driver){
        this.driver = driver;
    }
}

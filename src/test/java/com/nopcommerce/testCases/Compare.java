package com.nopcommerce.testCases;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.commons.PageGeneratorManager;
import com.nopcommerce.pageObjects.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Compare extends BaseTest {
    private WebDriver driver;
    private HomePO homePage;
    private LoginPO loginPage;
    private NotebooksPO notebooksPage;
    private WishlistComparePO wishlistComparePage;
    private WishListPO wishListPage;
    private ComparePO comparePage;
    private ShoppingCartPO shoppingCartPage;
    public static String productName;

    @Description("Pre-condition")
    @Severity(SeverityLevel.NORMAL)
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){

        driver = getBrowserDriverManager(browserName, url);

        homePage = PageGeneratorManager.getHomePage(driver);
        loginPage = PageGeneratorManager.getLoginPage(driver);

        loginPage.setAllCookie(driver,Login.loginCookie);

        homePage = loginPage.openHomePage();

    }

    @Description("Add To Compare List")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_01_Add_To_Compare_List(){
        homePage.openSubMenuPageTopPC(driver, "Computers ", "Notebooks ");
        wishlistComparePage = PageGeneratorManager.getWishListComparePage(driver);

        wishlistComparePage.clickToButtonCompareListRanDom();
        verifyTrue(wishlistComparePage.isJQueryAjaxLoadSuccess(driver));

        verifyTrue(wishlistComparePage.isNotificationMessage(driver));

        wishlistComparePage.clickCloseMessage(driver);

        wishlistComparePage.clickToButtonCompareListRanDom2();
        verifyTrue(wishlistComparePage.isJQueryAjaxLoadSuccess(driver));

        verifyTrue(wishlistComparePage.isNotificationMessage(driver));

        wishlistComparePage.clickToLinkPage(driver, "product comparison");

        verifyTrue(wishlistComparePage.isDisplayProductNameCompareListPage(wishlistComparePage.productName1));
        verifyTrue(wishlistComparePage.isDisplayProductNameCompareListPage(wishlistComparePage.productName2));
        verifyTrue(wishlistComparePage.isDisplayProductPriceCompareListPage(wishlistComparePage.priceP1));
        verifyTrue(wishlistComparePage.isDisplayProductPriceCompareListPage(wishlistComparePage.priceP2));

        wishlistComparePage.clickToLinkPage(driver, "Clear list");

        verifyEquals(wishlistComparePage.getMessageClearList(), "You have no items to compare.");

    }

    @Description("Add product to cart from wishlist page")
    @Severity(SeverityLevel.NORMAL)
    //@Test
    public void TC_02_Add_Product_To_Cart_From_Wishlist() {
        homePage = wishListPage.openHomePage();
        homePage.clickToLinkPage(driver, "Wishlist");

        wishListPage = PageGeneratorManager.getWishListPage(driver);
        wishListPage.clickToCheckboxByName(driver, notebooksPage.productName);

        wishListPage.clickToButtonByText(driver, "buttons", "Add to cart");

        shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
        verifyTrue(shoppingCartPage.getTitlePage(driver));

        verifyTrue(shoppingCartPage.isProductAddToCartSuccess(notebooksPage.productName));

        shoppingCartPage.clickToLinkPage(driver, "Wishlist");
        wishListPage = PageGeneratorManager.getWishListPage(driver);

        verifyTrue(wishListPage.isProductNameUnDisplay(driver, notebooksPage.productName));
    }



    @Description("Sort Product By Price High To Low")
    @Severity(SeverityLevel.NORMAL)
    //@Test
    public void TC_04_Sort_Price_High_To_Low(){
        homePage.openSubMenuPageTopPC(driver, "Computers ", "Notebooks ");
        notebooksPage = PageGeneratorManager.getNotebooksPage(driver);

        notebooksPage.selectDefaultByText(driver, "products-orderby","Price: High to Low");

        verifyTrue(notebooksPage.isJQueryAjaxLoadSuccess(driver));

        verifyTrue(notebooksPage.isProductPriceSortHighToLow());
    }

    @Description("Display With 3 Product Per Page")
    @Severity(SeverityLevel.NORMAL)
    //@Test
    public void TC_05_Display_3_Product_Per_Page(){
        homePage.openSubMenuPageTopPC(driver, "Computers ", "Notebooks ");
        notebooksPage = PageGeneratorManager.getNotebooksPage(driver);

        notebooksPage.selectDefaultByText(driver, "products-pagesize","3");
        verifyTrue(notebooksPage.isJQueryAjaxLoadSuccess(driver));

        verifyTrue(notebooksPage.isDisplayNumberProductPerPage(driver,3));

        verifyTrue(notebooksPage.isDisplayNextIcon(driver,"Next"));

        notebooksPage.clickToLinkPage(driver, "2");
        verifyTrue(notebooksPage.isJQueryAjaxLoadSuccess(driver));

        verifyTrue(notebooksPage.isDisplayNextIcon(driver,"Previous"));
    }

    @Description("Display With 6 Product And Check Display Paging")
    @Severity(SeverityLevel.NORMAL)
    //@Test
    public void TC_06_Display_6_Product_Per_Page(){
        homePage.openSubMenuPageTopPC(driver, "Computers ", "Notebooks ");
        notebooksPage = PageGeneratorManager.getNotebooksPage(driver);

        notebooksPage.selectDefaultByText(driver, "products-pagesize","6");
        verifyTrue(notebooksPage.isJQueryAjaxLoadSuccess(driver));

        verifyTrue(notebooksPage.isDisplayNumberProductPerPage(driver,6));

        verifyTrue(notebooksPage.isDisplayPaging(driver,"pager"));

    }

    @Description("Display With 9 Product And Check Display Paging")
    @Severity(SeverityLevel.NORMAL)
    //@Test
    public void TC_07_Display_9_Product_Per_Page(){
        homePage.openSubMenuPageTopPC(driver, "Computers ", "Notebooks ");
        notebooksPage = PageGeneratorManager.getNotebooksPage(driver);

        notebooksPage.selectDefaultByText(driver, "products-pagesize","9");
        verifyTrue(notebooksPage.isJQueryAjaxLoadSuccess(driver));

        verifyTrue(notebooksPage.isDisplayNumberProductPerPage(driver,9));

        verifyTrue(notebooksPage.isDisplayPaging(driver,"pager"));
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }

}

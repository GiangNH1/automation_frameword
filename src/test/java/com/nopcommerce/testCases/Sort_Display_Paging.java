package com.nopcommerce.testCases;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.commons.PageGeneratorManager;
import com.nopcommerce.pageObjects.HomePO;
import com.nopcommerce.pageObjects.NotebooksPO;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Sort_Display_Paging extends BaseTest {
    private WebDriver driver;
    private HomePO homePage;
    private NotebooksPO notebooksPage;

    @Description("Pre-condition")
    @Severity(SeverityLevel.NORMAL)
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){

        driver = getBrowserDriverManager(browserName, url);

        homePage = PageGeneratorManager.getHomePage(driver);

    }

    @Description("Sort Product By Name A - Z")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_01_Sort_Name_A_To_Z(){
        homePage.openSubMenuPageTopPC(driver, "Computers ", "Notebooks ");
        notebooksPage = PageGeneratorManager.getNotebooksPage(driver);

        notebooksPage.selectDefaultByText(driver, "products-orderby","Name: A to Z");

        verifyTrue(notebooksPage.isJQueryAjaxLoadSuccess(driver));

        verifyTrue(notebooksPage.isProductNameSortAToZ());

    }

    @Description("Sort Product By Name Z - A")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_02_Sort_Name_Z_To_A() {
        homePage.openSubMenuPageTopPC(driver, "Computers ", "Notebooks ");
        notebooksPage = PageGeneratorManager.getNotebooksPage(driver);

        notebooksPage.selectDefaultByText(driver, "products-orderby","Name: Z to A");

        verifyTrue(notebooksPage.isJQueryAjaxLoadSuccess(driver));

        verifyTrue(notebooksPage.isProductNameSortZtoA());
    }

    @Description("Sort Product By Price Low To High")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_03_Sort_Price_Low_To_High() {
        homePage.openSubMenuPageTopPC(driver, "Computers ", "Notebooks ");
        notebooksPage = PageGeneratorManager.getNotebooksPage(driver);

        notebooksPage.selectDefaultByText(driver, "products-orderby","Price: Low to High");

        verifyTrue(notebooksPage.isJQueryAjaxLoadSuccess(driver));

        verifyTrue(notebooksPage.isProductPriceSortLowToHigh());
    }

    @Description("Sort Product By Price High To Low")
    @Severity(SeverityLevel.NORMAL)
    @Test
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
    @Test
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
    @Test
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

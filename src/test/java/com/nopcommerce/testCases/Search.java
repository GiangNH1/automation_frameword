package com.nopcommerce.testCases;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.commons.PageGeneratorManager;
import com.nopcommerce.pageObjects.HomePO;
import com.nopcommerce.pageObjects.LoginPO;
import com.nopcommerce.pageObjects.SearchPO;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Search extends BaseTest {
    private WebDriver driver;
    private SearchPO searchPage;
    private HomePO homePage;
    private LoginPO loginPage;
    private String revativeData, absoluteData, keywordSearch;

    @Description("Pre-condition")
    @Severity(SeverityLevel.NORMAL)
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforClass(String browserName, String url){

        driver = getBrowserDriverManager(browserName, url);

        homePage = PageGeneratorManager.getHomePage(driver);

        //loginPage.setAllCookie(driver,Login.loginCookie);

        revativeData = "Lenovo";
        absoluteData = "Lenovo Thinkpad X1 Carbon Laptop";
        keywordSearch = "Apple Macbook Pro";

    }

    @Description("Search Empty Data")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_01_Search_Empty_Data(){
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        searchPage.enterToTextboxByID(driver, "q", "");

        searchPage.clickToButtonByText(driver, "buttons","Search");

        verifyTrue(searchPage.isDisplayErrorMessage(driver,"Search term minimum length is 3 characters"));
    }

    @Description("Search Data Not Existing")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_02_Search_Data_Not_Existing(){
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        searchPage.enterToTextboxByID(driver, "q", "Markbook pro 2050");

        searchPage.clickToButtonByText(driver, "buttons","Search");

        verifyTrue(searchPage.isDisplayErrorMessage(driver,"No products were found that matched your criteria."));
    }

    @Description("Search Data Relative")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_03_Search_Data_Relative(){
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        searchPage.enterToTextboxByID(driver, "q", revativeData);

        searchPage.clickToButtonByText(driver, "buttons","Search");

        verifyTrue(searchPage.verifyProductName(revativeData));
    }

    @Description("Search Data Absolute")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_04_Search_Data_Absolute(){
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        searchPage.enterToTextboxByID(driver, "q", absoluteData);

        searchPage.clickToButtonByText(driver, "buttons","Search");

        verifyTrue(searchPage.verifyProductName(absoluteData));
    }

    @Description("Advanced Search Categories")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_05_Advanced_Search_Parent_Categories(){
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        searchPage.enterToTextboxByID(driver, "q", keywordSearch);

        searchPage.checkedTextboxByID(driver, "advs");

        searchPage.selectDefaultByText(driver, "cid", "Computers");

        searchPage.unCheckedTextboxByID(driver, "isc");

        searchPage.clickToButtonByText(driver,"buttons", "Search");

        verifyTrue(searchPage.isDisplayErrorMessage(driver,"No products were found that matched your criteria."));
    }

    @Description("Advanced Search Sub Categories")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_06_Advanced_Search_Sub_Categories(){
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        searchPage.enterToTextboxByID(driver, "q", keywordSearch);

        searchPage.checkedTextboxByID(driver, "advs");

        searchPage.selectDefaultByText(driver, "cid", "Computers");

        searchPage.checkedTextboxByID(driver, "isc");

        searchPage.clickToButtonByText(driver,"buttons", "Search");

        Assert.assertTrue(searchPage.verifyProductName(keywordSearch));
    }

    @Description("Advanced Search Correct Manufacturer")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_07_Advanced_Search_Incorrect_Manufacturer(){
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        searchPage.enterToTextboxByID(driver, "q", keywordSearch);

        searchPage.checkedTextboxByID(driver, "advs");

        searchPage.selectDefaultByText(driver, "cid", "Computers");

        searchPage.checkedTextboxByID(driver, "isc");

        searchPage.selectDefaultByText(driver, "mid", "HP");

        searchPage.clickToButtonByText(driver, "buttons", "Search");

        verifyTrue(searchPage.isDisplayErrorMessage(driver,"No products were found that matched your criteria."));
    }

    @Description("Advanced Search Correct Manufacturer")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_08_Advanced_Search_Correct_Manufacturer(){
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        searchPage.enterToTextboxByID(driver, "q", keywordSearch);

        searchPage.checkedTextboxByID(driver, "advs");

        searchPage.selectDefaultByText(driver, "cid", "Computers");

        searchPage.checkedTextboxByID(driver, "isc");

        searchPage.selectDefaultByText(driver, "mid", "Apple");

        searchPage.clickToButtonByText(driver, "buttons","Search");

        Assert.assertTrue(searchPage.verifyProductName(keywordSearch));
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }

}

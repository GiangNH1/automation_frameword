package com.nopcommerce.testCases;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.commons.PageGeneratorManager;
import com.nopcommerce.pageObjects.HomePO;
import com.nopcommerce.pageObjects.SearchPO;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Log4jSearch extends BaseTest {
    private WebDriver driver;
    private SearchPO searchPage;
    private HomePO homePage;
    private String revativeData, absoluteData, keywordSearch;


    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforClass(String browserName, String url){

        log.info("Pre-condition - step 01: Open Page With Browser '" + browserName + "' And Url: '" + url +";");
        driver = getBrowserDriverManager(browserName, url);

        log.info("Pre-condition - step 02: Khởi tạo page search");
        homePage = PageGeneratorManager.getHomePage(driver);

        revativeData = "Lenovo";
        absoluteData = "Lenovo Thinkpad X1 Carbon Laptop";
        keywordSearch = "Apple Macbook Pro";

    }

    @Test
    public void TC_01_Search_Empty_Data(){
        log.info("Search Empty Data - step 01: Click to link search on footer");
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        log.info("Search Empty Data - step 02: Click button search");
        searchPage.clickToButtonByText(driver, "buttons","Search");

        log.info("Search Empty Data - step 03: Verify message waning 'Search term minimum length is 3 characters' is display");
        verifyTrue(searchPage.isDisplayErrorMessage(driver,"Search term minimum length is 3 characters"));
    }

    @Test
    public void TC_02_Search_Data_Not_Existing(){
        log.info("Search Data Not Existing - step 01: Click to link search on footer");
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        log.info("Search Data Not Existing - step 02: Enter to search with data not existing \"Markbook pro 2050\" ");
        searchPage.enterToTextboxByID(driver, "q", "Markbook pro 2050");

        log.info("Search Data Not Existing - step 03: Click button search");
        searchPage.clickToButtonByText(driver, "buttons","Search");

        log.info("Search Data Not Existing - step 04: Verify message 'No products were found that matched your criteria.' is display");
        verifyTrue(searchPage.isDisplayErrorMessage(driver,"No products were found that matched your criteria."));
    }

    @Test
    public void TC_03_Search_Data_Relative(){
        log.info("Search Data Relative - step 01: Click to link search on footer");
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        log.info("Search Data Relative - step 02: Enter to search with data relative "+ revativeData +"'");
        searchPage.enterToTextboxByID(driver, "q", revativeData);

        log.info("Search Data Relative - step 03: Click button search");
        searchPage.clickToButtonByText(driver, "buttons","Search");

        log.info("Search Data Relative - step 04: Verify data is display");
        verifyTrue(searchPage.verifyProductName(revativeData));
    }

    @Test
    public void TC_04_Search_Data_Absolute(){
        log.info("Search Data Absolute - step 01: Click to link search on footer");
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        log.info("Search Absolute - step 02: Enter to search with data not existing '"+ absoluteData +"'");
        searchPage.enterToTextboxByID(driver, "q", absoluteData);

        log.info("Search Absolute - step 03: Click button search");
        searchPage.clickToButtonByText(driver, "buttons","Search");

        log.info("Search Absolute - step 04: Verify data is display");
        verifyTrue(searchPage.verifyProductName(absoluteData));
    }

    @Test
    public void TC_05_Advanced_Search_Parent_Categories(){
        log.info("Advanced Search Categories - step 01: Click to link search on footer");
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        log.info("Advanced Search Categories - step 02: Enter to search with data not existing '"+ keywordSearch +"'");
        searchPage.enterToTextboxByID(driver, "q", keywordSearch);

        log.info("Advanced Search Categories - step 03: Checked advanced search");
        searchPage.checkedTextboxByID(driver, "advs");

        log.info("Advanced Search Categories - step 04: Categories -> Selected Item 'Computers' ");
        searchPage.selectDefaultByText(driver, "cid", "Computers");

        log.info("Advanced Search Categories - step 05: Automatically search sub categories: Unchecked");
        searchPage.unCheckedTextboxByID(driver, "isc");

        log.info("Advanced Search Categories - step 06: Click button search");
        searchPage.clickToButtonByText(driver,"buttons", "Search");

        log.info("Advanced Search Categories - step 07: Verify data is display");
        verifyTrue(searchPage.isDisplayErrorMessage(driver,"No products were found that matched your criteria."));
    }

    @Test
    public void TC_06_Advanced_Search_Sub_Categories(){
        log.info("Advanced Search Sub Categories - step 01: Click to link search on footer");
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        log.info("Advanced Search Sub Categories - step 02: Enter to search with data not existing '"+ keywordSearch +"'");
        searchPage.enterToTextboxByID(driver, "q", keywordSearch);

        log.info("Advanced Search Sub Categories - step 03: Checked advanced search");
        searchPage.checkedTextboxByID(driver, "advs");

        log.info("Advanced Search Sub Categories - step 04: Categories -> Selected Item 'Computers' ");
        searchPage.selectDefaultByText(driver, "cid", "Computers");

        log.info("Advanced Search Sub Categories - step 05: Automatically search sub categories: checked");
        searchPage.checkedTextboxByID(driver, "isc");

        log.info("Advanced Search Sub Categories - step 06: Click button search");
        searchPage.clickToButtonByText(driver,"buttons", "Search");

        log.info("Advanced Search Sub Categories - step 07: Verify data is display");
        Assert.assertTrue(searchPage.verifyProductName(keywordSearch));
    }

    @Test
    public void TC_07_Advanced_Search_Incorrect_Manufacturer(){
        log.info("Advanced Search Correct Manufacturer - step 01: Click to link search on footer");
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        log.info("Advanced Search Correct Manufacturer - step 02: Enter to search with data not existing '"+ keywordSearch +"'");
        searchPage.enterToTextboxByID(driver, "q", keywordSearch);

        log.info("Advanced Search Correct Manufacturer - step 03: Checked advanced search");
        searchPage.checkedTextboxByID(driver, "advs");

        log.info("Advanced Search Correct Manufacturer - step 04: Categories -> Selected Item 'Computers' ");
        searchPage.selectDefaultByText(driver, "cid", "Computers");

        log.info("Advanced Search Correct Manufacturer - step 05: Automatically search sub categories: checked");
        searchPage.checkedTextboxByID(driver, "isc");

        log.info("Advanced Search Correct Manufacturer - step 06: Manufacturer -> Selected Item 'HP' ");
        searchPage.selectDefaultByText(driver, "mid", "HP");

        log.info("Advanced Search Correct Manufacturer - step 07: Click button search");
        searchPage.clickToButtonByText(driver, "buttons", "Search");

        log.info("Advanced Search Correct Manufacturer - step 08: Verify data is display");
        verifyTrue(searchPage.isDisplayErrorMessage(driver,"No products were found that matched your criteria."));
    }

    @Test
    public void TC_08_Advanced_Search_Correct_Manufacturer(){
        log.info("Advanced Search Correct Manufacturer - step 01: Click to link search on footer");
        homePage.clickToLinkPage(driver, "Search");
        searchPage = PageGeneratorManager.getSearchPage(driver);

        log.info("Advanced Search Correct Manufacturer - step 02: Enter to search with data not existing '"+ keywordSearch +"'");
        searchPage.enterToTextboxByID(driver, "q", keywordSearch);

        log.info("Advanced Search Correct Manufacturer - step 03: Checked advanced search");
        searchPage.checkedTextboxByID(driver, "advs");

        log.info("Advanced Search Correct Manufacturer - step 04: Categories -> Selected Item 'Computers' ");
        searchPage.selectDefaultByText(driver, "cid", "Computers");

        log.info("Advanced Search Correct Manufacturer - step 05: Automatically search sub categories: checked");
        searchPage.checkedTextboxByID(driver, "isc");

        log.info("Advanced Search Correct Manufacturer - step 06: Manufacturer -> Selected Item 'Apple' ");
        searchPage.selectDefaultByText(driver, "mid", "Apple");

        log.info("Advanced Search Correct Manufacturer - step 07: Click button search");
        searchPage.clickToButtonByText(driver, "buttons","Search");

        log.info("Advanced Search Correct Manufacturer - step 08: Verify data is display");
        Assert.assertTrue(searchPage.verifyProductName(keywordSearch));
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }

}

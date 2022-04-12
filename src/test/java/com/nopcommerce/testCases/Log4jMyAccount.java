package com.nopcommerce.testCases;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.commons.GlobalConstants;
import com.nopcommerce.commons.PageGeneratorManager;
import com.nopcommerce.pageObjects.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Log4jMyAccount extends BaseTest {
    private WebDriver driver;
    private RegisterPO registerPage;
    private LoginPO loginPage;
    private HomePO homePage;
    private AddressPO addressPage;
    private CustomerInfoPO customerInfoPage;
    private ChangePasswordPO changePasswordPage;
    String emailAddress, firstName, lastName, day, month, year, gender, country, city, address1, address2, zip, phoneNumber;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforClass(String browserName, String url) {

        log.info("Pre-condition - step 01: Open Page With Browser '" + browserName + "' And Url: '" + url + ";");
        driver = getBrowserDriverManager(browserName, url);

        emailAddress = GlobalConstants.EMAIL_ADDRESS;
        firstName = "Automation"; lastName = "FC"; day = "1"; month= "January"; year = "2000"; gender = "Female";
        country = "Viet Nam"; city = "Ho Chi Minh"; address1 = "44 Bui Van Ba"; address2 = "Quan 7"; zip = "700000"; phoneNumber="0345671897";


        log.info("Pre-condition - step 02: Khởi tạo page login");
        loginPage = PageGeneratorManager.getLoginPage(driver);

        log.info("Pre-condition - step 03: Set cookie login page");
        loginPage.setAllCookie(driver,Login.loginCookie);

        log.info("Pre-condition - step 04: Click navigate home page");
        homePage = loginPage.openHomePage();
    }

    //@Test
    public void TC_01_Update_Customer_Info(){
        log.info("Update Customer Info - step 01: Navigate to 'My Account' page");
        homePage.clickToLinkPage(driver, "My account");

        log.info("Update Customer Info - step 02: Verify Customer Info page open");
        customerInfoPage = PageGeneratorManager.getCustomerInfoPage(driver);
        verifyEquals(customerInfoPage.getTitlePage(), "My account - Customer info");

        log.info("Update Customer Info - step 03: Gender '" + gender +"' checked");
        customerInfoPage.checkedTextboxByID(driver, "gender-female");

        log.info("Update Customer Info - step 04: Enter to Firstname text box with value is '" + firstName +"'");
        customerInfoPage.enterToTextboxByID(driver, "FirstName",firstName);

        log.info("Update Customer Info - step 05: Enter to Lastname text box with value is '" + lastName +"'");
        customerInfoPage.enterToTextboxByID(driver, "LastName",lastName);

        log.info("Update Customer Info - step 06: Select to Day drop dow with value is '"+ day +"'");
        customerInfoPage.selectDefaultByText(driver, "DateOfBirthDay", day);

        log.info("Update Customer Info - step 07: Select to Moth drop dow with value is '"+ month +"'");
        customerInfoPage.selectDefaultByText(driver, "DateOfBirthMonth", month);

        log.info("Update Customer Info - step 08: Select to Year drop dow with value is '"+ year +"'");
        customerInfoPage.selectDefaultByText(driver, "DateOfBirthYear", year);

        log.info("Update Customer Info - step 09: Enter to email textbox with value is '"+ emailAddress +"'");
        customerInfoPage.enterToTextboxByID(driver, "Email", emailAddress);

        log.info("Update Customer Info - step 10: Newsletter: checked");
        customerInfoPage.checkedTextboxByID(driver, "Newsletter");

        log.info("Update Customer Info - step 11: Click to 'Save' button");
        customerInfoPage.clickToButtonByText(driver, "buttons","Save");
        System.out.println(emailAddress);

        log.info("Update Customer Info - step 12: Verify gender checked female");
        verifyTrue(customerInfoPage.isCheckedToGender("gender-female"));

        log.info("Update Customer Info - step 14: Verify data at fist name");
        verifyEquals(customerInfoPage.getTextAttribute(driver, "value", "FirstName"), firstName);

        log.info("Update Customer Info - step 15: Verify data at last name");
        verifyEquals(customerInfoPage.getTextAttribute(driver, "value", "LastName"), lastName);

        log.info("Update Customer Info - step 16: Verify data at day of birth");
        verifyEquals(customerInfoPage.getSelectDefaultByText(driver, "DateOfBirthDay"), day);

        log.info("Update Customer Info - step 17: Verify data at month of birth");
        verifyEquals(customerInfoPage.getSelectDefaultByText(driver, "DateOfBirthMonth"), month);

        log.info("Update Customer Info - step 18: Verify data at year of birth");
        verifyEquals(customerInfoPage.getSelectDefaultByText(driver, "DateOfBirthYear"), year);

        log.info("Update Customer Info - step 19: Verify data at Email");
        verifyEquals(customerInfoPage.getTextAttribute(driver, "value", "Email"), emailAddress);

        log.info("Update Customer Info - step 20: Verify checked at newsletter");
        verifyTrue(customerInfoPage.isCheckedToGender("Newsletter"));
    }

    //@Test
    public void TC_02_Add_New_Address(){
        log.info("Add New Address - step 01: Navigate to 'My Account' page");
        homePage.clickToLinkPage(driver, "My account");

        customerInfoPage = PageGeneratorManager.getCustomerInfoPage(driver);
        log.info("Add New Address - step 01: Navigate to 'My Account' page");
        customerInfoPage.openPagesAtMyAccountByName(driver, "Addresses");

        log.info("Add New Address - step 02: Verify Address page open");
        addressPage = PageGeneratorManager.getAddressPage(driver);
        verifyEquals(addressPage.getTitlePage(), "My account - Addresses");

        log.info("Add New Address - step 03: Click to 'Add new' button");
        customerInfoPage.clickToButtonByText(driver, "add-button","Add new");

        log.info("Add New Address - step 04: Enter to Firstname text box with value is '" + firstName +"'");
        addressPage.enterToTextboxByID(driver, "Address_FirstName",firstName);

        log.info("Add New Address - step 05: Enter to Lastname text box with value is '" + lastName +"'");
        addressPage.enterToTextboxByID(driver, "Address_LastName",lastName);

        log.info("Add New Address - step 06: Enter to email text box with value is '"+ emailAddress +"'");
        customerInfoPage.enterToTextboxByID(driver, "Address_Email", emailAddress);

        log.info("Add New Address - step 07: Select to Country drop dow with value is '"+ country +"'");
        customerInfoPage.selectDefaultByText(driver, "Address.CountryId", country);

        log.info("Add New Address - step 08: Enter to City text box with value is '" + city +"'");
        addressPage.enterToTextboxByID(driver, "Address_City",city);

        log.info("Add New Address - step 09: Enter to Address 1 text box with value is '" + address1 +"'");
        addressPage.enterToTextboxByID(driver, "Address_Address1",address1);

        log.info("Add New Address - step 10: Enter to Address 2 text box with value is '" + address2 +"'");
        addressPage.enterToTextboxByID(driver, "Address_Address2",address2);

        log.info("Add New Address - step 11: Enter to Zip / postal code text box with value is '" + zip +"'");
        addressPage.enterToTextboxByID(driver, "Address_ZipPostalCode", zip);

        log.info("Add New Address - step 12: Enter to Phone number text box with value is '" + phoneNumber +"'");
        addressPage.enterToTextboxByID(driver, "Address_PhoneNumber", phoneNumber);

        log.info("Add New Address - step 13: Click to 'Save' button");
        addressPage.clickToButtonByText(driver, "buttons","Save");
        System.out.println(emailAddress);

        log.info("Add New Address - step 14: Verify data at Name");
        verifyEquals(addressPage.getTextNewAddress("name"), firstName +" "+ lastName);

        log.info("Add New Address - step 15: Verify data at Email");
        verifyEquals(addressPage.getTextNewAddress("email"), "Email: "+emailAddress);

        log.info("Add New Address - step 16: Verify data at Phone number");
        verifyEquals(addressPage.getTextNewAddress("phone"), "Phone number: " +phoneNumber);

        log.info("Add New Address - step 17: Verify data at Address1");
        verifyEquals(addressPage.getTextNewAddress("address1"), address1);

        log.info("Add New Address - step 18: Verify data at Address2");
        verifyEquals(addressPage.getTextNewAddress("address2"), address2);

        log.info("Add New Address - step 19: Verify data at City and Zip");
        verifyEquals(addressPage.getTextNewAddress("city-state-zip"), city +", "+ zip);

        log.info("Add New Address - step 20: Verify data at Country");
        verifyEquals(addressPage.getTextNewAddress("country"), country);

    }

    @Test
    public void TC_03_Change_Password(){
        log.info("Change Password - step 01: Navigate to 'My Account' page");
        homePage.clickToLinkPage(driver, "My account");

        changePasswordPage = PageGeneratorManager.getChangePasswordPage(driver);
        log.info("Change Password - step 02: Navigate to 'Change password' page");
        changePasswordPage.openPagesAtMyAccountByName(driver, "Change password");

        log.info("Change Password - step 03: Enter to Old Password text box with value is '" + GlobalConstants.OLD_PASSWORD +"'");
        changePasswordPage.enterToTextboxByID(driver, "OldPassword", GlobalConstants.OLD_PASSWORD);

        log.info("Change Password - step 04: Enter to New Password text box with value is '" + GlobalConstants.NEW_PASSWORD +"'");
        changePasswordPage.enterToTextboxByID(driver, "NewPassword",GlobalConstants.NEW_PASSWORD);

        log.info("Change Password - step 05: Enter to Confirm Password text box with value is '" + GlobalConstants.NEW_PASSWORD +"'");
        changePasswordPage.enterToTextboxByID(driver, "ConfirmNewPassword",GlobalConstants.NEW_PASSWORD);

        log.info("Change Password - step 06: Click to 'Change password' button");
        changePasswordPage.clickToButtonByText(driver, "buttons","Change password");

        log.info("Change Password - step 07: Verify change password success");
        verifyEquals(changePasswordPage.getTextChangePassword(), "Password was changed");

        log.info("Change Password - step 08: Click close Message");
        changePasswordPage.clickCloseMessage();

        log.info("Change Password - step 09: Deleted all cookie");
//        homePage = PageGeneratorManager.getHomePage(driver);
//        homePage.clickToLinkPage(driver, "Log out");
        changePasswordPage.deleteAllCookie(driver);

        log.info("Pre-condition - step 10: Click navigate home page");
        homePage = loginPage.openHomePage();

        log.info("Change Password - step 11: Navigate to 'Login' page");
        homePage.clickToLinkPage(driver, "Log in");

        log.info("Change Password - step 12: Login with password old");
        loginPage.loginAsUser(emailAddress, GlobalConstants.OLD_PASSWORD);

        log.info("Change Password - step 13: Verify login unsuccessful");
        Assert.assertEquals(loginPage.getErrorMessageText(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

        log.info("Change Password - step 14: Set cookie login with password new page");
        loginPage.loginAsUser(emailAddress, GlobalConstants.NEW_PASSWORD);

        log.info("Change Password - step 15: Verify login successful");
        Assert.assertTrue(loginPage.isDisplayMyAccount());
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }

}

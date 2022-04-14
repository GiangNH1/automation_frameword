package com.nopcommerce.testCases;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.commons.GlobalConstants;
import com.nopcommerce.commons.PageGeneratorManager;
import com.nopcommerce.pageObjects.*;
import com.nopcommerce.utilities.DataFaker;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MyAccount extends BaseTest {
    private WebDriver driver;
    private RegisterPO registerPage;
    private LoginPO loginPage;
    private HomePO homePage;
    private AddressPO addressPage;
    private CustomerInfoPO customerInfoPage;
    private ChangePasswordPO changePasswordPage;
    private DataFaker dataFaker;

    private String emailAddress, passwordOld, passwordNew, firstName, lastName, day, month, year, gender, country, city, address1, address2, zip, phoneNumber;

    @Description("Pre-condition")
    @Severity(SeverityLevel.NORMAL)
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforClass(String browserName, String url) {

        driver = getBrowserDriverManager(browserName, url);
        dataFaker = DataFaker.getData();

        emailAddress = "giangauto"+dataFaker.getEmail();
        passwordOld = Register.password;
        passwordNew = dataFaker.getPassword();
        firstName = dataFaker.getFirstName(); lastName = dataFaker.getLastName(); day = "1"; month= "January"; year = "2000"; gender = "Female";
        country = "Viet Nam"; city = dataFaker.getCity(); address1 = dataFaker.getAddress1(); address2 = dataFaker.getAddress2(); zip = dataFaker.getZip(); phoneNumber= dataFaker.getPhone();


        loginPage = PageGeneratorManager.getLoginPage(driver);

        loginPage.setAllCookie(driver,Login.loginCookie);

        homePage = loginPage.openHomePage();
    }

    @Description("Update Customer Info")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_01_Update_Customer_Info(){
        homePage.clickToLinkPage(driver, "My account");

        customerInfoPage = PageGeneratorManager.getCustomerInfoPage(driver);
        verifyEquals(customerInfoPage.getTitlePage(), "My account - Customer info");

        customerInfoPage.checkedTextboxByID(driver, "gender-female");

        customerInfoPage.enterToTextboxByID(driver, "FirstName",firstName);

        customerInfoPage.enterToTextboxByID(driver, "LastName",lastName);

        customerInfoPage.selectDefaultByText(driver, "DateOfBirthDay", day);

        customerInfoPage.selectDefaultByText(driver, "DateOfBirthMonth", month);

        customerInfoPage.selectDefaultByText(driver, "DateOfBirthYear", year);

        customerInfoPage.enterToTextboxByID(driver, "Email", emailAddress);

        customerInfoPage.checkedTextboxByID(driver, "Newsletter");

        customerInfoPage.clickToButtonByText(driver, "buttons","Save");
        System.out.println(emailAddress);

        verifyTrue(customerInfoPage.isCheckedToGender("gender-female"));

        verifyEquals(customerInfoPage.getTextAttribute(driver, "value", "FirstName"), firstName);

        verifyEquals(customerInfoPage.getTextAttribute(driver, "value", "LastName"), lastName);

        verifyEquals(customerInfoPage.getSelectDefaultByText(driver, "DateOfBirthDay"), day);

        verifyEquals(customerInfoPage.getSelectDefaultByText(driver, "DateOfBirthMonth"), month);

        verifyEquals(customerInfoPage.getSelectDefaultByText(driver, "DateOfBirthYear"), year);

        verifyEquals(customerInfoPage.getTextAttribute(driver, "value", "Email"), emailAddress);

        verifyTrue(customerInfoPage.isCheckedToGender("Newsletter"));
    }

    @Description("Add New Address")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_02_Add_New_Address(){
        homePage.clickToLinkPage(driver, "My account");

        customerInfoPage = PageGeneratorManager.getCustomerInfoPage(driver);
        customerInfoPage.openPagesAtMyAccountByName(driver, "Addresses");

        addressPage = PageGeneratorManager.getAddressPage(driver);
        verifyEquals(addressPage.getTitlePage(), "My account - Addresses");

        customerInfoPage.clickToButtonByText(driver, "add-button","Add new");

        addressPage.enterToTextboxByID(driver, "Address_FirstName",firstName);

        addressPage.enterToTextboxByID(driver, "Address_LastName",lastName);

        customerInfoPage.enterToTextboxByID(driver, "Address_Email", emailAddress);

        customerInfoPage.selectDefaultByText(driver, "Address.CountryId", country);

        addressPage.enterToTextboxByID(driver, "Address_City",city);

        addressPage.enterToTextboxByID(driver, "Address_Address1",address1);

        addressPage.enterToTextboxByID(driver, "Address_Address2",address2);

        addressPage.enterToTextboxByID(driver, "Address_ZipPostalCode", zip);

        addressPage.enterToTextboxByID(driver, "Address_PhoneNumber", phoneNumber);

        addressPage.clickToButtonByText(driver, "buttons","Save");
        System.out.println(emailAddress);

        verifyEquals(addressPage.getTextNewAddress("name"), firstName +" "+ lastName);

        verifyEquals(addressPage.getTextNewAddress("email"), "Email: "+emailAddress);

        verifyEquals(addressPage.getTextNewAddress("phone"), "Phone number: " +phoneNumber);

        verifyEquals(addressPage.getTextNewAddress("address1"), address1);

        verifyEquals(addressPage.getTextNewAddress("address2"), address2);

        verifyEquals(addressPage.getTextNewAddress("city-state-zip"), city +", "+ zip);

        verifyEquals(addressPage.getTextNewAddress("country"), country);

    }

    @Description("Change Password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_03_Change_Password(){
        homePage.clickToLinkPage(driver, "My account");

        changePasswordPage = PageGeneratorManager.getChangePasswordPage(driver);
        changePasswordPage.openPagesAtMyAccountByName(driver, "Change password");

        System.out.println(passwordOld);
        changePasswordPage.enterToTextboxByID(driver, "OldPassword", passwordOld);

        changePasswordPage.enterToTextboxByID(driver, "NewPassword",passwordNew);

        changePasswordPage.enterToTextboxByID(driver, "ConfirmNewPassword", passwordNew);

        changePasswordPage.clickToButtonByText(driver, "buttons","Change password");

        verifyEquals(changePasswordPage.getTextChangePassword(), "Password was changed");

        changePasswordPage.clickCloseMessage(driver);

        changePasswordPage.deleteAllCookie(driver);

        homePage = loginPage.openHomePage();

        homePage.clickToLinkPage(driver, "Log in");

        loginPage.loginAsUser(emailAddress, passwordOld);

        Assert.assertEquals(loginPage.getErrorMessageText(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

        loginPage.loginAsUser(emailAddress, passwordNew);

        Assert.assertTrue(loginPage.isDisplayMyAccount());
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }

}

package com.nopcommerce.testCases;

import com.nopcommerce.commons.BaseTest;
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

public class Register extends BaseTest {
    private WebDriver driver;
    private RegisterPO registerPage;
    private LoginPO loginPage;
    private HomePO homePage;
    private AddressPO addressPage;
    private CustomerInfoPO customerInfoPage;
    private ChangePasswordPO changePasswordPage;
    private DataFaker dataFaker;

    private String  firstName, lastName, day, month, year;
    public static String emailAddress, password;

    @Description("Pre-condition")
    @Severity(SeverityLevel.NORMAL)
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforClass(String browserName, String url) {

        driver = getBrowserDriverManager(browserName, url);
        dataFaker = DataFaker.getData();

        emailAddress = dataFaker.getEmail();
        password = dataFaker.getPassword();
        firstName = dataFaker.getFirstName();
        lastName = dataFaker.getLastName();
        day = "10"; month= "January"; year = "1998";

        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Description("Register Empty Data")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_01_Register_Empty_Data() {

        homePage.clickToLinkPage(driver, "Register");

        registerPage = PageGeneratorManager.getRegisterPage(driver);
        registerPage.clickToButtonByText(driver, "buttons", "Register");

        verifyEquals(registerPage.getMessageError(driver, "FirstName-error"), "First name is required.");

        verifyEquals(registerPage.getMessageError(driver, "LastName-error"), "Last name is required.");

        verifyEquals(registerPage.getMessageError(driver, "Email-error"), "Email is required.");

        verifyEquals(registerPage.getMessageError(driver, "Password-error"), "Password is required.");

        verifyEquals(registerPage.getMessageError(driver, "ConfirmPassword-error"), "Password is required.");

    }

    @Description("Register Invalid Email")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_02_Register_Invalid_Email() {
        homePage.clickToLinkPage(driver, "Register");

        registerPage = PageGeneratorManager.getRegisterPage(driver);
        registerPage.checkedTextboxByID(driver, "gender-female");

        registerPage.enterToTextBoxByID(driver, "FirstName", firstName);

        registerPage.enterToTextBoxByID(driver, "LastName", lastName);

        registerPage.selectDefaultByText(driver, "DateOfBirthDay", day);

        registerPage.selectDefaultByText(driver, "DateOfBirthMonth", month);

        registerPage.selectDefaultByText(driver, "DateOfBirthYear", year);

        registerPage.enterToTextBoxByID(driver, "Email", "123@456#%");

        registerPage.checkedTextboxByID(driver, "Newsletter");

        registerPage.enterToTextBoxByID(driver, "Password", password);

        registerPage.enterToTextBoxByID(driver, "ConfirmPassword", password);

        registerPage.clickToButtonByText(driver, "buttons", "Register");

        verifyEquals(registerPage.getMessageError(driver, "Email-error"), "Wrong email");

    }

    @Description("Register Success")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_03_Register_Success() {
        homePage.clickToLinkPage(driver, "Register");

        registerPage = PageGeneratorManager.getRegisterPage(driver);
        registerPage.checkedTextboxByID(driver, "gender-female");

        registerPage.enterToTextBoxByID(driver, "FirstName", firstName);

        registerPage.enterToTextBoxByID(driver, "LastName", lastName);

        registerPage.selectDefaultByText(driver, "DateOfBirthDay", day);

        registerPage.selectDefaultByText(driver, "DateOfBirthMonth", month);

        registerPage.selectDefaultByText(driver, "DateOfBirthYear", year);

        registerPage.enterToTextBoxByID(driver, "Email", emailAddress);

        registerPage.checkedTextboxByID(driver, "Newsletter");

        registerPage.enterToTextBoxByID(driver, "Password", password);

        registerPage.enterToTextBoxByID(driver, "ConfirmPassword", password);

        registerPage.clickToButtonByText(driver, "buttons", "Register");

        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        homePage.clickToLinkPage(driver, "Log out");
    }

    @Description("Register Existing Email")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_04_Register_Existing_Email() {
        homePage.clickToLinkPage(driver, "Register");

        registerPage = PageGeneratorManager.getRegisterPage(driver);
        registerPage.checkedTextboxByID(driver, "gender-female");

        registerPage.enterToTextBoxByID(driver, "FirstName", firstName);

        registerPage.enterToTextBoxByID(driver, "LastName", lastName);

        registerPage.selectDefaultByText(driver, "DateOfBirthDay", day);

        registerPage.selectDefaultByText(driver, "DateOfBirthMonth", month);

        registerPage.selectDefaultByText(driver, "DateOfBirthYear", year);

        registerPage.enterToTextBoxByID(driver, "Email", emailAddress);

        registerPage.checkedTextboxByID(driver, "Newsletter");

        registerPage.enterToTextBoxByID(driver, "Password", password);

        registerPage.enterToTextBoxByID(driver, "ConfirmPassword", password);

        registerPage.clickToButtonByText(driver, "buttons", "Register");

        verifyEquals(registerPage.getErrorExistingEmailMessageTextBox(), "The specified email already exists");
    }

    @Description("Register Password Less Than 6 Chars")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars() {
        homePage.clickToLinkPage(driver, "Register");

        registerPage = PageGeneratorManager.getRegisterPage(driver);
        registerPage.checkedTextboxByID(driver, "gender-female");

        registerPage.enterToTextBoxByID(driver, "FirstName", firstName);

        registerPage.enterToTextBoxByID(driver, "LastName", lastName);

        registerPage.selectDefaultByText(driver, "DateOfBirthDay", day);

        registerPage.selectDefaultByText(driver, "DateOfBirthMonth", month);

        registerPage.selectDefaultByText(driver, "DateOfBirthYear", year);

        registerPage.enterToTextBoxByID(driver, "Email", emailAddress);

        registerPage.checkedTextboxByID(driver, "Newsletter");

        registerPage.enterToTextBoxByID(driver, "Password", "123");

        registerPage.enterToTextBoxByID(driver, "ConfirmPassword", password);

        registerPage.clickToButtonByText(driver, "buttons", "Register");

        verifyEquals(registerPage.getMessageError(driver, "Password-error"),
                "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Description("Register Invalid Confirm Password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_06_Register_Invalid_Confirm_Password() {
        homePage.clickToLinkPage(driver, "Register");

        registerPage = PageGeneratorManager.getRegisterPage(driver);
        registerPage.checkedTextboxByID(driver, "gender-female");

        registerPage.enterToTextBoxByID(driver, "FirstName", firstName);

        registerPage.enterToTextBoxByID(driver, "LastName", lastName);

        registerPage.selectDefaultByText(driver, "DateOfBirthDay", day);

        registerPage.selectDefaultByText(driver, "DateOfBirthMonth", month);

        registerPage.selectDefaultByText(driver, "DateOfBirthYear", year);

        registerPage.enterToTextBoxByID(driver, "Email", emailAddress);

        registerPage.checkedTextboxByID(driver, "Newsletter");

        registerPage.enterToTextBoxByID(driver, "Password", password);

        registerPage.enterToTextBoxByID(driver, "ConfirmPassword", "12345666");

        registerPage.clickToButtonByText(driver, "buttons", "Register");

        verifyEquals(registerPage.getMessageError(driver, "ConfirmPassword-error"),
                "The password and confirmation password do not match.");
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }

}

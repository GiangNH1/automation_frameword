package com.nopcommerce.testCases;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.commons.GlobalConstants;
import com.nopcommerce.commons.PageGeneratorManager;
import com.nopcommerce.pageObjects.HomePO;
import com.nopcommerce.pageObjects.LoginPO;
import com.nopcommerce.pageObjects.RegisterPO;
import com.nopcommerce.utilities.DataFaker;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Set;

public class Login extends BaseTest {
    private WebDriver driver;
    private RegisterPO registerPage;
    private LoginPO loginPage;
    private HomePO homePage;
    private DataFaker dataFaker;
    public static Set<Cookie> loginCookie;

    private String emailAddress, password;

    @Description("Pre-condition")
    @Severity(SeverityLevel.NORMAL)
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforClass(String browserName, String url){

        driver = getBrowserDriverManager(browserName, url);

        dataFaker = DataFaker.getData();

        emailAddress = Register.emailAddress;
        password = Register.password;

        homePage = PageGeneratorManager.getHomePage(driver);

//        homePage.clickToLinkPage(driver, "Register");
//        registerPage = PageGeneratorManager.getRegisterPage(driver);
//
//        registerPage.enterToTextboxByID(driver, "FirstName",dataFaker.getFirstName());
//
//        registerPage.enterToTextboxByID(driver, "LastName",dataFaker.getLastName());
//
//        registerPage.enterToTextboxByID(driver, "Email", emailAddress);
//
//        registerPage.enterToTextboxByID(driver, "Password",password);
//
//        registerPage.enterToTextboxByID(driver, "ConfirmPassword",password);
//
//        registerPage.clickToButtonByText(driver, "buttons","Register");
//        System.out.println(emailAddress);
//
//        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
//
//        homePage.clickToLinkPage(driver, "Log out");
    }

    @Description("Login Empty Data")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_01_Login_Empty_Data() {
        homePage.clickToLinkPage(driver, "Log in");

        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.enterToTextboxByID(driver, "Email","");

        loginPage.enterToTextboxByID(driver, "Password","");

        loginPage.clickToButtonByText(driver, "buttons","Log in");

        verifyEquals(loginPage.getMessageError(driver, "Email-error"), "Please enter your email.");

    }

    @Description("Login Invalid Email")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_02_Login_Invalid_Email(){
        homePage.clickToLinkPage(driver, "Log in");

        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.enterToTextboxByID(driver, "Email","123@ahshajh#");

        loginPage.clickToButtonByText(driver, "buttons","Log in");

        verifyEquals(loginPage.getMessageError(driver, "Email-error"), "Wrong email.");

    }

    @Description("Login Email Not Register")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_03_Login_Email_Not_Register(){
        homePage.clickToLinkPage(driver, "Log in");

        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.enterToTextboxByID(driver, "Email","auto2033@gmail.com");

        loginPage.enterToTextboxByID(driver, "Password",password);

        loginPage.clickToButtonByText(driver, "buttons","Log in");

        Assert.assertEquals(loginPage.getErrorMessageText(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }

    @Description("Login Email Registered Password Empty")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_04_Login_Email_Registered_Password_Empty(){
        homePage.clickToLinkPage(driver, "Log in");

        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.enterToTextboxByID(driver, "Email", emailAddress);

        loginPage.enterToTextboxByID(driver, "Password","");

        loginPage.clickToButtonByText(driver, "buttons","Log in");

        Assert.assertEquals(loginPage.getErrorMessageText(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

    }

    @Description("Login Email Registered Invalid Password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_05_Login_Email_Registered_Invalid_Password(){
        homePage.clickToLinkPage(driver, "Log in");

        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.enterToTextboxByID(driver, "Email", emailAddress);

        loginPage.enterToTextboxByID(driver, "Password","123444");

        loginPage.clickToButtonByText(driver, "buttons","Log in");

        Assert.assertEquals(loginPage.getErrorMessageText(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

    }

    @Description("Login Success")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_06_Login_Success(){
        homePage.clickToLinkPage(driver, "Log in");

        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.enterToTextboxByID(driver, "Email", emailAddress);

        loginPage.enterToTextboxByID(driver, "Password",password);

        loginPage.clickToButtonByText(driver, "buttons","Log in");
        loginCookie = loginPage.getAllCookie(driver);

        homePage = PageGeneratorManager.getHomePage(driver);
        Assert.assertTrue(loginPage.isDisplayMyAccount());

        homePage.clickToLinkPage(driver, "Log out");
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }
}

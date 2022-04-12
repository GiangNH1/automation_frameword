package com.nopcommerce.testCases;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.commons.GlobalConstants;
import com.nopcommerce.commons.PageGeneratorManager;
import com.nopcommerce.pageObjects.HomePO;
import com.nopcommerce.pageObjects.LoginPO;
import com.nopcommerce.pageObjects.RegisterPO;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Set;

public class Log4jLogin extends BaseTest {
    private WebDriver driver;
    private RegisterPO registerPage;
    private LoginPO loginPage;
    private HomePO homePage;
    public static Set<Cookie> loginCookie;

    private String emailAddress, password;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforClass(String browserName, String url){

        log.info("Pre-condition - step 01: Open Page With Browser '" + browserName + "' And Url: '" + url +";");
        driver = getBrowserDriverManager(browserName, url);

        emailAddress = GlobalConstants.EMAIL_ADDRESS;
        password = "123456";

        log.info("Pre-condition - step 02: Khởi tạo page search");
        homePage = PageGeneratorManager.getHomePage(driver);

        log.info("Register - step 01: Navigate to 'Register' page");
        homePage.clickToLinkPage(driver, "Register");
        registerPage = PageGeneratorManager.getRegisterPage(driver);

        log.info("Register - step 02: Enter to Firstname textbox with value is 'Giang'");
        registerPage.enterToTextboxByID(driver, "FirstName","Giang");

        log.info("Register - step 03: Enter to Lastname textbox with value is 'Auto'");
        registerPage.enterToTextboxByID(driver, "LastName","Auto");

        log.info("Register - step 04: Enter to UseremailAddress textbox with value is '"+ emailAddress +"'");
        registerPage.enterToTextboxByID(driver, "Email", emailAddress);

        log.info("Register - step 05: Enter to userPassword textbox with value is '"+ password +"'");
        registerPage.enterToTextboxByID(driver, "Password",password);

        log.info("Register - step 06: Enter to ConfirmPassword textbox with value is '"+ password +"'");
        registerPage.enterToTextboxByID(driver, "ConfirmPassword",password);

        log.info("Register - step 07: Click to 'Register' button");
        registerPage.clickToButtonByText(driver, "buttons","Register");
        System.out.println(emailAddress);

        log.info("Register - step 08: Verify register success message is display");
        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        log.info("Register - step 09: Click to logout link");
        homePage.clickToLinkPage(driver, "Log out");
    }

    //@Test
    public void TC_01_Login_Empty_Data() {
        log.info("Login Empty Data - step 01: Navigate to 'Login' page");
        homePage.clickToLinkPage(driver, "Log in");

        loginPage = PageGeneratorManager.getLoginPage(driver);
        log.info("Login Empty Data - step 02: Enter to Email textbox with value is ''");
        loginPage.enterToTextboxByID(driver, "Email","");

        log.info("Login Empty Data - step 03: Enter to Password textbox with value is ''");
        loginPage.enterToTextboxByID(driver, "Password","");

        log.info("Login Empty Data - step 04: Click to 'Log in' button");
        loginPage.clickToButtonByText(driver, "buttons","Log in");

        log.info("Login Empty Data - step 05: Verify message error at email");
        verifyEquals(loginPage.getMessageError(driver, "Email-error"), "Please enter your email");

    }

    //@Test
    public void TC_02_Login_Invalid_Email(){
        log.info("Login Invalid Email - step 01: Navigate to 'Login' page");
        homePage.clickToLinkPage(driver, "Log in");

        loginPage = PageGeneratorManager.getLoginPage(driver);
        log.info("Login Invalid Email - step 02: Enter to Email textbox with value is '123@ahshajh#'" );
        loginPage.enterToTextboxByID(driver, "Email","123@ahshajh#");

        log.info("Login Invalid Email - step 03: Click to 'Log in' button");
        loginPage.clickToButtonByText(driver, "buttons","Log in");

        log.info("Login Invalid Email - step 04: Verify message error at email");
        verifyEquals(loginPage.getMessageError(driver, "Email-error"), "Wrong email");

    }

    //@Test
    public void TC_03_Login_Email_Not_Register(){
        log.info("Login Email Not Register - step 01: Navigate to 'Login' page");
        homePage.clickToLinkPage(driver, "Log in");

        loginPage = PageGeneratorManager.getLoginPage(driver);
        log.info("Login Email Not Register - step 02: Enter to Email textbox with value is 'auto2033@gmail.com'" );
        loginPage.enterToTextboxByID(driver, "Email","auto2033@gmail.com");

        log.info("Login Email Not Register - step 03: Enter to Password textbox with value is '" + password +"'" );
        loginPage.enterToTextboxByID(driver, "Password",password);

        log.info("Login Email Not Register - step 04: Click to 'Log in' button");
        loginPage.clickToButtonByText(driver, "buttons","Log in");

        log.info("Login Email Not Register - step 05: Verify login unsuccessful");
        Assert.assertEquals(loginPage.getErrorMessageText(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }

    //@Test
    public void TC_04_Login_Email_Registered_Password_Empty(){
        log.info("Login Email Registered Password Empty - step 01: Navigate to 'Login' page");
        homePage.clickToLinkPage(driver, "Log in");

        loginPage = PageGeneratorManager.getLoginPage(driver);
        log.info("Login Email Registered Password Empty - step 02: Enter to Email textbox with value is '" + emailAddress +"'" );
        loginPage.enterToTextboxByID(driver, "Email", emailAddress);

        log.info("Login Email Registered Password Empty - step 03: Enter to Password textbox with value is ''" );
        loginPage.enterToTextboxByID(driver, "Password","");

        log.info("Login Email Registered Password Empty - step 04: Click to 'Log in' button");
        loginPage.clickToButtonByText(driver, "buttons","Log in");

        log.info("Login Email Registered Password Empty - step 05: Verify login unsuccessful");
        Assert.assertEquals(loginPage.getErrorMessageText(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

    }

    //@Test
    public void TC_05_Login_Email_Registered_Invalid_Password(){
        log.info("Login Email Registered Invalid Password - step 01: Navigate to 'Login' page");
        homePage.clickToLinkPage(driver, "Log in");

        loginPage = PageGeneratorManager.getLoginPage(driver);
        log.info("Login Email Registered Invalid Password - step 02: Enter to Email textbox with value is '" + emailAddress +"'" );
        loginPage.enterToTextboxByID(driver, "Email", emailAddress);

        log.info("Login Email Registered Invalid Password - step 03: Enter to Password textbox with value is '123444'" );
        loginPage.enterToTextboxByID(driver, "Password","123444");

        log.info("Login Email Registered Invalid Password - step 04: Click to 'Log in' button");
        loginPage.clickToButtonByText(driver, "buttons","Log in");

        log.info("Login Email Registered Invalid Password - step 05: Verify login unsuccessful");
        Assert.assertEquals(loginPage.getErrorMessageText(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

    }

    @Test
    public void TC_06_Login_Success(){
        log.info("Login Success - step 01: Navigate to 'Login' page");
        homePage.clickToLinkPage(driver, "Log in");

        loginPage = PageGeneratorManager.getLoginPage(driver);
        log.info("Login Success - step 02: Enter to Email textbox with value is '" + emailAddress +"'" );
        loginPage.enterToTextboxByID(driver, "Email", emailAddress);

        log.info("Login Success - step 03: Enter to Password textbox with value is '" + password +"'" );
        loginPage.enterToTextboxByID(driver, "Password",password);

        log.info("Login Success - step 04: Click to 'Log in' button");
        loginPage.clickToButtonByText(driver, "buttons","Log in");
        log.info("Login Success - step 05: Get All cookie login page");
        loginCookie = loginPage.getAllCookie(driver);

        homePage = PageGeneratorManager.getHomePage(driver);
        log.info("Login Success - step 06: Verify login success");
        Assert.assertTrue(loginPage.isDisplayMyAccount());

        log.info("Login Success - step 07: Click to 'Log out' link");
        homePage.clickToLinkPage(driver, "Log out");
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }
}

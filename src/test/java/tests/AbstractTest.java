package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import pages.common.MainPage;
import pages.user.LoginPage;
import utils.Browser;
import utils.TestContext;

public class AbstractTest
{
    public static TestContext context = new TestContext();
    public static Browser browser = context.doCreateBrowser();

    protected static final int DEFAULT_SLEEP = 2000;

    @BeforeClass
    public static void setUpClass()
    {
        browser.get("https://www.migros.com.tr");
    }

    @AfterClass
    public static void tearDownClass()
    {
        if (null != browser)
            browser.close();
    }


    public void login(String username, String password)
    {
        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.loginButton);

        LoginPage loginPage = new LoginPage(browser);

        sleep();
        browser.waitAndSendKeys(loginPage.inputEmail, username);
        browser.waitAndSendKeys(loginPage.inputPassword, password);

        browser.waitAndClick(loginPage.loginButton);
        sleep();
    }

    public static void sleep()
    {
        sleep(DEFAULT_SLEEP);
    }

    public static void sleep(int miliseconds)
    {
        try
        {
            Thread.sleep(miliseconds);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}

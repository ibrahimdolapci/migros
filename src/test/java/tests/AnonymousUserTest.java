package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import utils.Browser;
import utils.TestContext;

import java.io.IOException;

public class AnonymousUserTest {
    public static TestContext context = new TestContext();
    public static Browser browser = context.doCreateBrowser();

    @BeforeClass
    public static void setUpClass() throws IOException {
        browser.get("https://www.migros.com.tr");
    }

    @AfterClass
    public static void tearDownClass() {
        if (null != browser)
            browser.close();
    }
}

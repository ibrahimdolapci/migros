package tests;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import pages.common.MainPage;
import pages.user.LoginPage;
import utils.Browser;
import utils.TestContext;

import java.io.FileInputStream;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class AuthorizedUserTest {
    public static TestContext context = new TestContext();
    public static Browser browser = context.doCreateBrowser();

    @BeforeClass
    public static void setUpClass() throws IOException, InterruptedException {

        browser.get("https://www.migros.com.tr");

        FileInputStream serviceAccount = new FileInputStream("src/test/resources/service-account.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://migrostesting.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        login();
        clearBasket();
    }

    @AfterClass
    public static void tearDownClass() {
        if (null != browser)
            browser.close();
    }


    public static void login() throws InterruptedException {
        MainPage mainPage = new MainPage(browser);
        LoginPage loginPage = new LoginPage(browser);

        if (browser.isElementDisplayed(loginPage.displayName)) {
            return;
        }

        if (browser.isElementDisplayed(mainPage.loginButton)) {
            browser.waitAndClick(mainPage.loginButton);
        }

        final String phoneNumber = context.getInternalProps().getPhoneNumber();

        browser.waitAndSendKeys(loginPage.inputPhone, phoneNumber);
        browser.waitAndClick(loginPage.loginButton);
        getVerificationCode();
    }

    public void logout() {
        LoginPage loginPage = new LoginPage(browser);
        if (!browser.isElementDisplayed(loginPage.displayName)) {
            return;
        }

        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(loginPage.displayName);
        browser.waitAndClick(mainPage.logoutButton);
    }

    public static void getVerificationCode() throws InterruptedException {
        final LoginPage loginPage = new LoginPage(browser);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("verification-code");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String verificationCode = dataSnapshot.getValue(String.class);
                browser.waitAndSendKeys(loginPage.inputVerifyPhone, verificationCode);
                browser.waitAndClick(loginPage.verifyOtpButton);

                ref.removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        sleep(20000);
    }

    public static void clearBasket() throws InterruptedException {
        sleep(1000);
        MainPage mainPage = new MainPage(browser);
        if (browser.isElementDisplayed((mainPage.closeStoreAnnouncementButton))) {
            browser.waitAndClick(mainPage.closeStoreAnnouncementButton);
        }
        browser.waitAndClick(mainPage.shoppingBasketButton);

        while (browser.isElementDisplayed(mainPage.trashButton)) {
            browser.waitAndClick(mainPage.trashButton);
        }
    }
}

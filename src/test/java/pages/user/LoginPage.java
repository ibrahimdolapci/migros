package pages.user;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;
import utils.Browser;

public class LoginPage extends AbstractPage
{
    public LoginPage(Browser browser)
    {
        super(browser);
    }

    @FindBy(id = "phoneNumber")
    public WebElement inputPhone;

    @FindBy(id = "phoneNumberToVerify")
    public WebElement inputVerifyPhone;

    @FindBy(id = "membership-modal-login-button")
    public WebElement loginButton;

    @FindBy(id = "verifyOtpButton")
    public WebElement verifyOtpButton;

    @FindBy(className = "display-name")
    public WebElement displayName;

    @FindBy(id = "otp-error-message")
    public WebElement otpErrorMessage;

    @FindBy(css = "#otp-modal .modal-header button.close")
    public WebElement closePopupButton;
}

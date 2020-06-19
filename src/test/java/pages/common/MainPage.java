package pages.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;
import utils.Browser;

public class MainPage extends AbstractPage
{
    public MainPage(Browser browser)
    {
        super(browser);
    }

    @FindBy(css = ".header-sticky-register-login .header-sticky-register-login--list-item:last-child")
    public WebElement loginButton;

    @FindBy(linkText = "Bebek, Oyuncak")
    public WebElement babyToyMenu;

    @FindBy(css = "a[data-monitor-ga-action='Bebek Bezi']")
    public WebElement diaperCategory;

    @FindBy(css = ".shoping-cart-icon-block .fa-shopping-cart")
    public WebElement shoppingBasketButton;

    @FindBy(xpath = "//*[@id=\"cart-bar\"]/div/div/div/div/h3")
    public WebElement emptyBasketText;

    @FindBy(className = "rubbish")
    public WebElement trashButton;

    @FindBy(className = "go-to-basket-button")
    public WebElement goToBasketButton;

    @FindBy(css = "#deliveryFromStoreAnnouncement button[aria-label='Close']")
    public WebElement closeStoreAnnouncementButton;

    @FindBy(css = "#membership-modal button.close")
    public WebElement closePopupButton;

    @FindBy(className = "progress-bar-text")
    public WebElement progressBarText;

    @FindBy(css = ".action-td .plus-orange")
    public WebElement plusButton;

    @FindBy(linkText = "Çıkış Yap")
    public WebElement logoutButton;

    @FindBy(css = "#cart-bar td.title")
    public WebElement addedCartItem;

    @FindBy(className = "cookie-popup-dismiss")
    public WebElement cookieDismissButton;
}

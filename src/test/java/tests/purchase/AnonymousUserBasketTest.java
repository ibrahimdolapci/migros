package tests.purchase;

import org.junit.Assert;
import org.junit.Test;
import pages.common.CategoryPage;
import pages.common.MainPage;
import pages.purchase.BasketPage;
import pages.user.LoginPage;
import tests.AnonymousUserTest;

public class AnonymousUserBasketTest extends AnonymousUserTest {

    @Test
    public void wrongVerificationCode() {
        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.loginButton);

        final LoginPage loginPage = new LoginPage(browser);
        browser.waitAndSendKeys(loginPage.inputPhone, "5123456789");
        browser.waitAndClick(loginPage.loginButton);
        browser.waitAndSendKeys(loginPage.inputVerifyPhone, "123456");
        browser.waitAndClick(loginPage.verifyOtpButton);

        Assert.assertNotNull(loginPage.otpErrorMessage.getText());
        browser.waitAndClick(loginPage.closePopupButton);
    }

    @Test
    public void babyToyMenu() {
        MainPage mainPage = new MainPage(browser);

        if (browser.isElementDisplayed(mainPage.cookieDismissButton)) {
            browser.waitAndClick(mainPage.cookieDismissButton);
        }
        browser.waitAndClick(mainPage.babyToyMenu);

        CategoryPage categoryPage = new CategoryPage(browser);

        Assert.assertEquals("Bebek, Oyuncak", categoryPage.currentCategoryTitle.getText());
    }

    @Test
    public void diaperMenu() {
        babyToyMenu();

        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.diaperCategory);

        CategoryPage categoryPage = new CategoryPage(browser);

        Assert.assertEquals("Bebek Bezi", categoryPage.currentCategoryTitle.getText());
    }

    @Test
    public void priceDescendentSorting() {
        diaperMenu();
        CategoryPage categoryPage = new CategoryPage(browser);
        browser.waitAndClick(categoryPage.getPrimaBrandFilter());
        browser.waitAndClick(categoryPage.getBeden4Filter());
        browser.waitAndClick(categoryPage.othersButton);
        browser.waitAndClick(categoryPage.sortPriceDesc);

        String attribute = categoryPage.selectedSortCriteria.getAttribute("data-sort-criteria");
        Assert.assertEquals("PRICE_DESC", attribute);
    }

    @Test
    public void addToBasket() {
        priceDescendentSorting();

        CategoryPage categoryPage = new CategoryPage(browser);
        browser.waitAndClick(categoryPage.firstProductAddBasketButton);

        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.shoppingBasketButton);
        Assert.assertEquals(mainPage.addedCartItem.getText(), categoryPage.firstProductTitle.getText());
    }

    @Test
    public void summaryTotal() {
        addToBasket();

        MainPage mainPage = new MainPage(browser);
        while (browser.isElementDisplayed(mainPage.progressBarText)) {
            browser.waitAndClick(mainPage.plusButton);
        }
        browser.waitAndClick(mainPage.goToBasketButton);

        BasketPage basketPage = new BasketPage(browser);
        if (browser.isElementDisplayed(basketPage.cartCampaignModal)) {
            browser.waitAndClick(basketPage.CampaignModalCloseButton);
        }
        String basketTotal = basketPage.basketTotal.getText();

        browser.waitAndClick(basketPage.clothBagRadioButton);
        browser.waitAndClick(basketPage.approveBasket);

        String summaryTotal = basketPage.basketTotal.getText();
        Assert.assertEquals(basketTotal, summaryTotal);
    }
}

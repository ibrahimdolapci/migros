package tests.purchase;

import org.junit.Assert;
import org.junit.Test;
import pages.common.CategoryPage;
import pages.common.MainPage;
import pages.purchase.BasketPage;
import tests.AuthorizedUserTest;

public class AuthorizedUserBasketTest extends AuthorizedUserTest {

    @Test
    public void clearBasketItems() throws InterruptedException {
        login();
        clearBasket();

        MainPage mainPage = new MainPage(browser);
        Assert.assertEquals("Sepetiniz şu anda boş.", mainPage.emptyBasketText.getText());
    }

    @Test
    public void babyToyMenu() {
        MainPage mainPage = new MainPage(browser);

        if (browser.isElementDisplayed(mainPage.cookieDismissButton)) {
            browser.waitAndClick(mainPage.cookieDismissButton);
        }
        browser.waitAndClick(mainPage.babyToyMenu);
        Assert.assertEquals("Bebek, Oyuncak", mainPage.currentCategoryTitle.getText());
    }

    @Test
    public void diaperMenu() {
        babyToyMenu();

        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.diaperCategory);
        Assert.assertEquals("Bebek Bezi", mainPage.currentCategoryTitle.getText());
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
    public void addToBasket() throws InterruptedException {
        priceDescendentSorting();
        login();

        CategoryPage categoryPage = new CategoryPage(browser);
        browser.waitAndClick(categoryPage.firstProductAddBasketButton);

        MainPage mainPage = new MainPage(browser);
        browser.waitAndClick(mainPage.shoppingBasketButton);
        Assert.assertEquals(mainPage.addedCartItem.getText(), categoryPage.firstProductTitle.getText());
    }

    @Test
    public void summaryTotal() throws InterruptedException {
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
        String basketTotal = basketPage.basketTotal.getAttribute("data-raw-amount");

        browser.waitAndClick(basketPage.clothBagRadioButton);
        browser.waitAndClick(basketPage.approveBasket);

        String summaryTotal = basketPage.basketTotal.getAttribute("data-raw-amount");
        Assert.assertEquals(basketTotal, summaryTotal);
    }
}
package pages.purchase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;
import utils.Browser;

public class BasketPage extends AbstractPage
{
    public BasketPage(Browser browser)
    {
        super(browser);
    }

    @FindBy(id = "summaryRevenue")
    public WebElement basketTotal;

    @FindBy(id = "in-cart-next-button")
    public WebElement approveBasket;

    @FindBy(id = "cartCampaignModal")
    public WebElement cartCampaignModal;

    @FindBy(css = "#cartCampaignModal button.close")
    public WebElement CampaignModalCloseButton;

    @FindBy(xpath = "//*[@id=\"cartPageBagChoiceForm\"]/div/label[2]")
    public WebElement clothBagRadioButton;
}

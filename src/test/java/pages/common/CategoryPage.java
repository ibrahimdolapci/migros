package pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;
import utils.Browser;

import java.util.List;

public class CategoryPage extends AbstractPage
{
    private Browser browser;
    public CategoryPage(Browser browser)
    {
        super(browser);
        this.browser = browser;
    }

    public WebElement getPrimaBrandFilter(){
        WebElement brands = browser.findElement(By.cssSelector(".category-sidebar > .in > .filter:nth-of-type(3) .brands-list"));
        return brands.findElement(By.xpath("//*[contains(text(), 'Prima')]"));
    };

    public WebElement getBeden4Filter(){
        WebElement brands = browser.findElement(By.cssSelector(".category-sidebar > .in > .filter:nth-of-type(4) .brands-list"));
        return brands.findElement(By.xpath("//*[contains(text(), '4 Beden')]"));
    };

    @FindBy(css = "#product-list-sort li[data-sort-criteria='PRICE_DESC']")
    public WebElement sortPriceDesc;

    @FindBy(xpath = "//*[@id=\"product-list-sort\"]/nav/button")
    public WebElement othersButton;

    @FindBy(css = ".sub-category-product-list .list:nth-of-type(1)")
    public WebElement firstProduct;

    public WebElement firstProductTitle = firstProduct.findElement(By.className("product-card-title"));

    public WebElement firstProductAddBasketButton = firstProduct.findElement(By.className("product-card-button"));

    @FindBy(css = "#product-list-sort .sort-options.selected")
    public WebElement selectedSortCriteria;
}

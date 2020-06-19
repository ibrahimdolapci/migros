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
    }

    public WebElement getBeden4Filter(){
        WebElement brands = browser.findElement(By.cssSelector(".category-sidebar > .in > .filter:nth-of-type(4) .brands-list"));
        return brands.findElement(By.xpath("//*[contains(text(), '4 Beden')]"));
    }

    @FindBy(css = "#product-list-sort li[data-sort-criteria='PRICE_DESC']")
    public WebElement sortPriceDesc;

    @FindBy(xpath = "//*[@id=\"product-list-sort\"]/nav/button")
    public WebElement othersButton;

    @FindBy(css = ".sub-category-product-list .list:nth-of-type(1) .product-card-title")
    public WebElement firstProductTitle;

    @FindBy(css = ".sub-category-product-list .list:nth-of-type(1) .product-card-button")
    public WebElement firstProductAddBasketButton;

    @FindBy(css = "#product-list-sort .sort-options.selected")
    public WebElement selectedSortCriteria;

    @FindBy(className = "current-category--title")
    public WebElement currentCategoryTitle;
}

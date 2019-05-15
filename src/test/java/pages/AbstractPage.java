package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage
{
    protected WebDriver browser;

    protected AbstractPage(WebDriver browser)
    {
        PageFactory.initElements(browser, this);
    }
}
